package com.apurba.airline.Repository;

import com.apurba.airline.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySaleDate(LocalDate saleDate);
    List<Ticket> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
}

