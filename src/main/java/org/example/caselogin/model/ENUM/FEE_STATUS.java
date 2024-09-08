package org.example.caselogin.model.ENUM;

public enum FEE_STATUS {
    TUITION("tuition debt"),
    SUBMITTED("submitted");


    private final String freeStatus;

    FEE_STATUS(String freeStatus) {
        this.freeStatus = freeStatus;
    }

    public String getRoleName() {
        return freeStatus;
    }
    }
