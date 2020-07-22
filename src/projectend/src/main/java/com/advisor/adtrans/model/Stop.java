package com.advisor.adtrans.model;


import com.advisor.adtrans.Node;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="stop")
//public class Stop extends AuditModel {
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column
    private String name;
    @Column
    private  Double longitude;
    @Column
    private Boolean needCheck;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private City city;
    @Column
    private Double Lattitude;


    @ManyToMany(mappedBy = "stops")
    List<Line> likes;

    public Stop(Stop stop) {
    }

    //public Stop(List<Line> likes) {
      //  this.likes = likes;
    //}


    public Stop(String name) {
        this.name = name;
    }

    public Stop(String name, Double longitude, Boolean needCheck, Double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.needCheck = needCheck;
        this.Lattitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLattitude() {
        return Lattitude;
    }

    public void setLattitude(Double lattitude) {
        Lattitude = lattitude;
    }

    public Long getId() {
        return id;
    }




    public Boolean getNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(Boolean needCheck) {
        this.needCheck = needCheck;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return Objects.equals(name, stop.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
