package br.com.kaikeventura.jmeter.service;

import br.com.kaikeventura.jmeter.model.PaymentMethod;
import br.com.kaikeventura.jmeter.model.ProductSessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PayService {

    private final HttpSession session;

    public Map<String, Boolean> payValidate(PaymentMethod paymentMethod) throws Exception {
        ProductSessionData productSessionData = (ProductSessionData) session.getAttribute("productSessionData");
        PaymentMethod paymentMethodSession = (PaymentMethod) session.getAttribute("paymentMethod");
        if(!paymentMethod.getPaymentID().equals(paymentMethodSession.getPaymentID())) {
            throw new Exception("Error");
        }
        if(!productSessionData.getSessionID().equals(session.getId())) {
            throw new Exception("Error");
        }
        Map<String, Boolean> success = new HashMap<>();
        success.put("success", true);
        return success;
    }
}
