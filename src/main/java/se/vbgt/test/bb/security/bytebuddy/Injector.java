package se.vbgt.test.bb.security.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatchers;
import se.vbgt.test.bb.security.Anno;
import se.vbgt.test.bb.security.bytebuddy.interceptors.AnnoInterceptor;
import se.vbgt.test.bb.security.bytebuddy.interceptors.SomeResponseInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.none;

public class Injector {

    private static final ForDeclaredMethods ANNO_ADVICE =
        Advice.to(AnnoInterceptor.class)
              .on(ElementMatchers.isAnnotatedWith(Anno.class));

    private static final ForDeclaredMethods SOME_RESPONSE_ADVICE =
        Advice.to(SomeResponseInterceptor.class)
              .on(ElementMatchers.isAnnotatedWith(Anno.class));

    public static void injectTransformer() {
        new AgentBuilder.Default()
            .ignore(none())
            .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)

            // Only on classes where at least one method is annotated with @Anno
            .type(new IsAnnoMatcher<TypeDescription>())

            // Add transform so that if the method is with the return type of SomeResponse is throwing a MissingMyRoleException,
            // create a SomeReponse instead of throwing exception
            .transform(new MethodTransformer(SOME_RESPONSE_ADVICE, "SomeResponse"))

            // If the roles in TheadLocal does not match requirements of @Anno, throw MissingMyRoleException
            .transform(new MethodTransformer(ANNO_ADVICE, "@Anno"))

            .installOnByteBuddyAgent();
    }
}
