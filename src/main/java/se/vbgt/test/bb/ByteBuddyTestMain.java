package se.vbgt.test.bb;

import net.bytebuddy.agent.ByteBuddyAgent;
import se.vbgt.test.bb.domain.Foo;
import se.vbgt.test.bb.domain.SomeResponse;
import se.vbgt.test.bb.security.MyRole;

import static java.lang.System.out;
import static se.vbgt.test.bb.security.MyRole.ROLE_2;
import static se.vbgt.test.bb.security.MyRole.ROLE_3;
import static se.vbgt.test.bb.security.bytebuddy.Injector.injectTransformer;

public class ByteBuddyTestMain {
    public static void main(String[] args) {
        ByteBuddyAgent.install();
        injectTransformer();

        out.println("--- ROLE 3 ---");
        MyRole.setOnThread(ROLE_3);
        new Foo().it("345");
        SomeResponse roll3 = new Foo().barf("345");
        out.println("Test role 3 "+ roll3);

        out.println();
        out.println("--- ROLE 2 ---");
        MyRole.setOnThread(ROLE_2);
        new Foo().it("error");
        SomeResponse roll2 = new Foo().barf("fd");
        out.println("Test role 2 "+ roll2);
    }
}
