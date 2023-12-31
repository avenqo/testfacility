package com.avenqo.testfacility.drivers;

public class HeadlessNotSupportedException extends IllegalStateException {

    public HeadlessNotSupportedException() {
        super("Headless not supported for this browser");
    }
}