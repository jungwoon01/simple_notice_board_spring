package com.jungwoon.simple_notice_board.handler.exception;

public class OAuthIdDuplicateException extends RuntimeException {

    // 객체를 구분할 때 ( JVM 한테 중요 )
    private static final long serialVersionUID = 1L;

    public OAuthIdDuplicateException(String message) {
        super(message);
    }
}
