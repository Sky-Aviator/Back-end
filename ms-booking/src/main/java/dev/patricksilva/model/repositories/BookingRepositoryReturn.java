package dev.patricksilva.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patricksilva.model.entities.BookingReturn;

@Repository
public interface BookingRepositoryReturn  extends JpaRepository<BookingReturn, Long>{
}