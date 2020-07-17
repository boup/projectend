package com.advisor.adtrans;
import com.advisor.adtrans.model.*;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.NominatimOptions;
import fr.dudie.nominatim.model.Address;
import fr.dudie.nominatim.model.BoundingBox;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

public class Parser {

    static final String CssPath = "#content > div > p:nth-child(1)";
    static final String CssPath_2 = "#content > div > p:nth-child(2)";

    public Parser() {
    }

    public void parse(boolean isJavaScriptNeeded) throws IOException {

        Graphopper hopper = new Graphopper();

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);

        List<Line> result = new ArrayList<>();

        //result.addAll(parseAFTU(true, webClient)); FROM http://aftu-senegal.org/kmz/
        result.addAll(parseDemDikk(isJavaScriptNeeded, webClient));

        for (Line line : result) {
            System.out.println(line.getName());
            System.out.println(line.getStops());
        }


        //readNodesFromDatabase! so that the one already processed are not added again
        Map<String, Stop> nodes = new HashMap<>();
        //readEdgesFromDatabase! so that the one already processed are not added again
        Map<Pair<Long, Long>, Edge> edges = new HashMap<>();

        Long counter = Long.valueOf(nodes.size() + 1);
        List<Stop> newNodes = new ArrayList<>();
        List<Edge> newEdges = new ArrayList<>();

        buildGraphForStops(result, nodes, edges, newNodes, newEdges, counter);

        findCoordinatesForNewStops(newNodes);

        countDistancesBetweenNewStops(newNodes, newEdges, hopper);

        runDijkstraAlgorithm(nodes, edges, counter, 14.6714, -17.4399, 14.7073, -17.4512, hopper);

