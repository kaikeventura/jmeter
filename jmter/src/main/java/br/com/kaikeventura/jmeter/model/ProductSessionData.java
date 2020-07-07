package br.com.kaikeventura.jmeter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSessionData extends Product implements Serializable {
    private static final long serialVersionUID = -34559431714779636L;
    private String token;
    @JsonProperty("session_id")
    private String sessionID;
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    @JsonProperty("payment_id")
    private String paymentID;
}