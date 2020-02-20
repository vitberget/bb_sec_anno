package se.vbgt.test.bb.security.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder.Transformer;
import net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.utility.JavaModule;

public class MethodTransformer implements Transformer {

    private final ForDeclaredMethods advice;
    private final String comment;

    public MethodTransformer(ForDeclaredMethods advice, String comment) {
        this.advice = advice;
        this.comment = comment;
    }

    @Override
    public Builder<?> transform(Builder<?> builder,
                                TypeDescription typeDescription,
                                ClassLoader classLoader,
                                JavaModule javaModule) {
        System.out.printf("Transforming %s %s%n", comment, typeDescription);
        return builder.visit(advice);
    }
}
