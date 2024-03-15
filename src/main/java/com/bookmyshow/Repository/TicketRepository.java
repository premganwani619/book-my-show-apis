package com.bookmyshow.Repository;

import com.bookmyshow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

}
