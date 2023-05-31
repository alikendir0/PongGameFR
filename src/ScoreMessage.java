import java.awt.*;

public class ScoreMessage  {

    public static void paint(Graphics g, int player) {
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String message = "Player " + player + " scored!";
        int x = (GamePanel.GAME_WIDTH )/2-184;
        int y = GamePanel.GAME_HEIGHT/2;

        g.drawString(message, x, y);
    }
}