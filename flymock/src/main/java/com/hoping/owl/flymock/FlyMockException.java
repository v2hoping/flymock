package com.hoping.owl.flymock;

/**
 * Created by houping wang on 2019/3/11
 *
 * @author houping wang
 */
public class FlyMockException extends RuntimeException {

    public FlyMockException() {
    }

    public FlyMockException(String message) {
        super(message);
    }

    public FlyMockException(String message, Throwable cause) {
        super(message, cause);
    }
}
