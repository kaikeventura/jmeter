package br.com.kaikeventura.jmeter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {
    private static final long serialVersionUID = -90000113L;
    private int id;
    private String name;
    private Double price;
}