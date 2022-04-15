package com.mercadolibre.apiantifraude.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IpNotAllowedException extends RuntimeException{

    public IpNotAllowedException() {
        super();
    }

    public IpNotAllowedException(String msg) {
        super(msg);
    }

}
