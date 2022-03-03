package com.projects.libraryproject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "ADRESY")
public class AddressEntity {

    @Id
    @Column(name = "NR_ADRESU")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @Column(name = "MIEJSCOWOSC", nullable = false)
    private String locality;

    @Column(name = "ULICA")
    private String  street;

    @Column(name = "NR_DOMU", nullable = false)
    private int  houseNumber;

    @Column(name = "NR_LOKALU")
    private int  houseUnitNumber;

    @OneToOne
    @JoinColumn(name = "NR_POCZTY")
    private PostEntity post;

    @Override
    public String toString() {
        return "AddressEntity{" +
                "addressId=" + addressId +
                ", locality='" + locality + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", houseUnitNumber=" + houseUnitNumber +
                ", postEntity=" + post +
                '}';
    }

    public AddressEntity(String locality, String street, int houseNumber, int houseUnitNumber, PostEntity post) {
        this.locality = locality;
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseUnitNumber = houseUnitNumber;
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity address = (AddressEntity) o;
        return addressId == address.addressId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}
