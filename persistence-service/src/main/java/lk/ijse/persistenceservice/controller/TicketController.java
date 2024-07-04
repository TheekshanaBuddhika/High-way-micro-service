package lk.ijse.pesistanceservice.controller;

import lk.ijse.pesistanceservice.dto.ResponseDTO;
import lk.ijse.pesistanceservice.dto.TicketDTO;
import lk.ijse.pesistanceservice.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persistence/ticket")
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
        try {
            return ticketService.getTicket(id);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseDTO getTicketsByStatus(@PathVariable String status) {
        try {
            return ticketService.getTicketByStatus(status);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }
}
