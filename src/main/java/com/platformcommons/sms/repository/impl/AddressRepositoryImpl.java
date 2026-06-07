package com.platformcommons.sms.repository.impl;

import com.platformcommons.sms.entity.Address;
import com.platformcommons.sms.repository.AddressRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Address save(Address address) {
        if (address.getId() == null) {
            entityManager.persist(address);
            return address;
        }

        return entityManager.merge(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }
}
