package se.vbgt.test.bb.domain;

/**
 * Represent a special return type, in "real life" perhaps a Servlet response.
 */
public class SomeResponse {
    String message;
    int code;

    public SomeResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("SomeResponse{message='%s', code=%d}", message, code);
    }
}
