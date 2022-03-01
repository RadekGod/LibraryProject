package com.projects.libraryproject.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POCZTY")
public class PostEntity {

    @Id
    @Column(name = "NR_POCZTY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(name = "KOD_POCZTOWY", unique = true, nullable = false)
    private String zipCode;

    @Column(name = "MIEJSCOWOSC_POCZTY", nullable = false)
    private String postLocality;

    @Override
    public String
    toString() {
        return "PostEntity{" +
                "postId=" + postId +
                ", zipCode='" + zipCode + '\'' +
                ", postLocality='" + postLocality + '\'' +
                '}';
    }

    public PostEntity(String zipCode, String postLocality) {
        this.zipCode = zipCode;
        this.postLocality = postLocality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity post = (PostEntity) o;
        return postId == post.postId && zipCode.equals(post.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, zipCode);
    }
}
