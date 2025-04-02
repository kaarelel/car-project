package com.carproject.request;

public class ClientRequest {
    private String fullName;
    private String phoneNumber;
    private String selectedBrand;
    private String selectedModel;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(String selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    public String getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(String selectedModel) {
        this.selectedModel = selectedModel;
    }
}