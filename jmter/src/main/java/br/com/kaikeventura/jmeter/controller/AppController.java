package br.com.kaikeventura.jmeter.controller;

import br.com.kaikeventura.jmeter.model.PaymentMethod;
import br.com.kaikeventura.jmeter.service.PaymentMethodService;
import br.com.kaikeventura.jmeter.model.PaymentDetail;
import br.com.kaikeventura.jmeter.model.Product;
import br.com.kaikeventura.jmeter.service.PayService;
import br.com.kaikeventura.jmeter.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AppController {

    private final PurchaseService purchaseService;
    private final PaymentMethodService paymentMethodService;
    private final PayService payService;
    private final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @PostMapping("purchase")
    public ResponseEntity<PaymentDetail> purchase(@RequestBody Product product) {
        LOG.info("Performing in purchase: ", product);
        PaymentDetail paymentDetail = purchaseService.getDataPurchase(product);
        return ResponseEntity.ok(paymentDetail);
    }

    @PostMapping("payment/method")
    public ResponseEntity<PaymentMethod> paymentMethod(@RequestBody PaymentMethod paymentMethod) throws Exception {
        LOG.info("Performing in payment method: ", paymentMethod);
        try {
            return ResponseEntity.ok(paymentMethodService.getPaymentMethod(paymentMethod));
        }
        catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @PostMapping("pay")
    public ResponseEntity<Map<String, Boolean>> pay(@RequestBody PaymentMethod paymentMethod) throws Exception {
        LOG.info("Performing in pay: ", paymentMethod);
        try {
            return ResponseEntity.ok(payService.payValidate(paymentMethod));
        }
        catch (Exception e) {
            throw new Exception(e.getCause());
        }
    }

}