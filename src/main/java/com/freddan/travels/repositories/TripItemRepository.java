package com.freddan.travels.repositories;

import com.freddan.travels.entities.TripItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripItemRepository extends JpaRepository<TripItem, Long> {
}
