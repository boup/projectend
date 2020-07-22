package com.advisor.adtrans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="transcity")

//public class TransCity extends AuditModel {
public class TransCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transId;

    @Column
    private  String link;
    @Column
    private  Long price;
    //private Integer CityID;
    //private  String departure;

    @Column
    private  Enum  typeoftransport;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private City city;
    public TransCity() {
    }

    public TransCity(String link, Long price, Enum typeoftransport) {
        this.link = link;
        this.price = price;
        this.typeoftransport = typeoftransport;
    }

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Enum getTypeoftransport() {
        return typeoftransport;
    }

    public void setTypeoftransport(Enum typeoftransport) {
        this.typeoftransport = typeoftransport;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

