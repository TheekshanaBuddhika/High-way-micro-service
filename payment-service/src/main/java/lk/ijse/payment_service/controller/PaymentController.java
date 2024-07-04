package lk.ijse.payment_service.controller;

import lk.ijse.payment_service.dto.PaymentDTO;
import lk.ijse.payment_service.dto.ResponseDTO;
import lk.ijse.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.*;

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
