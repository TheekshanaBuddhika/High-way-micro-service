package lk.ijse.ticketservice.service;

import lk.ijse.ticketservice.dto.ResponseDTO;
import lk.ijse.ticketservice.dto.TicketDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketService {
    private final RestTemplate restTemplate;

    public TicketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDTO saveTicket(TicketDTO ticketDTO) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/ticket", ticketDTO, ResponseDTO.class);
    }

    public ResponseDTO disableTicket(Long id) {
        return restTemplate.postForObject("http://persistence-service/api/v1/persistence/ticket/dis/" + id, null, ResponseDTO.class);
    }

    public ResponseDTO getAllTickets() {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/ticket", ResponseDTO.class);
    }

    public ResponseDTO getTicket(Long id) {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/ticket/get/" + id, ResponseDTO.class);
    }

    public ResponseDTO getTicketByStatus(String status) {
        return restTemplate.getForObject("http://persistence-service/api/v1/persistence/ticket/status/" + status, ResponseDTO.class);
    }
}
