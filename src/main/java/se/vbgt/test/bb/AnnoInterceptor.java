package se.vbgt.test.bb;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

public class AnnoInterceptor {
    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Origin Method method) {

        Anno anno = (Anno) method.getDeclaredAnnotations()[0];
        System.out.println("This fn was written in "+ anno.value());

        if (anno.value().equals("BLOCK")) {
            throw new IllegalStateException("Someone said block");
        }

    }
}
