import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    private int player;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Score score;
    Menu menu;
    Controls controls;
    private boolean showScoreMessage = false;
    private boolean beginning=true;
    private enum STATE{
        MENU,GAME,CONTROLS
    }
    private STATE State=STATE.MENU;
    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread=new Thread(this);
        gameThread.start();
    }
    public void newBall(){
        random=new Random();
        ball=new Ball((GAME_WIDTH/2-BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddles(){
        paddle1=new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2=new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
    public void paint(Graphics g){
        if(State==STATE.GAME){
            image=createImage(getWidth(),getHeight());
            graphics=image.getGraphics();
            draw(graphics);
            g.drawImage(image,0,0,this);
            if (showScoreMessage) {
                ScoreMessage.paint(g,player);
            }
        } else if(State==STATE.MENU){
            menu=new Menu();
            menu.paint(g);
        } else if (State==STATE.CONTROLS) {
            controls =new Controls();
            controls.paint(g);
        }
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision(){
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y>=GAME_HEIGHT-PADDLE_HEIGHT)
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y>=GAME_HEIGHT-PADDLE_HEIGHT)
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
        //to make the paddles not go out of bounds
        if(ball.y<=0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y>=GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }
        //to reflect the ball from the walls
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //to make the ball bounce from paddles+increases ball speed for the fun of it
        if(ball.x<=0){
            playerScored(2);
            newBall();
            newPaddles();
        }
        if(ball.x>=GAME_WIDTH-BALL_DIAMETER){
            playerScored(1);
            newBall();
            newPaddles();
        }
    }
    public void playerScored(int id) {
        switch(id) {
            case 1:
                score.player1++;

                player=1;
                showScoreMessage = true;
                //this displays when a player scores a message
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //pauses the game for a brief moment
                showScoreMessage = false;
                beginning=true;
                break;
            case 2:
                score.player2++;
                player=2;
                showScoreMessage = true;
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                showScoreMessage = false;
                beginning=true;
                break;
        }
    }
    public void resetGameState(){
        score.player1=0;
        score.player2=0;
        newPaddles();
        newBall();
        ball.setSpeed(0);
        beginning=true;
    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if(State==STATE.GAME){
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
                if((State==STATE.GAME&&KeyEvent.VK_ENTER==e.getKeyCode())&&beginning){
                    ball.setSpeed(4);
                    beginning=false;
                }
                if(State==STATE.GAME&&KeyEvent.VK_R==e.getKeyCode())
                    resetGameState();
            }if(State==STATE.MENU){
                if(KeyEvent.VK_ENTER==e.getKeyCode()){
                    State=STATE.GAME;
                }else if (KeyEvent.VK_P==e.getKeyCode())
                    State=STATE.CONTROLS;
            }
            if(State==STATE.CONTROLS){
                if(KeyEvent.VK_ENTER==e.getKeyCode()){
                    State=STATE.GAME;
                }
            }
        }
        public void keyReleased(KeyEvent e){
            if(State==STATE.GAME){
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            }
        }
    }
}