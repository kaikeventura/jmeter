package br.com.kaikeventura.jmeter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethod extends PaymentDetail implements Serializable {
    private static final long serialVersionUID = -90000025L;
    @JsonProperty("in_cash")
    private Boolean inCash;
    @JsonProperty("installments_number")
    private int installmentsNumber;
    @JsonProperty("payment_installments")
    private List<Installments> paymentInstallments;
    @JsonProperty("total_value")
    private Double totalValue;
}
