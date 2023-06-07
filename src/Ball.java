import java.util.*;
import java.awt.*;
public class Ball extends Rectangle{
    private Random random;
    public int xVelocity;
    public int yVelocity;
    Ball(int x,int y,int width,int height){
        super(x,y,width,height);
    }
    public void setXDirection(int randomXDirection){
        xVelocity=randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVelocity=randomYDirection;
    }
    public void move(){
        x+=xVelocity;
        y+=yVelocity;
    }
    public void setSpeed(int speed){
        random=new Random();
        int randomXDirection=random.nextInt(2);
        if(randomXDirection==0)
            randomXDirection--;
        setXDirection(randomXDirection*speed);

        int randomYDirection=random.nextInt(2);
        if(randomYDirection==0)
            randomYDirection--;
        setYDirection(randomYDirection*speed);
    }
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height);
    }
}
