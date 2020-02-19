package se.vbgt.test.bb.domain;

import se.vbgt.test.bb.security.Anno;

import static java.lang.System.out;
import static se.vbgt.test.bb.security.MyRole.ROLE_2;
import static se.vbgt.test.bb.security.MyRole.ROLE_3;

public class Foo {

    @Anno(ROLE_2)
    public String it(String value) {
        out.println("foo.it called");
        return "foo " + value;
    }

    @Anno({ROLE_2, ROLE_3})
    public String that(String value) {
        out.println("foo.that called");
        return "foot " + value;
    }
}
