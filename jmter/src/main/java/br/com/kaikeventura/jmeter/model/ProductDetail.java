package br.com.kaikeventura.jmeter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetail implements Serializable {
    private static final long serialVersionUID = -90000103L;
    private String name;
    private Double price;
}