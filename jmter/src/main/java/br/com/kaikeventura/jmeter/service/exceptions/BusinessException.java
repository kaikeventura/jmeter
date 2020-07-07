package br.com.kaikeventura.jmeter.service.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
    private final String code;
    private final HttpStatus status;
}