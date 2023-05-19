package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.developer.DeveloperService;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.*;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketPriority;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketStatus;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TypeOfTicket;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.AddTicketMapper;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketDetailedMapper;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketSimplifiedMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final AddTicketMapper addTicketMapper;
    private final GetTicketDetailedMapper getTicketDetailedMapper;
    private final GetTicketSimplifiedMapper getTicketSimplifiedMapper;
    private final TicketRepository ticketRepository;
    private final DeveloperService developerService;

    public GetTicketDetailedDto createNewTicket(AddTicketDto addTicketDto) {
        Ticket newTicket = addTicketMapper.map(addTicketDto);
        newTicket.setDateCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        ticketRepository.save(newTicket);
        return getTicketDetailedMapper.map(newTicket);
    }

    public GetTicketDetailedDto fetchTicketById(String ticketId) {
        Ticket ticket = fetchTicketIfItExists(ticketId);
        return getTicketDetailedMapper.map(ticket);
    }

    public List<GetTicketDetailedDto> fetchAllTickets() {
        return ticketRepository.findAll().stream().map(getTicketDetailedMapper::map).toList();
    }

    public List<GetTicketSimplifiedDto> fetchTicketsByAuthor(String authorId) throws EntityNotFoundException {
        developerService.checkIfDeveloperIdExists(authorId);
        return ticketRepository.findByAuthorId(authorId).stream().map(getTicketSimplifiedMapper::map).toList();
    }

    public List<GetTicketSimplifiedDto> fetchTicketsAssignedToDev(String developerId) throws EntityNotFoundException {
        developerService.checkIfDeveloperIdExists(developerId);
        return ticketRepository.findByDevsAssignedId(developerId).stream().map(getTicketSimplifiedMapper::map).toList();
    }

    public List<Comment> fetchCommentsForTicket(String ticketId) throws EntityNotFoundException{
        Ticket ticket = fetchTicketIfItExists(ticketId);
        return ticket.getComments();
    }

    public GetTicketDetailedDto updateSingleFieldOfATicket(String id, UpdateTicketSingleFieldDto updateTicketSingleFieldDto) throws EntityNotFoundException, NoSuchFieldException, ClassCastException {
        Ticket ticketToBeUpdated = fetchTicketIfItExists(id);
        switch (updateTicketSingleFieldDto.getFieldName()) {
            case "title":
                ticketToBeUpdated.setTitle((String) updateTicketSingleFieldDto.getFieldValue());
                break;
            case "description":
                ticketToBeUpdated.setDescription((String) updateTicketSingleFieldDto.getFieldValue());
                break;
            case "typeOfTicket":
                ticketToBeUpdated.setTypeOfTicket(Enum.valueOf(TypeOfTicket.class, (String) updateTicketSingleFieldDto.getFieldValue()));
                break;
            case "ticketPriority":
                ticketToBeUpdated.setTicketPriority(Enum.valueOf(TicketPriority.class, (String) updateTicketSingleFieldDto.getFieldValue()));
                break;
            case "ticketStatus":
                ticketToBeUpdated.setTicketStatus(Enum.valueOf(TicketStatus.class, (String) updateTicketSingleFieldDto.getFieldValue()));
                break;
            case "devsAssigned":
                if (updateTicketSingleFieldDto.getFieldValue() instanceof List) {
                    ticketToBeUpdated.setDevsAssigned(developerService.fetchAllDevelopersByIdList((List<String>) updateTicketSingleFieldDto.getFieldValue()));
                } else {
                    throw new ClassCastException("The data type of the list of the ids provided is inappropriate. A list of strings is needed.");
                }
                break;
            default:
                throw new NoSuchFieldException("Invalid field name, or specified field is not to be modified.");
        }
        ticketToBeUpdated.setLastDateModified(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return getTicketDetailedMapper.map(ticketRepository.save(ticketToBeUpdated));
    }

    public GetTicketDetailedDto updateMultipleFieldsOfATicket(String id, UpdateTicketMultipleFieldsDto updateTicketMultipleFieldsDto) throws EntityNotFoundException {
        Ticket ticketToBeUpdated = fetchTicketIfItExists(id);
        ticketToBeUpdated.setTitle(updateTicketMultipleFieldsDto.getTitle());
        ticketToBeUpdated.setDescription(updateTicketMultipleFieldsDto.getDescription());
        ticketToBeUpdated.setTypeOfTicket(Enum.valueOf(TypeOfTicket.class, updateTicketMultipleFieldsDto.getTypeOfTicket()));
        ticketToBeUpdated.setTicketPriority(Enum.valueOf(TicketPriority.class, updateTicketMultipleFieldsDto.getTicketPriority()));
        ticketToBeUpdated.setTicketStatus(Enum.valueOf(TicketStatus.class, updateTicketMultipleFieldsDto.getTicketStatus()));
        ticketToBeUpdated.setDevsAssigned(developerService.fetchAllDevelopersByIdList(updateTicketMultipleFieldsDto.getDevsAssigned()));
        ticketToBeUpdated.setLastDateModified(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return getTicketDetailedMapper.map(ticketRepository.save(ticketToBeUpdated));
    }

    public void deleteTicket(String id) throws EntityNotFoundException{
        ticketRepository.delete(fetchTicketIfItExists(id));
    }

    private Ticket fetchTicketIfItExists(String id) throws EntityNotFoundException {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
            throw new EntityNotFoundException("Ticket with id '" + id + "' not found.");
        }
    }
}