package com.projects.libraryproject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "GATUNKI")
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_GATUNKU")
    private long typeId;

    @Column(name = "NAZWA_GATUNKU")
    private String typeName;

    @ManyToMany(mappedBy = "types")
    private List<BookEntity> books;


    @Override
    public String toString() {
        return "TypeEntity{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeEntity type = (TypeEntity) o;
        return Objects.equals(typeName, type.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }

    public TypeEntity(String typeName) {
        this.typeName = typeName;
    }

}
