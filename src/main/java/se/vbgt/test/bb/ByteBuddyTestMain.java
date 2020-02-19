package se.vbgt.test.bb;

import net.bytebuddy.agent.ByteBuddyAgent;
import se.vbgt.test.bb.domain.Bar;
import se.vbgt.test.bb.domain.Foo;
import se.vbgt.test.bb.security.MyRole;

import static java.lang.System.out;
import static se.vbgt.test.bb.security.MyRole.*;
import static se.vbgt.test.bb.security.bytebuddy.AnnoMethodTransformer.injectTransformer;

public class ByteBuddyTestMain {
    public static void main(String[] args) {
        ByteBuddyAgent.install();
        injectTransformer();

        // MyRole.setOnThread(ROLE_2);
        // MyRole.setOnThread(ROLE_2, ROLE_3);
        MyRole.setOnThread(ROLE_1, ROLE_2, ROLE_3);

        Foo foo = new Foo();
        out.printf("1 %s%n", foo.it("muh"));
        out.printf("1 %s%n", foo.that("bäh"));


        Bar b = new Bar();
        b.test();

        out.printf("2 %s%n", foo.it("nem"));
        out.printf("2 %s%n", foo.that("nem"));
    }
}
