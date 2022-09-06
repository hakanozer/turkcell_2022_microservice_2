package com.works.configs;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class CustomDecoder implements ErrorDecoder {

    public CustomDecoder() {
        System.out.println("CustomDecoder Call");
    }

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String s, Response response) {
        System.out.println("ErrorMessage : " + s);
        Exception exception = defaultErrorDecoder.decode(s, response);
        if(exception instanceof RetryableException){
            System.out.println("RetryableException Call");
            return new RuntimeException("RetryableException 500 Status Code");
        }

        switch (response.status()) {
            case 400:
                return new RuntimeException("400 Status Code");
        }
        return new Exception("Global Exception");
    }

}
