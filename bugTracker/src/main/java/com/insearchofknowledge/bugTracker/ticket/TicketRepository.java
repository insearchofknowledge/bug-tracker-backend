package com.insearchofknowledge.bugTracker.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, String> {

    List<Ticket> findByAuthorId(String authorId);

    List<Ticket> findByDevsAssignedId(String id);
}
