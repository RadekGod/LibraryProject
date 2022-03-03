package com.projects.libraryproject.entity;


import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "WYDAWNICTWA")
public class PublishingHouseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private long publishingHouseId;

    @Column(name = "NAZWA")
    private String name;

    @Column(name = "DATA_ZALOZENIA")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "NR_ADRESU")
    private AddressEntity address;
}
