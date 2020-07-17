package com.advisor.adtrans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="line")
//public class Line extends AuditModel {
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long lineId;

    @Column
    private String name;
   // @Column(name="stops")
   // @OneToMany(fetch)
    //private List<Stop> stops;
    @ManyToMany
    @JoinTable(
            name = "stopline",
            joinColumns = @JoinColumn(name = "lineId"),
            inverseJoinColumns = @JoinColumn(name = "stopId"))
    List<Stop> stops;
    @Column
    private String link;

    @Column
    private  String addStop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private City city;


    public Line() {
    }
    public Line(String aName) {
        name = aName;
        stops = new ArrayList<Stop>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStop(Stop name) {
        stops.add(name);
    }
    public List<Stop> getStops() {
        return stops;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
//    //public Long getLineID() {
//        return LineID;
//    }



    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
