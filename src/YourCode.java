import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YourCode {
    public static void main(String[] args) {
        final BasicGUI gui = new BasicGUI();
        gui.button1.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent actionEvent) {
                button1Clicked(gui);
            }
        });
        gui.button2.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent actionEvent) {
                button2Clicked(gui);
            }
        });
        gui.showWindow();

        /* You can write more code here if you desire */

    }

    public static void button1Clicked(BasicGUI gui) {
        /* Write what happens after button1 gets clicked here */

    }

    public static void button2Clicked(BasicGUI gui) {
        /* Write what happens after button2 gets clicked here */

    }
}
