package se.vbgt.test.bb;

import static java.lang.System.out;

public class Foo {

    @Anno("123")
    public String it(String value) {
        out.println("foo.it called");
        return "foo " + value;
    }

    @Anno("BLOCK")
    public String that(String value) {
        out.println("foo.that called");
        return "foot " + value;
    }
}
