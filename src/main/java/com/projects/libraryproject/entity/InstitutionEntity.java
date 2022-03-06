package com.projects.libraryproject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "PLACOWKI")
public class InstitutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_PLACOWKI")
    private long institutionId;

    @Column(name = "NR_TELEFONU")
    private String phoneNumber;

    @Column(name = "E_MAIL")
    private String email;

    //@ManyToMany(mappedBy = "institutions")
    //private List<BookEntity> books;

    @ManyToOne
    @JoinColumn(name = "NR_BIBLIOTEKI")
    private LibraryEntity library;

    @OneToOne
    @JoinColumn(name = "NR_ADRESU")
    private AddressEntity address;

}
