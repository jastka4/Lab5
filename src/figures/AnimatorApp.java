package figures;

import javax.swing.*;
import java.awt.*;

class AnimatorApp extends JFrame {
    AnimatedPanel animatedPanel;
    JButton addFigure,
            changeAnimationState;
    JPanel menuPanel;
    

    public AnimatorApp()
    {
        super("Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        animatedPanel = new AnimatedPanel();
        animatedPanel.setPreferredSize(new Dimension(500,300));
        getContentPane().add(animatedPanel, BorderLayout.CENTER);


        addFigure = new JButton("Add");
        addFigure.addActionListener(actionEvent -> {
            animatedPanel.addFigure();
        });
        changeAnimationState = new JButton("Run/Pause animation");
        changeAnimationState.addActionListener(actionEvent -> animatedPanel.animate());

        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(addFigure);
        menuPanel.add(changeAnimationState);

        getContentPane().add(menuPanel, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            try {
                final AnimatorApp frame = new AnimatorApp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}