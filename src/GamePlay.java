import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                balYDir = -balYDir;

            }
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if(map.map[i][j] > 0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int bricksWidth = map.brickWidth;
                        int bricksHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX,brickY,bricksWidth,bricksHeight);
                        Rectangle ballrect = new Rectangle(ballPosX,ballPosY,20,20);
                        Rectangle brickrect = rect;
                        if(ballrect.intersects(brickrect)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5;
                            if(ballPosX+19 <= brickrect.x || ballPosX+1 >= brickrect.x+bricksWidth){
                                ballXDir = -ballXDir;
                            }
                            else {
                                balYDir = -balYDir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX+=ballXDir;
            ballPosY+=balYDir;
            if(ballPosX < 0){
                ballXDir = -ballXDir;
            }
            if(ballPosY < 0){
                balYDir = -balYDir;
            }
            if(ballPosX > 670){
                ballXDir = -ballXDir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX = 600;
            }
            else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX<10){
                playerX = 10;
            }
            else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                ballPosX =120;
                ballPosY = 350;
                ballXDir = -1;
                balYDir = -2;
                score = 0;
                playerX = 310;
                totalBricks = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    private  boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 0;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXDir = -1;
    private int balYDir = -2;
    private MapGenerator map;

    public GamePlay(){
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();

    }

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1,1,692, 592);
        map.draw((Graphics2D) g);
        g.setColor(Color.orange);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        g.setColor(Color.orange);
        g.fillRect(playerX,550,100,8);

        g.setColor(Color.red);
        g.fillOval(ballPosX,ballPosY,20,20);

        if(ballPosY > 570){
            play = false;
            ballXDir = 0;
            balYDir = 0;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString("Game Over! Score : "+score,190,300);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart", 190, 340);

        }

        if(totalBricks == 0){
            play = false;
            balYDir = -2;
            ballXDir = -1;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over! Score : "+score, 190, 300);

            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("Press Enter to Restart",190,340);
        }
        g.dispose();
    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }

    public void moveLeft(){
        play = true;
        playerX-=20;
    }

}
