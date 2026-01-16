package ex03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 리플렉션 - 동적 분석
public class App {
    public static void main(String[] args) {
        // 1. 키보드 입력 처리
        String uri = "/insert";

        // 2. 변경할 수 없는 코드(다른사람이 만든 코드)
        BoardController con = new BoardController();

        // 3. 동적 클래스 분석
        Method[] methods = BoardController.class.getDeclaredMethods();
        for (var m : methods) {
            RequestMapping rm = m.getDeclaredAnnotation(RequestMapping.class);
            if(rm.value().equals(uri))
            {
                try {
                    Object res = m.invoke(con);
                    System.out.println("응답 버퍼 : " + res);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
