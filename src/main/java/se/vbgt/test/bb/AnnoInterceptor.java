package se.vbgt.test.bb;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

import static java.lang.System.out;

public class AnnoInterceptor {
    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Origin Method method) {

        Anno anno = (Anno) method.getDeclaredAnnotations()[0];
        out.println("This fn was written in "+ anno.value());

        if ("BLOCK".equals(anno.value())) {
            throw new IllegalStateException("Someone said block");
        }
    }
}
