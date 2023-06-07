import javax.swing.*;
import java.awt.*;
public class Menu extends JPanel {
    public void paint(Graphics g){
        super.paintComponent(g);
        ImageIcon i = new ImageIcon("tedu.png");
        g.drawImage(i.getImage(), GamePanel.GAME_WIDTH/2-400/6, GamePanel.GAME_HEIGHT/2-316/6,400/3,316/3, this);


        Font font=new Font("arial",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("PONG GAME", GamePanel.GAME_WIDTH/2-g.getFontMetrics().stringWidth("PONG GAME")/2,GamePanel.GAME_HEIGHT/4);


        font=new Font("arial",Font.ITALIC,20);
        g.setFont(font);
        g.drawString("Press ENTER to Start the Game",GamePanel.GAME_WIDTH/2-g.getFontMetrics().stringWidth("Press ENTER to Start the Game")/2,GamePanel.GAME_HEIGHT*3/4);


        font=new Font("arial",Font.ITALIC,15);
        g.setFont(font);
        g.drawString("Press P for Controls",GamePanel.GAME_WIDTH/2-g.getFontMetrics().stringWidth("Press P for Controls")/2,GamePanel.GAME_HEIGHT*4/5);
    }
}

