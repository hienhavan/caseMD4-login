package org.example.caselogin.model;

public enum ROLE {
    ROLE_ADMIN("admin"),
    ROLE_LECTURER("lecturer"),
    ROLE_STUDENT("student"),
    ROLE_STAFF("staff");

    private final String roleName;

    ROLE(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}