        saveDataToDB();
    }

    private List<Line> parseDemDikk(boolean isJavaScriptNeeded, WebClient webClient) throws IOException {
        //webClient.waitForBackgroundJavaScript(1000);
        webClient.getOptions().setJavaScriptEnabled(isJavaScriptNeeded);
        HtmlPage page = webClient.getPage("http://demdikk.com/");

        String pageHtml = page.asXml();

        List<Line> result = new ArrayList<Line>();

        Document doc = Jsoup.parse(pageHtml);

        // Lignes
        String link = null;
        int nr = 1;
        link = doc.select("#text-2 > div > ul > li:nth-child(1) > a").attr("href");
        while (link != "") {
            extractLineInfo(webClient, result, link, nr, CssPath, CssPath_2);
            // go to next page
            nr++;
            if (nr == 13) nr = 18;
            link = doc.select("#text-2 > div > ul > li:nth-child(" + nr + ") > a").attr("href");
        }
        return result;
    }

    private void saveDataToDB() {
    }

    private void runDijkstraAlgorithm(Map<String, Stop> nodes, Map<Pair<Long, Long>, Edge> edges, Long counter, Double startLat, Double startLon, Double endLat, Double endLon, Graphopper hopper) {

        Map<Long, Stop> nodeIdToNode = new HashMap<>();
        nodes.values().forEach(v -> nodeIdToNode.put(v.getId(), v));

        final Graph<Long, DefaultWeightedEdge> graph
                = new SimpleDirectedWeightedGraph(DefaultWeightedEdge.class);

        nodes.values().forEach(node -> graph.addVertex(node.getId()));
        edges.keySet().forEach(pair -> {
            graph.addEdge(pair.getLeft(), pair.getRight());
            graph.setEdgeWeight(pair.getLeft(), pair.getRight(), edges.get(pair).getEdgeWeight());
        });

        graph.addVertex((long) 0);
        graph.addVertex((long) 9999);
        nodeIdToNode.put((long) 0, new Stop("Start"));
        nodeIdToNode.put((long) 9999, new Stop("End"));
        nodes.values().forEach(node -> {
            graph.addEdge((long) 0, node.getId());
            graph.addEdge(node.getId(), (long) 9999);
            graph.addEdge((long) 0, (long) 9999);

            long timeSE = hopper.countTimeOnFoot(startLat, startLon, endLat, endLon);
            graph.setEdgeWeight((long) 0, (long) 9999, timeSE);
            edges.put(Pair.of(0L, 9999L), new Edge(0L, 9999L, timeSE, EdgeType.FOOT, "Walk"));

            long timeEdge = hopper.countTimeOnFoot(startLat, startLon, node.getLattitude(), node.getLongitude());
            graph.setEdgeWeight(0L, node.getId(), timeEdge);
            edges.put(Pair.of(0L, node.getId()), new Edge(0L, node.getId(), timeEdge, EdgeType.FOOT, "Walk"));

            long timeEdge2 = hopper.countTimeOnFoot(node.getLattitude(), node.getLongitude(), endLat, endLon);
            graph.setEdgeWeight(node.getId(), 9999L, timeEdge2);
            edges.put(Pair.of(node.getId(), 9999L), new Edge(node.getId(), 9999L, timeEdge2, EdgeType.FOOT, "Walk"));
        });

        GraphPath<Long, DefaultWeightedEdge> sp = DijkstraShortestPath.findPathBetween(graph, 0L, 9999L);

        // Print results
        for (DefaultWeightedEdge e : sp.getEdgeList()) {
            System.out.println(nodeIdToNode.get(graph.getEdgeSource(e)).getName() + " -> " + nodeIdToNode.get(graph.getEdgeTarget(e)).getName() + " " + edges.get(Pair.of(graph.getEdgeSource(e), graph.getEdgeTarget(e))).getLineName());
        }
    }

    private void extractLineInfo(WebClient webClient, List<Line> result, String link, int nr, String cssPathRoute, String cssPathName) throws IOException {
        HtmlPage page;
        String pageHtml;
        page = webClient.getPage(link);
        pageHtml = page.asXml();
        Document linePage = Jsoup.parse(pageHtml);
        if (nr == 1 || nr == 23) {
            Line line = readLineName(linePage.select(cssPathRoute).text(), "Ligne", " ", 1);
            line = createLineWithStops(linePage.select(cssPathName).text(), "–", 0, line);
            result.add(line);
        } else {
            Line line = readLineName(linePage.select(cssPathRoute).text(), "Ligne", " ", 1);
            line = createLineWithStops(linePage.select(cssPathRoute).text(), "–", 1, line);
            result.add(line);
        }
    }

    private void buildGraphForStops(List<Line> lines, Map<String, Stop> nodes, Map<Pair<Long, Long>, Edge> edges, List<Stop> newNodes, List<Edge> newEdges, Long counter) {

        for (Line line : lines) {
            Stop lastStop = null;
            for (Stop stop : line.getStops()) {
                Stop node = null;
                if (!nodes.containsKey(stop)) {
                    node = new Stop(stop);
                    node.setId(counter);
                    counter++;
                    nodes.put(stop.getName(), node);
                    newNodes.add(node);
                } else {
                    node = nodes.get(stop);
                }
                if (lastStop != null) {
                    Pair<Long, Long> pairLastMe = Pair.of(lastStop.getId(), node.getId());
                    Pair<Long, Long> pairMeLast = Pair.of(node.getId(), lastStop.getId());
                    if (!edges.containsKey(pairLastMe)) {
                        Edge edge = new Edge(lastStop.getId(), node.getId(), 0L, EdgeType.BUS, "BUS " + line.getName());
                        edges.put(pairLastMe, edge);
                        newEdges.add(edge);
                    } else {
                        edges.get(pairLastMe).setLineName(edges.get(pairLastMe).getLineName() + " or " + line.getName());
                    }
                    if (!line.getStops().get(0).equals(line.getStops().get(line.getStops().size() - 1))) {
                        // that line goes in circle!
                        if (!edges.containsKey(pairMeLast)) {
                            Edge edge2 = new Edge(node.getId(), lastStop.getId(), 0L, EdgeType.BUS, "BUS " + line.getName());
                            newEdges.add(edge2);
                            edges.put(pairMeLast, edge2);
                        } else
                            edges.get(pairMeLast).setLineName(edges.get(pairMeLast).getLineName() + " or " + line.getName());
                    }

                }
                lastStop = node;
            }
        }
    }


    private void countDistancesBetweenNewStops(List<Stop> newNodes, List<Edge> newEdges, Graphopper hopper) {
        Map<Long, Stop> nodeIdToNode = new HashMap<>();

        newNodes.forEach(v -> nodeIdToNode.put(v.getId(), v));

        newEdges.forEach(edge -> {
            edge.setEdgeWeight(hopper.countTimeInMetersByCar(nodeIdToNode.get(edge.getNodeOutId()).getLattitude(),
                    nodeIdToNode.get(edge.getNodeOutId()).getLongitude(),
                    nodeIdToNode.get(edge.getNodeInId()).getLattitude(),
                    nodeIdToNode.get(edge.getNodeInId()).getLongitude()));
            edge.setDistance(hopper.countDistanceInMetersByCar(nodeIdToNode.get(edge.getNodeOutId()).getLattitude(),
                    nodeIdToNode.get(edge.getNodeOutId()).getLongitude(),
                    nodeIdToNode.get(edge.getNodeInId()).getLattitude(),
                    nodeIdToNode.get(edge.getNodeInId()).getLongitude()));
        });
    }

    private void findCoordinatesForNewStops(List<Stop> newStops) throws IOException {

        final SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        final ClientConnectionManager connexionManager = new SingleClientConnManager(null, registry);

        final HttpClient httpClient = new DefaultHttpClient(connexionManager, null);

        // Senegal box
        final BoundingBox viewBox = new BoundingBox();
        viewBox.setNorth(14.8618);
        viewBox.setSouth(14.6344);
        viewBox.setEast(-17.2128);
        viewBox.setWest(-17.5562);
        final NominatimOptions options = new NominatimOptions();
        options.setViewBox(viewBox);
        options.setBounded(true);
        JsonNominatimClient boundedNominatimClient = new JsonNominatimClient("https://nominatim.openstreetmap.org/", httpClient, "mw@amu.edu.pl", options);

        newStops.stream().forEach(stop -> {
            List<Address> addresses = new ArrayList<>();
            try {
                addresses = boundedNominatimClient.search(stop.getName() + ", Dakar, Senegal");
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Address> dakar = null;
            while (addresses.size() == 0) {
                if (dakar == null) {
                    try {
                        dakar = boundedNominatimClient.search("Dakar, Senegal");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    addresses = dakar;
                }
                stop.setNeedCheck(true);
            }
            stop.setLattitude(addresses.get(0).getLatitude());
            stop.setLongitude(addresses.get(0).getLatitude());
        });
    }

    private Line readLineName(String text, String parseKeyWord, String splitSymbol, int lineNameElementNr) {
        String[] array = text.split(parseKeyWord);

        String[] lineName = array[1].split(splitSymbol);
        String lineNr = lineName[lineNameElementNr];
        Line line = new Line(parseKeyWord + " " + lineNr);
        return line;
    }

    private Line createLineWithStops(String text, String splitSymbol, int lineNameElementNr, Line line) {
        String[] array = text.split(line.getName());
        String[] lineStops;
        if (array.length > 0) {
            lineStops = array[lineNameElementNr].split(splitSymbol);
        } else {
            lineStops = array[0].split(splitSymbol);
        }
        lineStops[0] = lineStops[0].replace(line.getName() + " ", "");

        for (String s : Arrays.asList(lineStops)) {
            s = s.trim();
            // delete dots at the end
            s = s.replaceAll("^(.*)\\.(.*)$", "$1$2");
            s = s.toLowerCase();
            s = StringUtils.capitalize(s);
            line.addStop(new Stop(s));
        }

        return line;
    }
}
