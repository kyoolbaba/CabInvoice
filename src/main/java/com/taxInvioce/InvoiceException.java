package com.taxInvioce;

public class InvoiceException extends Exception{

    enum ExceptionType{
       NULL_VALUE_ENTERED
    }
    ExceptionType type;
    public InvoiceException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
