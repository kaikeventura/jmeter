package br.com.kaikeventura.jmeter.service;

import br.com.kaikeventura.jmeter.model.Product;
import br.com.kaikeventura.jmeter.model.PaymentDetail;
import br.com.kaikeventura.jmeter.model.ProductDetail;
import br.com.kaikeventura.jmeter.model.ProductSessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final HttpSession session;

    public PaymentDetail getDataPurchase(Product product) {
        return saveInSession(product);
    }

    private PaymentDetail saveInSession(Product product) {
        ProductSessionData productSessionData = new ProductSessionData();
        productSessionData.setId(product.getId());
        productSessionData.setName(product.getName());
        productSessionData.setPrice(product.getPrice());
        productSessionData.setToken(UUID.randomUUID().toString());
        productSessionData.setSessionID(session.getId());
        productSessionData.setPaymentDate(LocalDate.now());
        productSessionData.setPaymentID(UUID.randomUUID().toString().substring(0, 12));
        session.setAttribute("productSessionData", productSessionData);

        return buildPaymentDetail(productSessionData);
    }

    private PaymentDetail buildPaymentDetail(ProductSessionData productSessionData) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setName(productSessionData.getName());
        productDetail.setPrice(productSessionData.getPrice());

        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setPaymentDate(productSessionData.getPaymentDate());
        paymentDetail.setProductDetail(productDetail);
        paymentDetail.setPaymentID(productSessionData.getPaymentID());

        return paymentDetail;
    }
}
