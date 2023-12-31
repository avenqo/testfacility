package com.avenqo.testfacility.drivers;

public class TargetNotValidException extends IllegalStateException {

    public TargetNotValidException(String target) {
        super(String.format("Target %s not supported. Use either local or gird", target));
    }
}
