package com.projects.libraryproject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "BIBLIOTEKI")
public class LibraryEntity {

    @Id
    @Column(name = "NR_BIBLIOTEKI")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long libraryId;

    @Column(name = "NR_TELEFONU")
    private String phoneNumber;

    @Column(name = "DATA_ZALOZENIA")
    private Date creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NR_ADRESU")
    private AddressEntity address;

    @Override
    public String toString() {
        return "LibraryEntity{" +
                "id=" + libraryId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creationDate=" + creationDate +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryEntity that = (LibraryEntity) o;
        return Objects.equals(libraryId, that.libraryId);
    }

    public LibraryEntity(String phoneNumber, Date creationDate) {
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryId);
    }
}
