package com.projects.libraryproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "KSIAZKI")
public class BookEntity {

    @Id
    @Column(name = "NR_KSIAZKI")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(name = "TYTUL")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "KSIAZKI_GATUNKI",
            joinColumns = {@JoinColumn(name = "NR_KSIAZKI")},
            inverseJoinColumns = {@JoinColumn(name = "NR_GATUNKU")}
    )
    private List<TypeEntity> types;

    @ManyToMany
    @JoinTable(
            name = "KSIAZKI_AUTORZY",
            joinColumns = {@JoinColumn(name = "NR_KSIAZKI")},
            inverseJoinColumns = {@JoinColumn(name = "NR_AUTORA")}
    )
    private List<AuthorEntity> authors;

    //@ManyToMany
    //private List<InstitutionEntity> institutions;

    @Column(name = "WYDAWNICTWO")
    private String  publisher;

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

}
