package se.vbgt.test.bb.security;

public class MissingMyRoleException extends RuntimeException {
    private final MyRole[] requiredRoles;
    private final MyRole[] userRoles;

    public MissingMyRoleException(MyRole[] requiredRoles, MyRole[] userRoles) {
        this.requiredRoles = requiredRoles;
        this.userRoles = userRoles;
    }

    public MyRole[] getRequiredRoles() {
        return requiredRoles;
    }
    public MyRole[] getUserRoles() {
        return userRoles;
    }
}
