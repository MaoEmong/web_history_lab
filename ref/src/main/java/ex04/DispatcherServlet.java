package ex04;

import ex04.anno.RequestMapping;
import ex04.anno.RestController;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet {
    // IoC 컨테이너 = 제어의 역전
    public List<Object> iocContainer = new ArrayList<>();

    // 1. 컴퍼넌트 스캔해서 ioc 컨테이너에 객체 추가
    public void componentScan(String folder) throws Exception {
        // 2. 패키지스캔
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageUrl = classLoader.getResource(folder);
        File ex04Folder = new File(packageUrl.toURI());
        File[] files = ex04Folder.listFiles(); // anno, App, BoardController, UserController

        // 3. 찾은 것들을 new해서 컬렉션에 담아두기
        for (File f : files){
            //System.out.println(f.getName());
            if(f.getName().endsWith(".class")){
                String className = folder+"."+f.getName().replace(".class", ""); // ex04.BoardController, ex04.UserController
                // ex04.BoardController, ex04.UserController, ex04.App
                Class cls = Class.forName(className); // JVM 문자열로 클래스 로딩
                //System.out.println(cls);

                if(cls.isAnnotationPresent(RestController.class)){
                    System.out.println(cls);
                    Object instance = cls.newInstance();
                    iocContainer.add(instance);
                }
            }
        }
    }

    // 2. 컴퍼넌트 스캔 대상에서 메서드 찾기
    public void findUri(String value){
        for(Object instance : iocContainer){
            Method[] methods = instance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);
                if(rm.value().equals(value)){
                    try {
                        method.invoke(instance);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}