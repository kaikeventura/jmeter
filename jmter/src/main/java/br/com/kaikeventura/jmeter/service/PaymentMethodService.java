package br.com.kaikeventura.jmeter.service;

import br.com.kaikeventura.jmeter.model.Installments;
import br.com.kaikeventura.jmeter.model.PaymentMethod;
import br.com.kaikeventura.jmeter.model.ProductSessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final HttpSession session;

    public PaymentMethod getPaymentMethod(PaymentMethod paymentMethod) throws Exception {
        checkIfValidPayment(paymentMethod);
        return verifyPaymentMethod(paymentMethod);
    }

    private void checkIfValidPayment(PaymentMethod paymentMethod) throws Exception {
        ProductSessionData productSessionData = (ProductSessionData) session.getAttribute("productSessionData");
        if (!paymentMethod.getPaymentID().equals(productSessionData.getPaymentID())) {
            throw new Exception("Error");
        }
    }

    private PaymentMethod verifyPaymentMethod(PaymentMethod paymentMethod) {
        if (!paymentMethod.getInCash()) {
            buildPaymentInstallments(paymentMethod);
            return paymentMethod;
        }
        paymentMethod.setPaymentInstallments(new ArrayList<>());
        paymentMethod.setInstallmentsNumber(1);
        paymentMethod.setTotalValue(
                paymentMethod.getProductDetail().getPrice() - ((5 * paymentMethod.getProductDetail().getPrice()) / 100)
        );
        return paymentMethod;
    }

    private void buildPaymentInstallments(PaymentMethod paymentMethod) {
        calculateInterest(paymentMethod);
        ArrayList<Installments> installments = new ArrayList<>();
        Double installmentValue = paymentMethod.getTotalValue() / paymentMethod.getInstallmentsNumber();
        for (int amount = 1; amount <= paymentMethod.getInstallmentsNumber(); amount++) {
            installments.add(new Installments(amount, installmentValue));
        }
        paymentMethod.setPaymentInstallments(installments);
        session.setAttribute("paymentMethod", paymentMethod);
    }

    private void calculateInterest(PaymentMethod paymentMethod) {
        if (paymentMethod.getInstallmentsNumber() == 3) {
            paymentMethod.setTotalValue(
                    paymentMethod.getProductDetail().getPrice() + ((3 * paymentMethod.getProductDetail().getPrice()) / 100)
            );
        }
        if (paymentMethod.getInstallmentsNumber() > 3 && paymentMethod.getInstallmentsNumber() < 8) {
            paymentMethod.setTotalValue(
                    paymentMethod.getProductDetail().getPrice() + ((4 * paymentMethod.getProductDetail().getPrice()) / 100)
            );
        }
        if (paymentMethod.getInstallmentsNumber() > 8 && paymentMethod.getInstallmentsNumber() < 15) {
            paymentMethod.setTotalValue(
                    paymentMethod.getProductDetail().getPrice() + ((4.8 * paymentMethod.getProductDetail().getPrice()) / 100)
            );
        }
        if (paymentMethod.getInstallmentsNumber() > 15) {
            paymentMethod.setTotalValue(
                    paymentMethod.getProductDetail().getPrice() + ((6 * paymentMethod.getProductDetail().getPrice()) / 100)
            );
        }
    }
}
