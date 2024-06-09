package br.com.rangeldev.api_restful_spring_railway.service.exeception;

public class NotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("OPS! Resource not found.");
    }

}
