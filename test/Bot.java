
import java.util.Random;
import java.util.Scanner;

public class Bot {

    static Scanner s;
    static String[] Q;

    public static void sendResponse(String Msg) {
        
        System.out.println("Msg :" + Msg);
    }

    public static void main(String[] args) {
        s = new Scanner(System.in);
        String msg;
        while (1 == 1) {
            msg = s.nextLine();
            if (msg.equals("exit")) {
                break;
            }
            sendResponse(msg);
        }
    }
}
