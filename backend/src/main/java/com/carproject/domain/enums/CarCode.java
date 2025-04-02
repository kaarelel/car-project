package com.carproject.domain.enums;

public enum CarCode {
    MERCEDES_BENZ,
    BMW,
    AUDI,
    CITROEN,
    MUU;

    public static CarCode fromString(String value) {
        if (value == null) return null;
        for (CarCode code : values()) {
            if (code.name().equalsIgnoreCase(value.trim())) {
                return code;
            }
        }
        return null;
    }

}
