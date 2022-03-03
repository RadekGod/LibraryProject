package com.projects.libraryproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UZYTKOWNICY")
public class UserEntity {

    @Id
    @Column(name = "NR_UZYTKOWNIKA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "IMIE", nullable = false)
    private String firstName;

    @Column(name = "NAZWISKO", nullable = false)
    private String lastName;

    @Column(name = "E_MAIL", nullable = false, unique = true)
    private String  email;

    @Column(name = "HASLO", nullable = false)
    private String  password;

    @Column(name = "SALDO", nullable = false)
    private Integer balance;

    @ManyToOne
    @JoinColumn(name = "NR_BIBLIOTEKI")
    private LibraryEntity library;

    @ManyToOne
    @JoinColumn(name = "NR_ADRESU")
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "NR_ROLI")
    private RoleEntity role;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", library=" + library +
                ", address=" + address +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return userId == user.userId && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }
}
