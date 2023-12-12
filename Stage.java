import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Stage {
    public ShootingFrame shootingFrame;
    public int playerX,playerY,playerMAXHP;
    public Graphics gra;
    public Stage(int playerX,int playerY,int playerMAXHP,Graphics gra,ShootingFrame shootingFrame){
        this.playerX=playerX;
        this.playerY=playerY;
        this.playerMAXHP=playerMAXHP;
        this.gra=gra;
        this.shootingFrame=shootingFrame;
    }
    public void run() {
        boolean roop=true;
        long startTime;
        long fpsTime=0;
        int fps=30;
        int FPS=0;
        int FPSCount=0;
        int score=0;
        long levelTimer=0;

        Font font;
        FontMetrics metrics;

        // EnumShootingScene scene=EnumShootingScene.TITLE;

        // int playerX=playerX,playerY=playerY;
        int otherX=0,otherY=0;
        int bulletInterval=0,playerInterval=0,otherInterval=0;
        int playercolor=0;
        int othercolor=0;
        
        int otherHP=0;
        // int playerMAXHP=10;
        int playerHP=playerMAXHP;
        int otherMAXHP=10;
        int level=0;
        int PlayerinvincibleTime=0;
        int OtherinvincibleTime=0;
        ArrayList<Bullet> bullets_player=new ArrayList<>();
        // ArrayList<Bullet> bullets_other=new ArrayList<>();
        ArrayList<Bullet> bullets_enemy=new ArrayList<>();
        ArrayList<Enemy> enemies= new ArrayList<>();
        // ArrayList<Enemy> enemies= new ArrayList<>();
        Random random=new Random();

        int PowerupIs=0;
            // shootingFrame=new ShootingFrame();
        while(roop){
            if(System.currentTimeMillis()-fpsTime>=1000){
                fpsTime=System.currentTimeMillis();
                FPS=FPSCount;
                FPSCount=0;
                System.out.println(FPS);
            }
            if(System.currentTimeMillis()-levelTimer>10*1000){
                levelTimer=System.currentTimeMillis();
                level++;
            }
            FPSCount++;
            startTime=System.currentTimeMillis();
            // System.out.println("OK");
            gra.setColor(Color.WHITE);
            gra.fillRect(0,0,500,500);
            gra.setColor(Color.BLACK);
            for(int i=0;i<playerHP;i++){
                gra.fillRect(i*500/playerMAXHP,430,500/playerMAXHP,30);
            }
            if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)&&playerY<440)playerY+=7;
            if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)&&playerX>0)playerX-=7;
            if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)&&playerX<440)playerX+=7;
            if(Keyboard.isKeyPressed(KeyEvent.VK_UP)&&playerY>0)playerY-=7;
            

            if(Keyboard.isKeyPressed(KeyEvent.VK_V)&&playerInterval==0){
                playercolor+=1;
                if(playercolor==3)playercolor=0;
                playerInterval=10;
            }
            if(playerInterval>0)playerInterval--;

            if(playerInterval>0)playerInterval--;
            if(playercolor==0)gra.setColor(Color.BLUE);
            if(playercolor==1)gra.setColor(Color.RED);
            if(playercolor==2)gra.setColor(Color.GREEN);
            if(PlayerinvincibleTime==0||(PlayerinvincibleTime%8==0&&PlayerinvincibleTime>0)){
                gra.fillRect(playerX+10,playerY,10,10);
                gra.fillRect(playerX,playerY+10,30,10);
                
            }
            if(PlayerinvincibleTime>0){
                PlayerinvincibleTime--;
            }

            if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&bulletInterval==0){
                bullets_player.add(new Bullet(playerX+12,playerY,playercolor));
                bulletInterval=7;
            }
            if(bulletInterval>0)bulletInterval--;

            for(int i=0;i<bullets_player.size();i++){
                Bullet bullet=bullets_player.get(i);
                if(bullet.color==0)gra.setColor(Color.BLUE);
                if(bullet.color==1)gra.setColor(Color.RED);
                if(bullet.color==2)gra.setColor(Color.GREEN);
                gra.fillRect(bullet.x,bullet.y,5,5);
                bullet.y-=10;
                if(bullet.y<-10){
                    bullets_player.remove(i);
                    // i--;
                }

                for(int j=0;j<enemies.size();j++){
                    Enemy enemy =enemies.get(j);
                    if(bullet.x>=enemy.x&&bullet.x<=enemy.x+30&&bullet.y>=enemy.y&&bullet.y<=enemy.y+20){
                        enemies.remove(j);
                        score+=10;
                    }
                }
            }
            if(random.nextInt(level<50?80-level:30)==1)enemies.add(new Enemy(random.nextInt(470),0,random.nextInt(3)));

            for(int i=0;i<enemies.size();i++){
                Enemy enemy =enemies.get(i);
                if(enemy.color==0)gra.setColor(Color.BLUE);
                if(enemy.color==1)gra.setColor(Color.RED);
                if(enemy.color==2)gra.setColor(Color.GREEN);
                gra.fillRect(enemy.x,enemy.y,30,10);
                gra.fillRect(enemy.x+10,enemy.y+10,10,10);
                enemy.y+=5;
                if(enemy.y>500){
                    enemies.remove(i);
                    i--;
                }
                if(PlayerinvincibleTime==0&&((enemy.x>=playerX&&enemy.x<=playerX+30&&enemy.y>=playerY&&enemy.y<=playerY+20)||
                (enemy.x+30>=playerX&&enemy.x+30<=playerX+30&&enemy.y+20>=playerY&&enemy.y+20<=playerY+20))){
                    playerHP--;
                    PlayerinvincibleTime=100;
                    enemies.remove(i);
                    // if(playerHP<=0){
                    //     screen=EnumShootingScreen.GAMEOVER;
                    //     score+=(level-1)*100;
                    // }
                    // screen=EnumShootingScreen.GAMEOVER;
                    
                }
                if(random.nextInt(40)==1)bullets_enemy.add(new Bullet(enemy.x+12,enemy.y,enemy.color));
            }
            //敵の弾処理
            for(int i=0;i<bullets_enemy.size();i++){
                Bullet bullet=bullets_enemy.get(i);
                if(bullet.color==0)gra.setColor(Color.BLUE);
                if(bullet.color==1)gra.setColor(Color.RED);
                if(bullet.color==2)gra.setColor(Color.GREEN);
                gra.fillRect(bullet.x,bullet.y,5,5);
                bullet.y+=10;
                if(bullet.y>500){
                    bullets_enemy.remove(i);
                    i--;
                }
                if(PlayerinvincibleTime==0&&bullet.x>=playerX&&bullet.x<=playerX+30&&bullet.y>=playerY&&bullet.y<=playerY+20&&(playercolor==bullet.color||playercolor==bullet.color+1||(playercolor==0&&bullet.color==2))){
                    if(playercolor==bullet.color){
                        playerHP--;
                    }
                    if(playercolor==bullet.color+1||(playercolor==0&&bullet.color==2)){
                        playerHP-=5;
                    }
                    PlayerinvincibleTime=100;
                    bullets_enemy.remove(i);
                    // if(playerHP<=0){
                    //     screen=EnumShootingScreen.GAMEOVER;
                    //     score+=(level-1)*100;
                    // }
                    
                }
            }

            //処理終了
            shootingFrame.panel.draw();
            try{
                long runTime=System.currentTimeMillis()-startTime;
                if(runTime<(1000/fps)){
                    Thread.sleep((1000/fps)-runTime);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
