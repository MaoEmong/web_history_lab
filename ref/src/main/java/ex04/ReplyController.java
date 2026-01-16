package ex04;

import ex04.anno.RequestMapping;
import ex04.anno.RestController;

@RestController
public class ReplyController {

    @RequestMapping("/reply/write")
    public void write()
    {
        System.out.println("댓글 쓰기 완료");
    }
}
