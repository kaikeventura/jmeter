package br.com.kaikeventura.jmeter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class PaymentDetail implements Serializable {
    private static final long serialVersionUID = -95735647L;
    @JsonProperty("payment_id")
    private String paymentID;
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    @JsonProperty("payment_detail")
    private ProductDetail productDetail;
}