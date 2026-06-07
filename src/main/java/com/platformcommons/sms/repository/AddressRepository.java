package com.platformcommons.sms.repository;

import com.platformcommons.sms.entity.Address;

import java.util.Optional;

public interface AddressRepository {

    Address save(Address address);

    Optional<Address> findById(Long id);
}
