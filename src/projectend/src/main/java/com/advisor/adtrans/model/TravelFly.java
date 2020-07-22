package com.advisor.adtrans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="travelfly")
//public class TravelFly extends AuditModel {
public class TravelFly{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avionId;

    @Column
    private  Long price;
    @Column
    private String company;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private City city;

    public TravelFly() {
    }

    public TravelFly(Long price, String company) {
        this.price = price;
        this.company = company;
    }

    public Long getAvionId() {
        return avionId;
    }

    public void setAvionId(Long avionId) {
        this.avionId = avionId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
