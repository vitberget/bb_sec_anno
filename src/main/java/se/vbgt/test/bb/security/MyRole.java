package se.vbgt.test.bb.security;

/**
 * Some very special user roles
 *
 * ...and support for storing them on a ThreadLocal (it's here because I'm lazy)
 */
public enum MyRole {
    ROLE_1,
    ROLE_2,
    ROLE_3;

    private static ThreadLocal<MyRole[]> threadLocal = new ThreadLocal<>();

    public static void setOnThread(MyRole... roles) {
        threadLocal.set(roles);
    }

    public static MyRole[] getFromThread() {
        return threadLocal.get();
    }

    public static void removeFromThread() {
        threadLocal.remove();
    }
}
