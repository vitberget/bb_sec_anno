package se.vbgt.test.bb;

import net.bytebuddy.agent.ByteBuddyAgent;
import se.vbgt.test.bb.domain.Bar;
import se.vbgt.test.bb.domain.Foo;

import static java.lang.System.out;
import static se.vbgt.test.bb.security.AnnoMethodTransformer.injectTransformer;

public class ByteBuddyTestMain {
    public static void main(String[] args) {
        ByteBuddyAgent.install();
        injectTransformer();

        Foo foo = new Foo();
        out.printf("1 %s%n", foo.it("muh"));
        out.printf("1 %s%n", foo.that("bäh"));


        Bar b = new Bar();
        b.test();

        out.printf("2 %s%n", foo.it("nem"));
        out.printf("2 %s%n", foo.that("nem"));
    }


}
