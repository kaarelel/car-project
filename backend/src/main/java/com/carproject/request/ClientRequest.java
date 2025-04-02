package com.carproject.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ClientRequest {

    @NotBlank(message = "Nimi on kohustuslik")
    @Pattern(regexp = "^[a-zA-ZäöüõÄÖÜÕ\\s]+$", message = "Nimi võib sisaldada ainult tähti ja tühikuid")
    private String fullName;

    @NotBlank(message = "Telefoninumber on kohustuslik")
    @Pattern(regexp = "^\\d+$", message = "Telefoninumber võib sisaldada ainult numbreid")
    private String phoneNumber;

    @NotBlank(message = "Automark on kohustuslik")
    private String selectedBrand;

    @NotBlank(message = "Automudel on kohustuslik")
    private String selectedModel;

    private boolean hasDriverLicense;

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

    public boolean isHasDriverLicense() {
        return hasDriverLicense;
    }

    public void setHasDriverLicense(boolean hasDriverLicense) {
        this.hasDriverLicense = hasDriverLicense;
    }
}
