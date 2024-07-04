package lk.ijse.pesistanceservice.service;

import lk.ijse.pesistanceservice.dto.ResponseDTO;
import lk.ijse.pesistanceservice.dto.TicketDTO;
import lk.ijse.pesistanceservice.entity.TicketEntity;
import lk.ijse.pesistanceservice.repository.TicketRepository;
import lk.ijse.pesistanceservice.repository.UserRepository;
import lk.ijse.pesistanceservice.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseDTO saveTicket(TicketDTO ticketDTO) {
        try {
            ticketRepository.save(TicketEntity.builder()
                    .userEntity(userRepository.findById(ticketDTO.getUserId()).orElse(null))
                    .vehicleEntity(vehicleRepository.findById(ticketDTO.getVehicleId()).orElse(null))
                    .issueDate(Date.valueOf(LocalDate.now()))
                    .issueLocation(ticketDTO.getIssueLocation())
                    .issueTime(LocalTime.now())
                    .status("PENDING")
                    .createBy(ticketDTO.getCreateBy())
                    .isActive(true)
                    .build());
            return new ResponseDTO("Ticket Saved Successfully", 200);
        }catch (Exception e){
            return new ResponseDTO("Failed to Save Ticket", 500);
        }
    }

    public void payTicket(TicketDTO ticketDTO) {
        try {
            TicketEntity ticketEntity = ticketRepository.findById(ticketDTO.getId()).orElse(null);
            if (ticketEntity != null){
                ticketEntity.setArrivalDate(ticketDTO.getArrivalDate());
                ticketEntity.setArrivalLocation(ticketDTO.getArrivalLocation());
                ticketEntity.setArrivalTime(LocalTime.parse(ticketDTO.getArrivalTime()));
                ticketEntity.setStatus(ticketDTO.getStatus());
                ticketEntity.setModifyBy(ticketDTO.getModifyBy());
                ticketRepository.save(ticketEntity);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseDTO disableTicket(Long id) {
        try {
            TicketEntity ticketEntity = ticketRepository.findById(id).orElse(null);
            if (ticketEntity != null){
                ticketEntity.setActive(false);
                ticketRepository.save(ticketEntity);
                return new ResponseDTO("Ticket Disabled Successfully", 200);
            }else {
                return new ResponseDTO("Ticket Not Found", 400);
            }
        }catch (Exception e){
            return new ResponseDTO("Failed to Disable Ticket", 500);
        }
    }

    public ResponseDTO getAllTickets() {
        try {
            return new ResponseDTO("All Tickets", 200, new HashMap<>(Map.of("tickets", ticketRepository.findAll().stream().map(ticketEntity -> TicketDTO
                    .builder()
                    .id(ticketEntity.getId())
                    .issueDate(ticketEntity.getIssueDate())
                    .issueLocation(ticketEntity.getIssueLocation())
                    .issueTime(ticketEntity.getIssueTime().toString())
                    .arrivalDate(ticketEntity.getArrivalDate() != null ? ticketEntity.getArrivalDate() : null)
                    .arrivalLocation(ticketEntity.getArrivalLocation() != null ? ticketEntity.getArrivalLocation() : null)
                    .arrivalTime(ticketEntity.getArrivalTime() != null ? ticketEntity.getArrivalTime().toString() : null)
                    .status(ticketEntity.getStatus())
                    .userId(ticketEntity.getUserEntity().getId())
                    .vehicleId(ticketEntity.getVehicleEntity().getId())
                    .createBy(ticketEntity.getCreateBy())
                    .modifyBy(ticketEntity.getModifyBy())
                    .isActive(ticketEntity.isActive())
                    .build()).toList())));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO("Failed to Get All Tickets", 500);
        }
    }

    public ResponseDTO getTicket(Long id) {
        try {
            TicketEntity ticketEntity = ticketRepository.findById(id).orElse(null);
            if (ticketEntity != null){
                return new ResponseDTO("Ticket", 200, new HashMap<>(Map.of("ticket", TicketDTO.builder()
                        .id(ticketEntity.getId())
                        .issueDate(ticketEntity.getIssueDate())
                        .issueLocation(ticketEntity.getIssueLocation())
                        .issueTime(ticketEntity.getIssueTime().toString())
                        .arrivalDate(ticketEntity.getArrivalDate() != null ? ticketEntity.getArrivalDate() : null)
                        .arrivalLocation(ticketEntity.getArrivalLocation() != null ? ticketEntity.getArrivalLocation() : null)
                        .arrivalTime(ticketEntity.getArrivalTime() != null ? ticketEntity.getArrivalTime().toString() : null)
                        .status(ticketEntity.getStatus())
                        .userId(ticketEntity.getUserEntity().getId())
                        .vehicleId(ticketEntity.getVehicleEntity().getId())
                        .createBy(ticketEntity.getCreateBy())
                        .modifyBy(ticketEntity.getModifyBy())
                        .isActive(ticketEntity.isActive())
                        .build())));
            }else {
                return new ResponseDTO("Ticket Not Found", 400);
            }
        }catch (Exception e){
            return new ResponseDTO("Failed to Get Ticket", 500);
        }
    }

    public ResponseDTO getTicketByStatus(String status) {
        try {
            return new ResponseDTO("Tickets", 200, new HashMap<>(Map.of("tickets", ticketRepository.findByStatus(status).stream().map(ticketEntity -> TicketDTO
                    .builder()
                    .id(ticketEntity.getId())
                    .issueDate(ticketEntity.getIssueDate())
                    .issueLocation(ticketEntity.getIssueLocation())
                    .issueTime(ticketEntity.getIssueTime().toString())
                    .arrivalDate(ticketEntity.getArrivalDate() != null ? ticketEntity.getArrivalDate() : null)
                    .arrivalLocation(ticketEntity.getArrivalLocation() != null ? ticketEntity.getArrivalLocation() : null)
                    .arrivalTime(ticketEntity.getArrivalTime() != null ? ticketEntity.getArrivalTime().toString() : null)
                    .status(ticketEntity.getStatus())
                    .userId(ticketEntity.getUserEntity().getId())
                    .vehicleId(ticketEntity.getVehicleEntity().getId())
                    .createBy(ticketEntity.getCreateBy())
                    .modifyBy(ticketEntity.getModifyBy())
                    .isActive(ticketEntity.isActive())
                    .build()).toList())));
        }catch (Exception e){
            return new ResponseDTO("Failed to Get Tickets", 500);
        }
    }
}

