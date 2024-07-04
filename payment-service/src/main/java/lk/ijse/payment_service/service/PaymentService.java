package lk.ijse.payment_service.service;

import lk.ijse.payment_service.dto.PaymentDTO;
import lk.ijse.payment_service.dto.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseDTO savePayment(PaymentDTO paymentDTO) {
        double total = paymentDTO.getChargePerKm() * paymentDTO.getTotalKm();

        if (total == paymentDTO.getTotalAmount()){
            return restTemplate.postForObject("http://persistence-service/api/v1/persistence/payment", paymentDTO, ResponseDTO.class);
        }else {
            return new ResponseDTO("Total Amount is not correct", 400);
        }
    }
}
