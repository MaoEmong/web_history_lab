package ex03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션
// 동작시점 - 컴파일 시점 or 런타임 시점
//@Retention(RetentionPolicy.SOURCE) // <- 컴파일 시점
@Retention(RetentionPolicy.RUNTIME) // <- 런타임 시점
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value(); // 어노테이션의 속성값 (value만 사용 시 키값 생략 가능) value == 키워드
}
