import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel panel;
    GameFrame(){
        panel = new GamePanel();
        super.add(panel);
        super.setTitle("Pong Game");
        super.setResizable(false);
        super.setBackground(Color.BLACK);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.pack();
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }
}

