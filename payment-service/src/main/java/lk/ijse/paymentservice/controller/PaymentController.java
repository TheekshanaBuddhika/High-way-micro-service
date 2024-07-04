package lk.ijse.paymentservice.controller;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.dto.ResponseDTO;
import lk.ijse.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
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
