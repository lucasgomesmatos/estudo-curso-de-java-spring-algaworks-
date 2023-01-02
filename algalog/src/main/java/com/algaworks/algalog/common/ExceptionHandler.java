package com.algaworks.algalog.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionError exceptionError = new ExceptionError();
        List<ExceptionError.Campo> campos = new ArrayList<>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new ExceptionError.Campo(nome, mensagem));
        }

        exceptionError.setStatus(status.value());
        exceptionError.setDataHora(LocalDateTime.now());
        exceptionError.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
        exceptionError.setCampos(campos);

        return handleExceptionInternal(ex, exceptionError, headers, status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NegocioExpection.class)
    public ResponseEntity<Object> handleNegocio(NegocioExpection ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionError exceptionError = new ExceptionError();
        exceptionError.setStatus(status.value());
        exceptionError.setDataHora(LocalDateTime.now());
        exceptionError.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, exceptionError, new HttpHeaders(), status, request);
    }
}
