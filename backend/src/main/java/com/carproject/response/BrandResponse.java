package com.carproject.response;

public class BrandResponse {
    private String code;
    private String name;

    public BrandResponse(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
