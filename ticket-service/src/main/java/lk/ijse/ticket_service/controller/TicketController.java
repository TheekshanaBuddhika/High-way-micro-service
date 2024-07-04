package lk.ijse.ticket_service.controller;

import lk.ijse.ticket_service.dto.ResponseDTO;
import lk.ijse.ticket_service.dto.TicketDTO;
import lk.ijse.ticket_service.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseDTO saveTicket(@RequestBody TicketDTO ticketDTO) {
        try {
            return ticketService.saveTicket(ticketDTO);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @PostMapping("/dis/{id}")
    public ResponseDTO disableTicket(@PathVariable Long id) {
        try {
            return ticketService.disableTicket(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping
    public ResponseDTO getAllTickets() {
        try {
            return ticketService.getAllTickets();
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getTicket(@PathVariable Long id) {
        System.out.println("ticket service get ticket by id");
        try {
            return ticketService.getTicket(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseDTO getTicketByStatus(@PathVariable String status) {
        try {
            return ticketService.getTicketByStatus(status);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }
}
