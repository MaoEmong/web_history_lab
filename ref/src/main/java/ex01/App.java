package ex01;

public class App {
    public static void main(String[] args) {
        String uri = "/select";

        BoardController con = new BoardController();

        if(uri.equals("/insert")){
            con.insert();
        }else if(uri.equals("/update")){
            con.update();
        }else if(uri.equals("/delete")){
            con.delete();
        }else if(uri.equals("/select")){
            con.select();
        }
    }
}
