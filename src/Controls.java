import java.awt.*;
public class Controls {
    public void paint(Graphics g){
        Font font=new Font("arial",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Player 1", 15,GamePanel.GAME_HEIGHT*2/5+10);
        g.drawString("Press W to go UP", 15,GamePanel.GAME_HEIGHT*2/5+30);
        g.drawString("Press S to go DOWN", 15,GamePanel.GAME_HEIGHT*2/5+50);

        g.drawString("Player 2", GamePanel.GAME_WIDTH-g.getFontMetrics().stringWidth("Player 2")-15,GamePanel.GAME_HEIGHT*2/5+10);
        g.drawString("Press ARROW UP to go UP", GamePanel.GAME_WIDTH-g.getFontMetrics().stringWidth("Press ARROW UP to go UP")-15,GamePanel.GAME_HEIGHT*2/5+30);
        g.drawString("Press ARROW DOWN to go DOWN", GamePanel.GAME_WIDTH-g.getFontMetrics().stringWidth("Press ARROW DOWN to go DOWN")-15,GamePanel.GAME_HEIGHT*2/5+50);
    }
}