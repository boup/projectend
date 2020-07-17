package com.advisor.adtrans;

import java.util.Objects;

public class Edge {

    private Long nodeOutId;
    private Long nodeInId;
    private Long edgeWeight;
    private Double distance;
    private EdgeType type;
    private String lineName;

    public Edge(Long nodeOutId, Long nodeInId, Long edgeWeight, EdgeType type, String lineName) {
        this.nodeOutId = nodeOutId;
        this.nodeInId = nodeInId;
        this.edgeWeight = edgeWeight;
        this.type = type;
        this.lineName  = lineName;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getNodeOutId() {
        return nodeOutId;
    }

    public void setNodeOutId(Long nodeOutId) {
        this.nodeOutId = nodeOutId;
    }

    public Long getNodeInId() {
        return nodeInId;
    }

    public void setNodeInId(Long nodeInId) {
        this.nodeInId = nodeInId;
    }

    public Long getEdgeWeight() {
        return edgeWeight;
    }

    public void setEdgeWeight(Long edgeWeight) {
        this.edgeWeight = edgeWeight;
    }


    public EdgeType getType() {
        return type;
    }

    public void setType(EdgeType type) {
        this.type = type;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(nodeOutId, edge.nodeOutId) &&
                Objects.equals(nodeInId, edge.nodeInId) &&
                Objects.equals(lineName, edge.lineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeOutId, nodeInId, lineName);
    }
}
