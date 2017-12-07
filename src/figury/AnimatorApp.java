package figury;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.*;

public class AnimatorApp extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    final AnimatorApp frame = new AnimatorApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param delay
     */
    public AnimatorApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ww = 450, wh = 300;
        setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        setLayout(new BorderLayout());

        AnimPanel kanwa = new AnimPanel();
        mainPanel.add(kanwa, BorderLayout.CENTER);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                kanwa.initialize();
            }
        });

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kanwa.addFig();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(btnAdd);

        JButton btnAnimate = new JButton("Animate");
        btnAnimate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kanwa.animate();
            }
        });

        bottomPanel.add(btnAnimate);
        add(bottomPanel, BorderLayout.SOUTH);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                kanwa.changeSize();
            }
        });
    }

}
