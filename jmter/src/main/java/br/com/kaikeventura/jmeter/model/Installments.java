package br.com.kaikeventura.jmeter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Installments implements Serializable {
    private static final long serialVersionUID = -21821150001817L;
    private int installment;
    private Double value;
}