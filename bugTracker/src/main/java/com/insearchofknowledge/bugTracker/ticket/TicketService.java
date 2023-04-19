package com.insearchofknowledge.bugTracker.ticket;

import com.insearchofknowledge.bugTracker.comment.Comment;
import com.insearchofknowledge.bugTracker.developer.DeveloperService;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.AddTicketDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.GetTicketDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.UpdateTicketMultipleFieldsDto;
import com.insearchofknowledge.bugTracker.ticket.ticketDto.UpdateTicketSingleFieldDto;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketPriority;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TicketStatus;
import com.insearchofknowledge.bugTracker.ticket.ticketEnum.TypeOfTicket;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.AddTicketMapper;
import com.insearchofknowledge.bugTracker.ticket.ticketMapper.GetTicketMapper;
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
    private final GetTicketMapper getTicketMapper;
    private final TicketRepository ticketRepository;
    private final DeveloperService developerService;

    public GetTicketDto createNewTicket(AddTicketDto addTicketDto) {
        Ticket newTicket = addTicketMapper.map(addTicketDto);
        newTicket.setDateCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        // we persist the ticket (before we add it to the list of the ticketsCreated by the author)
        ticketRepository.save(newTicket);
        // we add the newly created ticket to the list of the created tickets by this developer (author)
        //developerService.updateTicketsCreated(newTicket.getAuthor().getId(),newTicket);
        return getTicketMapper.map(newTicket);
    }

    public GetTicketDto fetchTicketById(String ticketId) {
        Ticket ticket = fetchTicketIfItExists(ticketId);
        return getTicketMapper.map(ticket);
    }

    public List<GetTicketDto> fetchAllTickets() {
        return ticketRepository.findAll().stream().map(getTicketMapper::map).toList();
    }

    public List<Comment> fetchCommentsForTicket(String ticketId) {
        Ticket ticket = fetchTicketIfItExists(ticketId);
        List<Comment> comments = ticket.getComments();
        return comments;
    }

//    public GetTicketDto editSingleField(String id, UpdateTicketSingleFieldDto updateTicketDto) throws Exception {
//        Ticket ticketToBeUpdated = getTicketIfItExists(id);
//        String fieldName = updateTicketDto.getFieldName();
//        Object fieldValue = updateTicketDto.getFieldValue();
//        try {
//            Field field = ticketToBeUpdated.getClass().getDeclaredField(fieldName);
//            field.setAccessible(true);
//            switch (fieldName) {
//                case "typeOfTicket":
//                    field.set(ticketToBeUpdated,TypeOfTicket.values()[(int)fieldValue]);
//                    field.setAccessible(false);
//                    break;
//                case "ticketPriority":
//                    field.set(ticketToBeUpdated, TicketPriority.values()[(int)fieldValue]);
//                    field.setAccessible(false);
//                    break;
//                case "ticketStatus":
//                    field.set(ticketToBeUpdated, TicketStatus.values()[(int)fieldValue]);
//                    field.setAccessible(false);
//                    break;
//                case "devsAssigned":
//                    field.set(ticketToBeUpdated, developerService.getAllDevelopersByIdLis((List<String>)fieldValue));
//                    break;
//                case "title":
//                    field.set(ticketToBeUpdated, fieldValue);
//                    break;
//                case"description":
//                    field.set(ticketToBeUpdated, fieldValue);
//                    break;
//                default:
//                    throw new NoSuchFieldException("Invalid field name, or specified field is not to be modified");
//            }
//
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//        ticketToBeUpdated.setLastDateModified(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
//        return getTicketMapper.map(ticketRepository.save(ticketToBeUpdated));
//    }

    public GetTicketDto updateSingleFieldOfATicket(String id, UpdateTicketSingleFieldDto updateTicketSingleFieldDto) throws EntityNotFoundException, NoSuchFieldException, ClassCastException {
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
                    throw new ClassCastException("The data type of the list of the ids provided is inappropriate. A list of strings is needed");
                }
                break;
            default:
                throw new NoSuchFieldException("Invalid field name, or specified field is not to be modified");
        }
        ticketToBeUpdated.setLastDateModified(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return getTicketMapper.map(ticketRepository.save(ticketToBeUpdated));
    }

    public GetTicketDto updateMultipleFieldsOfATicket(String id, UpdateTicketMultipleFieldsDto updateTicketMultipleFieldsDto) throws EntityNotFoundException {
        Ticket ticketToBeUpdated = fetchTicketIfItExists(id);
        ticketToBeUpdated.setTitle(updateTicketMultipleFieldsDto.getTitle());
        ticketToBeUpdated.setDescription(updateTicketMultipleFieldsDto.getDescription());
        ticketToBeUpdated.setTypeOfTicket(Enum.valueOf(TypeOfTicket.class, updateTicketMultipleFieldsDto.getTypeOfTicket()));
        ticketToBeUpdated.setTicketPriority(Enum.valueOf(TicketPriority.class, updateTicketMultipleFieldsDto.getTicketPriority()));
        ticketToBeUpdated.setTicketStatus(Enum.valueOf(TicketStatus.class, updateTicketMultipleFieldsDto.getTicketStatus()));
        ticketToBeUpdated.setDevsAssigned(developerService.fetchAllDevelopersByIdList(updateTicketMultipleFieldsDto.getDevsAssigned()));
        ticketToBeUpdated.setLastDateModified(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        return getTicketMapper.map(ticketRepository.save(ticketToBeUpdated));
    }

    public void deleteTicket(String id) {
        ticketRepository.delete(fetchTicketIfItExists(id));
    }

    private Ticket fetchTicketIfItExists(String id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
            throw new EntityNotFoundException("Ticket with id '" + id + "' not found");
        }
    }
}
