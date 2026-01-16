package ex02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
// 리플렉션 - 동적 분석
public class App {
    public static void main(String[] args) {
        // 1. 키보드 입력 처리
        String uri = "/join";

        // 2. 변경할 수 없는 코드(다른사람이 만든 코드)
        uri = uri.replace("/","");

        BoardController con = new BoardController();
        Method[] methods = BoardController.class.getDeclaredMethods();

        // 3. 동적 클래스 분석
        for(var n : methods)
        {
//            System.out.println(n);0
//            System.out.println(n.getName());
            try {
                if(n.getName().equals(uri)){
                    n.invoke(con);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
