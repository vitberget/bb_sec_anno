package se.vbgt.test.bb;

public class Foo {

    @Anno("123")
    public String it(String value) {
        System.out.println("foo.it called");
        return "foo "+value;
    }

    @Anno("BLOCK")
    public String that(String value) {
        System.out.println("foo.that called");
        return "foot "+value;
    }
}
