package dev.patricksilva.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patricksilva.model.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}