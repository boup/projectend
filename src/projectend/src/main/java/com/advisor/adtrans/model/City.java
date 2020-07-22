package com.advisor.adtrans.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="city")
//public class City extends AuditModel{
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String cityName;
    @Column
    private  Double longitude;

    @Column
    private Double latitude;


//    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Line> line;
//    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Operator> operator;
//    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Ticket> ticket;
//    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<TransCity> transcity;

    public City()  {
    }

    public City(String cityName, Double longitude, Double latitude) {
        this.cityName = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }
//    //public Long getciityID() {
//        return CityID;
//    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        cityName = cityName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setId(Long id) {
        id = id;
    }
//
//    public Set<Line> getLine() {
//        return line;
//    }
//
//    public void setLine(Set<Line> line) {
//        this.line = line;
//    }
//
//    public Set<Operator> getOperator() {
//        return operator;
//    }
//
//    public void setOperator(Set<Operator> operator) {
//        this.operator = operator;
//    }
//
//    public Set<Ticket> getTicket() {
//        return ticket;
//    }
//
//    public void setTicket(Set<Ticket> ticket) {
//        this.ticket = ticket;
//    }
//
//    public Set<TransCity> getTranscity() {
//        return transcity;
//    }
//
//    public void setTranscity(Set<TransCity> transcity) {
//        this.transcity = transcity;
//    }
}
