package br.com.rangeldev.api_restful_spring_railway.service.exeception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }
}
