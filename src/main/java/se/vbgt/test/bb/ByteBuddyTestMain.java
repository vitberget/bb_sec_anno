package se.vbgt.test.bb;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import java.lang.reflect.Method;
import java.util.Arrays;

import static java.lang.System.out;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;

public class ByteBuddyTestMain {
    public static void main(String[] args) {
        ByteBuddyAgent.install();

        Foo foo = new Foo();
        out.printf("1 %s%n", foo.it("muh"));
        out.printf("1 %s%n", foo.that("bäh"));

        Arrays.stream(ByteBuddyAgent.getInstrumentation().getAllLoadedClasses())
              .filter(c -> containsAnnoMethod(c))
              .forEach(c -> buddyClass(c));


        out.printf("2 %s%n", foo.it("nem"));
        out.printf("2 %s%n", foo.that("nem"));
    }

    private static void buddyClass(Class c) {
        new ByteBuddy()
            .redefine(c)
            .visit(createAnnoWrapper())
            .make()
            .load(c.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

    private static AsmVisitorWrapper createAnnoWrapper() {
        return Advice
            .to(AnnoInterceptor.class)
            .on(isAnnotatedWith(Anno.class));
    }

    private static boolean containsAnnoMethod(Class c) {
        return Arrays.stream(c.getMethods())
                     .anyMatch(m -> hasAnnoAnnotation(m));
    }

    private static boolean hasAnnoAnnotation(Method m) {
        return Arrays.stream(m.getDeclaredAnnotations())
              .anyMatch(a -> a instanceof Anno);
    }
}
