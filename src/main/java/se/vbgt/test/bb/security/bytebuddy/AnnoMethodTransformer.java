package se.vbgt.test.bb.security.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Transformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import se.vbgt.test.bb.security.Anno;

import static net.bytebuddy.matcher.ElementMatchers.none;

public class AnnoMethodTransformer implements Transformer {

    private ForDeclaredMethods advice = Advice
        .to(AnnoInterceptor.class)
        .on(ElementMatchers.isAnnotatedWith(Anno.class));

    @Override
    public Builder<?> transform(Builder<?> builder,
                                TypeDescription typeDescription,
                                ClassLoader classLoader,
                                JavaModule javaModule) {
        System.out.println("Transforming "+typeDescription);
        return builder.visit(advice);
    }

    public static void injectTransformer() {
        new AgentBuilder.Default()
            .ignore(none())
            .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
            .type(new IsAnnoMatcher<TypeDescription>())
            .transform(new AnnoMethodTransformer())
            .installOnByteBuddyAgent();
    }
}
