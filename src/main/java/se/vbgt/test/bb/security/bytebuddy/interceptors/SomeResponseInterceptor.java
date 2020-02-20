package se.vbgt.test.bb.security.bytebuddy.interceptors;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner.Typing;
import se.vbgt.test.bb.domain.SomeResponse;
import se.vbgt.test.bb.security.MissingMyRoleException;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * If MissingMyRoleException is thrown on method annotated with @Anno with return type SomeResponse,
 * then create a SomeResponse with some info from the exception
 */
public class SomeResponseInterceptor {
    @Advice.OnMethodExit(onThrowable = MissingMyRoleException.class)
    public static void onExit(
        @Advice.Origin Method method,
        @Advice.Thrown(readOnly = false) MissingMyRoleException thrown,
        @Advice.Return(readOnly = false, typing = Typing.DYNAMIC) Object returnValue

    ) {
        if(thrown != null && Objects.equals(method.getReturnType().getName(), SomeResponse.class.getName())) {
            returnValue = new SomeResponse("i am the ... evil guy "+thrown.getClass().getName(), -1);
            thrown = null;
        }
    }
}
