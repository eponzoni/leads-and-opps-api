package com.datacom.api;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendeesRepository extends CosmosRepository<Attendee, String> {
    Optional<Attendee> findByEmail(String email);
}
