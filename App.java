// public class App {
//     public static void main(String[] args) throws Exception {
//         System.out.println("Hello, World!");
//     }
    
// }

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
public class App{
    public static ShootingFrame shootingFrame;
    public static Stage stage;
    public static boolean loop;
    public static void main(String args[])throws IOException{
        shootingFrame=new ShootingFrame();

        loop=true;

        Graphics gra=shootingFrame.panel.image.getGraphics();
        
        //変数宣言
        long startTime;
        long fpsTime=0;
        int fps=30;
        int FPS=0;
        int FPSCount=0;
        int score=0;
        int level=0;
        int selectMode=-1;
        long levelTimer=0;
        Font font;
        FontMetrics metrics;

        EnumShootingScene scene=EnumShootingScene.TITLE;

        int playerX=0,playerY=0;
        int otherX=0,otherY=0;
        int bulletInterval=0,playerInterval=0,otherInterval=0;
        int playercolor=0;
        int othercolor=0;
        int playerHP=0;
        int otherHP=0;
        int playerMAXHP=10;
        int otherMAXHP=10;
        int PlayerinvincibleTime=0;
        int OtherinvincibleTime=0;
        ArrayList<Bullet> bullets_player=new ArrayList<>();
        ArrayList<Bullet> bullets_other=new ArrayList<>();
        // ArrayList<Enemy> enemies= new ArrayList<>();
        Random random=new Random();

        int PowerupIs=0;
        // Image img=

        File cat=new File("./src/cat.png");
        BufferedImage img = ImageIO.read(cat);

        // BufferedImage img = ImageIO.read(new File("C:\\Users\\ejiri\\OneDrive\\デスクトップ\\Torirenda\\src\\cat.png"));
        // BufferedImage img = ImageIO.read(new File("\\WEB-INF\\cat.png"));
        BufferedImage simg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        simg.createGraphics().drawImage(img.getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING),0, 0, 200, 200, null);
        // BufferedImage img = ImageIO.read(new File("./cat.png"));
        
