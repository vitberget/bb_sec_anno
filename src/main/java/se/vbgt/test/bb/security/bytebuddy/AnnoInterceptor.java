package se.vbgt.test.bb.security.bytebuddy;

import net.bytebuddy.asm.Advice;
import se.vbgt.test.bb.security.Anno;
import se.vbgt.test.bb.security.MissingMyRoleException;
import se.vbgt.test.bb.security.MyRole;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnoInterceptor {
    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Origin Method method) {

        // Lamdas and other "nice" stuff makes a private function, that cannot be run in the injected method

        Anno anno = null;
        for (Annotation a : method.getDeclaredAnnotations()) {
            if (a instanceof Anno) {
                anno = (Anno) a;
                break;
            }
        }

        if (anno != null) {
            MyRole[] requiredRoles = anno.value();
            MyRole[] userRoles = MyRole.getFromThread();

            for (MyRole req : requiredRoles) {
                boolean reqFullfilled = false;
                for (int i = 0; i < userRoles.length && !reqFullfilled; i++) {
                    reqFullfilled = req == userRoles[i];
                }
                if (!reqFullfilled) {
                    throw new MissingMyRoleException(requiredRoles, userRoles);
                }
            }
        }
    }
}
