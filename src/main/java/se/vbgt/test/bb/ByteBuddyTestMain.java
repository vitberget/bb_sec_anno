package se.vbgt.test.bb;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import static java.lang.System.out;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;

public class ByteBuddyTestMain {
    public static void main(String[] args) {
        ByteBuddyAgent.install();

        Foo foo = new Foo();
        out.printf("1 %s%n", foo.it("muh"));
        out.printf("1 %s%n", foo.that("bäh"));

        new ByteBuddy()
            .redefine(Foo.class)
            .visit(createAnnoWrapper())
            .make()
            .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        out.printf("2 %s%n", foo.it("nem"));
        out.printf("2 %s%n", foo.that("nem"));
    }

    private static AsmVisitorWrapper createAnnoWrapper() {
        return Advice
            .to(AnnoInterceptor.class)
            .on(isAnnotatedWith(Anno.class));
    }
}
