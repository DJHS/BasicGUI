import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

public class BasicGUI {
    abstract class ReflectionBindingActionListener implements ActionListener {
        @Override public void actionPerformed(ActionEvent actionEvent) {
            try {
                wrappedReflectionCall();
            } catch (NoSuchMethodException e) {
                e.printStackTrace(System.err);
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.err);
            } catch (InvocationTargetException e) {
                e.printStackTrace(System.err);
            }
        }

        public abstract void wrappedReflectionCall() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    }

    public JFrame window;
    public JLabel label = new JLabel("Label");
    public JButton button1 = new JButton("Button 1");
    public JButton button2 = new JButton("Button 2");
    public JTextField field1 = new JTextField();
    public JTextField field2 = new JTextField();

    static final int B = 4;

    public BasicGUI(final Class cls, final String button1BindingName, final String button2BindingName) {
        this.window = new JFrame();

        final BasicGUI gui = this;

        button1.addActionListener(new ReflectionBindingActionListener() {
            @Override public void wrappedReflectionCall() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
                cls.getMethod(button1BindingName, new Class[]{ BasicGUI.class }).invoke(null, gui);
            }
        });
        button2.addActionListener(new ReflectionBindingActionListener() {
            @Override public void wrappedReflectionCall() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
                cls.getMethod(button2BindingName, new Class[]{ BasicGUI.class }).invoke(null, gui);
            }
        });

        Container c = this.window.getContentPane();
        SpringLayout layout = new SpringLayout();
        c.setLayout(layout);
        window.add(label);
        window.add(button1);
        window.add(button2);
        window.add(field1);
        window.add(field2);

        layout.putConstraint(SpringLayout.NORTH, label, B, SpringLayout.NORTH, c);
        layout.putConstraint(SpringLayout.NORTH, field1, B, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.NORTH, field2, B, SpringLayout.SOUTH, field1);
        layout.putConstraint(SpringLayout.NORTH, button1, B, SpringLayout.SOUTH, field2);
        layout.putConstraint(SpringLayout.NORTH, button2, B, SpringLayout.SOUTH, field2);

        layout.putConstraint(SpringLayout.WEST, label, B, SpringLayout.WEST, c);
        layout.putConstraint(SpringLayout.WEST, field1, B, SpringLayout.WEST, c);
        layout.putConstraint(SpringLayout.WEST, field2, B, SpringLayout.WEST, c);
        layout.putConstraint(SpringLayout.WEST, button1, B, SpringLayout.WEST, c);
        layout.putConstraint(SpringLayout.WEST, button2, B, SpringLayout.HORIZONTAL_CENTER, c);

        layout.putConstraint(SpringLayout.EAST, label, -B, SpringLayout.EAST, c);
        layout.putConstraint(SpringLayout.EAST, field1, -B, SpringLayout.EAST, c);
        layout.putConstraint(SpringLayout.EAST, field2, -B, SpringLayout.EAST, c);
        layout.putConstraint(SpringLayout.EAST, button1, -B, SpringLayout.HORIZONTAL_CENTER, c);
        layout.putConstraint(SpringLayout.EAST, button2, -B, SpringLayout.EAST, c);

        layout.putConstraint(SpringLayout.SOUTH, button1, -B, SpringLayout.SOUTH, c);
        layout.putConstraint(SpringLayout.SOUTH, button2, -B, SpringLayout.SOUTH, c);

        window.pack();
        window.setMinimumSize(new Dimension(400, 200));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Basic GUI");

        this.showWindow();
    }

    public void showWindow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    // Do Nothing (it will fall back to default LnF)
                }
                window.setVisible(true);
            }
        });
    }
}
