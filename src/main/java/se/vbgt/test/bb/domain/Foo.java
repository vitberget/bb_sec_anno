package se.vbgt.test.bb.domain;

import se.vbgt.test.bb.security.Anno;

import static java.lang.System.out;
import static se.vbgt.test.bb.security.MyRole.ROLE_2;
import static se.vbgt.test.bb.security.MyRole.ROLE_3;

public class Foo {

    @Anno(ROLE_3)
    public String it(String value) {
        out.println("foo.it running");
        return "foo " + value;
    }

    @Anno({ROLE_2, ROLE_3})
    public String that(String value) {
        out.println("foo.that running");
        return "foot " + value;
    }

    @Anno({ROLE_3})
    public SomeResponse barf(String val) {
        out.println("Foo.barf running " + val);
        return new SomeResponse("det gick bra " + val, 200);
    }
}
