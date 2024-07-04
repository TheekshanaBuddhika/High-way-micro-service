package lk.ijse.pesistanceservice.service;

import lk.ijse.pesistanceservice.dto.PaymentDTO;
import lk.ijse.pesistanceservice.dto.ResponseDTO;
import lk.ijse.pesistanceservice.dto.TicketDTO;
import lk.ijse.pesistanceservice.entity.PaymentEntity;
import lk.ijse.pesistanceservice.entity.TicketEntity;
import lk.ijse.pesistanceservice.repository.PaymentRepository;
import lk.ijse.pesistanceservice.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class PaymentService {
    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;
    private final TicketService ticketService;

    public PaymentService(TicketRepository ticketRepository, PaymentRepository paymentRepository, TicketService ticketService) {
        this.ticketRepository = ticketRepository;
        this.paymentRepository = paymentRepository;
        this.ticketService = ticketService;
    }

    public ResponseDTO savePayment(PaymentDTO paymentDTO) {
        try {
            if (ticketRepository.existsById(paymentDTO.getTicketId())) {
                TicketEntity ticketEntity = ticketRepository.findById(paymentDTO.getTicketId()).orElse(null);

                PaymentEntity save = paymentRepository.save(PaymentEntity
                        .builder()
                        .cashierName(paymentDTO.getCashierName())
                        .cashierId(paymentDTO.getCashierId())
                        .chargePerKm(paymentDTO.getChargePerKm())
                        .totalKm(paymentDTO.getTotalKm())
                        .totalAmount(paymentDTO.getTotalAmount())
                        .ticket(ticketEntity)
                        .createBy(paymentDTO.getCreateBy())
                        .isActive(true)
                        .build());
                System.out.println("save payment ");
                if (save != null) {
                    ticketService.payTicket(TicketDTO
                            .builder()
                            .id(paymentDTO.getTicketId())
                            .status("PAID")
                            .arrivalDate(Date.valueOf(LocalDate.now()))
                            .arrivalTime(String.valueOf(LocalTime.now()))
                            .arrivalLocation(paymentDTO.getPayedLocation())
                            .build());
                    return new ResponseDTO("Payment Saved Successfully", 200);
                } else {
                    return new ResponseDTO("Failed to Save Payment", 500);
                }
            } else {
                return new ResponseDTO("Ticket Not Found", 400);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO("Failed to Save Payment", 500);
        }
    }
}
