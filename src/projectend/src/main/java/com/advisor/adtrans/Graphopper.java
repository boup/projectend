package com.advisor.adtrans;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.GraphHopperConfig;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.CarFlagEncoder;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FootFlagEncoder;

public class Graphopper {

    private GraphHopper hopper;

    public Graphopper() {

        hopper = new GraphHopperOSM().init(
                new GraphHopperConfig().putObject("prepare.min_network_size", "600")
                        .putObject("prepare.min_one_way_network_size", "600")
                        .putObject("index.max_region_search", "12")
                        .putObject("datareader.file", "dakar.osm")
                        .putObject("graph.location", "GraphHopper"))
                .forServer()
                .setEncodingManager(EncodingManager.create(new FootFlagEncoder(), new CarFlagEncoder()))
                .importOrLoad();

        hopper.setProfiles(

                new Profile("my_foot_profile").setVehicle("foot").setWeighting("shortest"),

                new Profile("my_car_profile").setVehicle("car").setWeighting("shortest")

        );

    }

    public double countDistanceInMetersByCar(double lat1, double lon1, double lat2, double lon2) {
        GHRequest req = new GHRequest(lat1, lon1, lat2, lon2);
        req.setProfile("my_car_profile");
        GHResponse rsp = hopper.route(req);
        if(rsp.getAll().size() > 0)
        {
            return rsp.getBest().getDistance();
        } else
            return 0;
    }

    public long countTimeInMetersByCar(double lat1, double lon1, double lat2, double lon2) {
        GHRequest req = new GHRequest(lat1, lon1, lat2, lon2);
        req.setProfile("my_car_profile");
        GHResponse rsp = hopper.route(req);
        if(rsp.getAll().size() > 0) {
            return rsp.getBest().getTime();
        } else {
            return 0;
        }
    }

    public double countDistanceInMetersOnFoot(double lat1, double lon1, double lat2, double lon2) {
        GHRequest req = new GHRequest(lat1, lon1, lat2, lon2);
        req.setProfile("my_foot_profile");
        GHResponse rsp = hopper.route(req);
        if(rsp.getAll().size() > 0)
        {
            return rsp.getBest().getDistance();
        } else
            return 0;
    }

    public long countTimeOnFoot(double lat1, double lon1, double lat2, double lon2) {
        GHRequest req = new GHRequest(lat1, lon1, lat2, lon2);
        req.setProfile("my_foot_profile");
        GHResponse rsp = hopper.route(req);
        if(rsp.getAll().size() > 0) {
            return rsp.getBest().getTime();
        } else {
            return 0;
        }
    }
}

