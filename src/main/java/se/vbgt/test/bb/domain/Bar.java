package se.vbgt.test.bb.domain;

import se.vbgt.test.bb.security.Anno;

import static se.vbgt.test.bb.security.MyRole.ROLE_1;

public class Bar {

    @Anno(ROLE_1)
    public void test() {
        System.out.println("no block?");
    }
}
