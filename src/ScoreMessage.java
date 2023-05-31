import java.awt.*;

public class ScoreMessage  {
    public static void paint(Graphics g, int player) {
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String notice = "Player " + player + " scored!";
        g.drawString(notice,(GamePanel.GAME_WIDTH )/2-184,GamePanel.GAME_HEIGHT/2);
    }
}