        while(loop){
            if(System.currentTimeMillis()-fpsTime>=1000){
                fpsTime=System.currentTimeMillis();
                FPS=FPSCount;
                FPSCount=0;
                System.out.println(FPS);
            }
            FPSCount++;
            startTime=System.currentTimeMillis();

            gra.setColor(Color.WHITE);

            gra.fillRect(0,0,500,500);
            gra.drawImage(simg, 280,260,null);

            switch(scene){
                case TITLE:
                    // ゲーム開始時の画面・ゲーム初期化
                    gra.setColor(Color.BLACK);
                    // gra.fillRect(100,100,100,40);
                    gra.setFont(new Font("SansSerif",Font.PLAIN,20));
                    gra.drawString("TORIRENDA", 30,80);
                    gra.drawString("Please,push the space button.", 30,160);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
                        scene=EnumShootingScene.START;
                    }
                    break;
                case START:
                //ゲーム開始
                    // ゲーム開始時の画面・ゲーム初期化
                    gra.setColor(Color.BLACK);
                    // gra.fillRect(100,100,100,40);
                    gra.setFont(new Font("SansSerif",Font.PLAIN,20));
                    gra.drawString("TORIRENDA", 30,80);
                    gra.drawString("Please,select the mode.", 30,160);
                    // gra.drawString("SINGLE PLAY", 30,200);
                    // gra.drawString("MULTI PLAY", 30,240);
                    
                    if(selectMode==-1){
                        gra.drawString("SINGLE PLAY", 30,200);
                        gra.drawString("MULTI PLAY", 30,240);
                    }

                    if(selectMode==0){
                        gra.drawString("→SINGLE PLAY", 30,200);
                        gra.drawString("MULTI PLAY", 30,240);
                    }

                    if(selectMode==1){
                        gra.drawString("SINGLE PLAY", 30,200);
                        gra.drawString("→MULTI PLAY", 30,240);
                    }
                    if(Keyboard.isKeyPressed(KeyEvent.VK_UP)){
                        selectMode=0;
                    }
                    if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)){
                        selectMode=1;
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&selectMode==0){
                        scene=EnumShootingScene.SINGLEPLAY;
                        bullets_player=new ArrayList<>();
                        bullets_other=new ArrayList<>();
                        // enemies= new ArrayList<>();
                        playerX=235;
                        playerY=400;
                        otherX=235;
                        otherY=50;
                        level=0;
                        score=0;
                        playerHP=playerMAXHP;
                        otherHP=otherMAXHP;
                        PlayerinvincibleTime=0;
                        OtherinvincibleTime=0;
                        selectMode=-1;
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&selectMode==1){
                        scene=EnumShootingScene.MULTIPLAY;
                        bullets_player=new ArrayList<>();
                        bullets_other=new ArrayList<>();
                        // enemies= new ArrayList<>();
                        playerX=235;
                        playerY=400;
                        otherX=235;
                        otherY=50;
                        level=0;
                        score=0;
                        playerHP=playerMAXHP;
                        otherHP=otherMAXHP;
                        PlayerinvincibleTime=0;
                        OtherinvincibleTime=0;
                        selectMode=-1;
                    }
                           
                    break;
                case SINGLEPLAY:
                    // stage=new Stage(playerX,playerY,playerMAXHP,gra,shootingFrame);
                    stage=new Stage(playerMAXHP,gra,shootingFrame);
                    stage.run();
                    scene=EnumShootingScene.GAMEOVER;
                case MULTIPLAY:
                    //対戦で実際に扱うところ
                    //受信してこっちで処理を行って欲しい。

                    //体力バー
                    gra.setColor(Color.BLACK);
                    for(int i=0;i<playerHP;i++){
                        gra.fillRect(i*500/playerMAXHP,430,500/playerMAXHP,30);
                    }

                    gra.setColor(Color.BLACK);
                    for(int i=0;i<otherHP;i++){
                        gra.fillRect(i*500/otherMAXHP,0,500/otherMAXHP,30);
                    }
                    //ここまで

                    // プレイヤーとその他との動きのプログラム、この部分を変更すれば切り替わる。
                    //今はサンプルでランダムに動かすプログラムを入れてる。
                    //playerX,Y:自機の座標,otherX,Y:相手の座標
                    //playercolor:自機色,othercolor:相手色
                    if(random.nextInt(40)==1)playercolor=random.nextInt(3);
                    if(random.nextInt(40)==1)othercolor=random.nextInt(3);
                    



                    //random.nextInt(40)==1がtrueなら発砲
                    if(random.nextInt(40)==1&&playerInterval==0){
                        bullets_player.add(new Bullet(true,playerX+12,playerY,playercolor));
                        playerInterval=7;
                    }

                    //random.nextInt(40)==1がtrueなら発砲
                    if(random.nextInt(40)==1&&otherInterval==0){
                        bullets_other.add(new Bullet(false,otherX+12,otherY,othercolor));
                        otherInterval=7;
                    }

                    //自機(右，左，上，下)
                    if(random.nextInt(10)==1&&playerX>0)playerX-=7;
                    if(random.nextInt(10)==1&&playerX<440)playerX+=7;
                    if(random.nextInt(10)==1&&playerY>0)playerY-=7;
                    if(random.nextInt(10)==1&&playerY<440)playerY+=7;
                    
                    //相手(右，左，上，下)
                    if(random.nextInt(10)==1&&otherX>0)otherX-=7;
                    if(random.nextInt(10)==1&&otherX<440)otherX+=7;
                    if(random.nextInt(10)==1&&otherY>0)otherY-=7;
                    if(random.nextInt(10)==1&&otherY<440)otherY+=7;

                    //コメントアウト外せば操作出来るようになる
                    // if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)&&playerX>0)playerX-=7;
                    // if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)&&playerX<440)playerX+=7;
                    // if(Keyboard.isKeyPressed(KeyEvent.VK_UP)&&playerY>0)playerY-=7;
                    // if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)&&playerY<440)playerY+=7;
                    
                    
                    //ここから先ゲームの処理

                    if(playerInterval>0)playerInterval--;
                    if(otherInterval>0)otherInterval--;

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
                    //自機の弾処理
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
                        if(OtherinvincibleTime==0&&bullet.x>=otherX&&bullet.x<=otherX+30&&bullet.y>=otherY&&bullet.y<=otherY+20&&(othercolor==bullet.color||othercolor==bullet.color+1||(othercolor==0&&bullet.color==2))){
                            if(othercolor==bullet.color){
                                otherHP--;
                            }
                            if(othercolor==bullet.color+1||(othercolor==0&&bullet.color==2)){
                                otherHP-=5;
                            }
                            OtherinvincibleTime=100;
                            bullets_player.remove(i);
                            if(otherHP<=0){
                                scene=EnumShootingScene.PLAYERWIN;
                            }
                            
                        }
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&bulletInterval==0){
                        bullets_player.add(new Bullet(true,playerX+12,playerY,playercolor));
                        bulletInterval=7;
                    }
                    if(bulletInterval>0)bulletInterval--;



                    if(othercolor==0)gra.setColor(Color.BLUE);
                    if(othercolor==1)gra.setColor(Color.RED);
                    if(othercolor==2)gra.setColor(Color.GREEN);
                    if(OtherinvincibleTime==0||(OtherinvincibleTime%8==0&&OtherinvincibleTime>0)){
                        gra.fillRect(otherX+10,otherY+10,10,10);
                        gra.fillRect(otherX,otherY,30,10);
                    }
                    if(OtherinvincibleTime>0){
                        OtherinvincibleTime--;
                    }


                    //相手の弾処理
                    for(int i=0;i<bullets_other.size();i++){
                        Bullet bullet=bullets_other.get(i);
                        if(bullet.color==0)gra.setColor(Color.BLUE);
                        if(bullet.color==1)gra.setColor(Color.RED);
                        if(bullet.color==2)gra.setColor(Color.GREEN);
                        gra.fillRect(bullet.x,bullet.y,5,5);
                        bullet.y+=10;
                        if(bullet.y>500){
                            bullets_other.remove(i);
                            // i--;
                        }
                        if(PlayerinvincibleTime==0&&bullet.x>=playerX&&bullet.x<=playerX+30&&bullet.y>=playerY&&bullet.y<=playerY+20&&(playercolor==bullet.color||playercolor==bullet.color+1||(playercolor==0&&bullet.color==2))){
                            if(playercolor==bullet.color){
                                playerHP--;
                            }
                            if(playercolor==bullet.color+1||(playercolor==0&&bullet.color==2)){
                                playerHP-=5;
                            }
                            PlayerinvincibleTime=100;
                            bullets_other.remove(i);
                            if(playerHP<=0){
                                scene=EnumShootingScene.OTHERWIN;
                            }
                            
                        }
                    }

                    

                    break;
                case PLAYERWIN:
                    gra.setColor(Color.BLACK);
                    // gra.fillRect(100,100,100,40);
                    gra.setFont(new Font("SansSerif",Font.PLAIN,20));
                    gra.drawString("PLAYER1 WIN!!!", 30,80);
                    // gra.drawString("SCORE:"+score, 30,120);
                    gra.drawString("Please,push the ESC button to return the title!", 30,160);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)){
                        scene=EnumShootingScene.TITLE;
                    }
                    break;
                case OTHERWIN:
                    gra.setColor(Color.BLACK);
                    // gra.fillRect(100,100,100,40);
                    gra.setFont(new Font("SansSerif",Font.PLAIN,20));
                    gra.drawString("PLAYER2 WIN!!!", 30,80);
                    gra.drawString("Please,push the ESC button to return the title!", 30,160);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)){
                        scene=EnumShootingScene.TITLE;
                    }
                    break;
                case GAMEOVER:
                    gra.setColor(Color.BLACK);
                    // gra.fillRect(100,100,100,40);
                    gra.setFont(new Font("SansSerif",Font.PLAIN,20));
                    gra.drawString("GAMEOVER!", 30,80);
                    gra.drawString("Please,push the ESC button to return the title!", 30,160);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)){
                        scene=EnumShootingScene.TITLE;
                    }
                    break;
            }


            // gra.setColor(Color.BLACK);
            // gra.fillRect(100,100,100,100);

            gra.setColor(Color.BLACK);
            gra.setFont(new Font("SansSerif",Font.PLAIN,10));
            gra.drawString(FPS+"FPS", 0,450);
            

            shootingFrame.panel.draw();
            try{
                long runTime=System.currentTimeMillis()-startTime;
                if(runTime<(1000/fps)){
                    Thread.sleep((1000/fps)-runTime);
                }
                
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // System.out.println(System.currentTimeMillis()-startTime);
        }
        System.exit(0);
    }
}