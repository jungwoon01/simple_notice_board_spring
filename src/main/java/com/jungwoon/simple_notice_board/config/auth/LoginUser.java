package com.jungwoon.simple_notice_board.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 어노테이션이 생성될 수 있는 위치를 지정한다.
// PARAMETER 로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
@Target(ElementType.PARAMETER)
// RUNTIME : 어노테이션을 런타임시에까지 사용할 수 있습니다.
// JVM 이 자바 바이트코드가 담긴 class 파일에서 런타임환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있습니다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
