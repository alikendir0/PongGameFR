import java.awt.*;
public class Menu {
    public void paint(Graphics g){
        Font font=new Font("arial",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("PONG GAME", GamePanel.GAME_WIDTH/2-180,GamePanel.GAME_HEIGHT/4);
        font=new Font("arial",Font.ITALIC,20);
        g.setFont(font);
        g.drawString("Press ENTER to Start the Game",GamePanel.GAME_WIDTH/2-160,GamePanel.GAME_HEIGHT*3/4);
    }
}

