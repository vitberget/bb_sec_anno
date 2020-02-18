package se.vbgt.test.bb;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {
    String value() default  "1882";
}
