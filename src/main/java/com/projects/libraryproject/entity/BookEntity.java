package com.projects.libraryproject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "KSIAZKI")
public class BookEntity {

    @Id
    @Column(name = "NR_KSIAZKI")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(name = "TYTUL")
    private String title;

    @Column(name = "DATA_WYDANIA")
    private Date releaseDate;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "LICZBA_STRON")
    private int pages;

    @Column(name = "TYP_OKLADKI")
    private String coverType;

    @Column(name = "OPIS")
    private String  description;

    @ManyToOne
    @JoinColumn(name = "NR_BIBLIOTEKI")
    private LibraryEntity library;

    @ManyToOne
    private PublishingHouseEntity publishingHouse;
}
