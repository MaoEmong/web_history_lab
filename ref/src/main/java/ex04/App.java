package ex04;

import ex04.anno.RequestMapping;
import ex04.anno.RestController;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// 리플렉션 - 동적 분석
public class App {
    public static void main(String[] args) throws Exception {
        String uri = "/reply/write";

        DispatcherServlet ds = new DispatcherServlet();
        ds.componentScan("ex04");
        ds.findUri(uri);
    }
}
