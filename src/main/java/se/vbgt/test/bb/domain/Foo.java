package se.vbgt.test.bb.domain;

import static java.lang.System.out;

public class Foo {

    @Anno("123")
    public String it(String value) {
        out.println("foo.it called");
        return "foo " + value;
    }

    @Anno("BLOCKe")
    public String that(String value) {
        out.println("foo.that called");
        return "foot " + value;
    }
}
