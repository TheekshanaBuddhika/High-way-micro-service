package lk.ijse.pesistanceservice.controller;

import lk.ijse.pesistanceservice.dto.PaymentDTO;
import lk.ijse.pesistanceservice.dto.ResponseDTO;
import lk.ijse.pesistanceservice.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persistence/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseDTO savePayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            return paymentService.savePayment(paymentDTO);
        }catch (Exception e){
            return new ResponseDTO("Internal Server Error", 500);
        }
    }
}
