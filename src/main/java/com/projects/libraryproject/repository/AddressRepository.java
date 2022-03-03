package com.projects.libraryproject.repository;

import com.projects.libraryproject.entity.AddressEntity;
import com.projects.libraryproject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> getAddressEntityByLocalityAndStreetAndHouseNumberAndHouseUnitNumberAndPost(String locality,
                                                                                                       String street,
                                                                                                       int houseNumber,
                                                                                                       int houseUnitNumber,
                                                                                                       PostEntity postId);

}
