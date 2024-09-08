package org.example.caselogin.model.ENUM;

public enum STUDENT_STATUS {
    DROP_OUT("drop out"),
    SUSPEND("suspend"),
    WAITING_FOR_CLASS_CHANGE("waiting for class change"),
    STUDYING("studying");

    private final String studentStatus;

    STUDENT_STATUS(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getRoleName() {
        return studentStatus;
    }
}
