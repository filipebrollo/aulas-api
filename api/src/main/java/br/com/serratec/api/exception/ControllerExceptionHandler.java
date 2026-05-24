package br.com.serratec.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // Ela anotação torna essa classe um farejador e repositório de erros
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.add(erro.getField() + " - " + erro.getDefaultMessage());
        }

        ErroResposta erroResposta = new ErroResposta(status.value(), "Existem erros na requisição!",
                LocalDateTime.now(), erros);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErroResposta erroResposta = new ErroResposta(status.value(), "Existem erros na requisição!",
                LocalDateTime.now(), null);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @ExceptionHandler(UsuarioException.class)
    protected ResponseEntity<Object> handleUsuarioException(UsuarioException ex){
        ErroResposta er = new ErroResposta(HttpStatus.UNPROCESSABLE_CONTENT.value(),"Existem erros! " + ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(er);
    }
}