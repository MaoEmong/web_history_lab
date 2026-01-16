package com.example.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컴퍼넌트 스캔 대상
public class BoardController {

    @GetMapping("/insert")
    public String insert() {
        System.out.println("insert 호출됨");
        return "<h1>apple</h1>";
    }

    @GetMapping("/delete")
    public void delete() {
        System.out.println("delete 호출됨");
    }

    @GetMapping("/update")
    public void update() {
        System.out.println("update 호출됨");
    }
}
