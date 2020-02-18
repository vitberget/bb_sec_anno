package se.vbgt.test.bb;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;

public class ByteBuddyTestMain {
    public static void main(String[] args) {
        ByteBuddyAgent.install();

        Foo foo = new Foo();

        System.out.printf("1 %s%n", foo.it("muh"));
        System.out.printf("1 %s%n", foo.that("bäh"));

        AsmVisitorWrapper annoWrapper =
            Advice
                .to(AnnoInterceptor.class)
                .on(isAnnotatedWith(Anno.class));

        new ByteBuddy()
            .redefine(Foo.class)
            .visit(annoWrapper)
            .make()
            .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        System.out.printf("2 %s%n", foo.it("nem"));
        System.out.printf("2 %s%n", foo.that("nem"));
    }
}
