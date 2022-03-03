package com.projects.libraryproject.service;

import com.projects.libraryproject.entity.AddressEntity;
import com.projects.libraryproject.entity.UserEntity;

import java.util.List;

public interface AddressService {

    AddressEntity saveAddress(AddressEntity address);

    List<AddressEntity> getAllAddressesWithIds(List<Long> ids);
}
