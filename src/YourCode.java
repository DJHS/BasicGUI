import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YourCode {
    public static void main(String[] args) {
        final BasicGUI gui = new BasicGUI(new Object(){}.getClass().getEnclosingClass(), "button1Clicked", "button2Clicked");

        /* Write code here that you want to run on startup */
    }

    public static void button1Clicked(BasicGUI gui) {
        /* Write code here to run when button1 gets clicked */
    }

    public static void button2Clicked(BasicGUI gui) {
        /* Write code here to run when button2 gets clicked */
    }
}
