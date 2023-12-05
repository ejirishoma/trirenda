// public class App {
//     public static void main(String[] args) throws Exception {
//         System.out.println("Hello, World!");
//     }
    
// }

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class App{
    public static ShootingFrame shootingFrame;
    public static boolean loop;
    public static void main(String args[]){
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
        long levelTimer=0;

        Font font;
        FontMetrics metrics;

        EnumShootingScreen screen=EnumShootingScreen.START;

        int playerX=0,playerY=0;
        int bulletInterval=0,playerInterval=0;
        int playercolor=0;
        int playerHP=0;
        int playerMAXHP=10;
        int invincibleTime=0;
        ArrayList<Bullet> bullets_player=new ArrayList<>();
        ArrayList<Bullet> bullets_enemy=new ArrayList<>();
        ArrayList<Enemy> enemies= new ArrayList<>();
        Player player = new Player(300, 300);
        Random random=new Random();

        int PowerupIs=0;
        
        
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


            switch(screen){
                case START:
                    // ゲーム開始時の画面・ゲーム初期化
                    gra.setColor(Color.BLACK);
                    // gra.fillRect(100,100,100,40);
                    gra.setFont(new Font("SansSerif",Font.PLAIN,20));
                    gra.drawString("TORIRENDA", 30,80);
                    gra.drawString("Please,push the space button!", 30,160);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
                        screen=EnumShootingScreen.GAME;
                        bullets_player=new ArrayList<>();
                        bullets_enemy=new ArrayList<>();
                        enemies= new ArrayList<>();
                        playerX=235;
                        playerY=400;
                        level=0;
                        score=0;
                        playerHP=playerMAXHP;
                        invincibleTime=0;
                    }
                    break;
                case GAME:
                //ゲーム開始 
                if(System.currentTimeMillis()-levelTimer>10*1000){
                    levelTimer=System.currentTimeMillis();
                    level++;
                }
                    if(playercolor==0)gra.setColor(Color.BLUE);
                    if(playercolor==1)gra.setColor(Color.RED);
                    if(playercolor==2)gra.setColor(Color.GREEN);

                    // 被弾した際に自機が点滅するよう描画
                    if(invincibleTime==0||(invincibleTime%8==0&&invincibleTime>0)){
                        gra.fillRect(playerX+10,playerY,10,10);
                        gra.fillRect(playerX,playerY+10,30,10);
                        
                    }
                    if(invincibleTime>0){
                        invincibleTime--;
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

                        for(int j=0;j<enemies.size();j++){
                            Enemy enemy =enemies.get(j);
                            if(bullet.x>=enemy.x&&bullet.x<=enemy.x+30&&bullet.y>=enemy.y&&bullet.y<=enemy.y+20){
                                enemies.remove(j);
                                score+=10;
                            }
                        }


                    }



                    // gra.setColor(Color.RED);
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
                        if(invincibleTime==0&&((enemy.x>=playerX&&enemy.x<=playerX+30&&enemy.y>=playerY&&enemy.y<=playerY+20)||
                        (enemy.x+30>=playerX&&enemy.x+30<=playerX+30&&enemy.y+20>=playerY&&enemy.y+20<=playerY+20))){
                            playerHP--;
                            invincibleTime=100;
                            enemies.remove(i);
                            if(playerHP<=0){
                                screen=EnumShootingScreen.GAMEOVER;
                                score+=(level-1)*100;
                            }
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
                        if(invincibleTime==0&&bullet.x>=playerX&&bullet.x<=playerX+30&&bullet.y>=playerY&&bullet.y<=playerY+20&&(playercolor==bullet.color||playercolor==bullet.color+1||(playercolor==0&&bullet.color==2))){
                            if(playercolor==bullet.color){
                                playerHP--;
                            }
                            if(playercolor==bullet.color+1||(playercolor==0&&bullet.color==2)){
                                playerHP-=5;
                            }
                            invincibleTime=100;
                            bullets_enemy.remove(i);
                            if(playerHP<=0){
                                screen=EnumShootingScreen.GAMEOVER;
                                score+=(level-1)*100;
                            }
                            
                        }
                    }
                    //色変え処理
                    // if(Keyboard.isKeyPressed(KeyEvent.VK_X)&&playercolor==0)playercolor=1;
                    // if(Keyboard.isKeyPressed(KeyEvent.VK_V)&&playercolor==1)playercolor=2;
                    // if(Keyboard.isKeyPressed(KeyEvent.VK_V)&&playercolor==2)playercolor=0;
                    if(Keyboard.isKeyPressed(KeyEvent.VK_V)&&playerInterval==0){
                        playercolor+=1;
                        if(playercolor==3)playercolor=0;
                        playerInterval=10;
                    }
                    if(playerInterval>0)playerInterval--;

                    // 自機を上下左右へ移動する操作
                    if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) player.moveLeft();
                    if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) player.moveRight();
                    if(Keyboard.isKeyPressed(KeyEvent.VK_UP)) player.moveUp();
                    if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) player.moveDown();

                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&bulletInterval==0){
                        bullets_player.add(player.shoot());
                        bulletInterval=7;
                    }
                    if(bulletInterval>0)bulletInterval--;

                    // 画面下部のHPゲージの表示
                    gra.setColor(Color.RED);
                    for(int i=0;i<playerHP;i++){
                        gra.fillRect(i*500/playerMAXHP,430,500/playerMAXHP,30);
                    }
                    {
                        gra.setColor(Color.BLACK);
                        font =new Font("SansSerif",Font.PLAIN,20);
                        metrics=gra.getFontMetrics(font);
                        gra.setFont(font);
                        gra.drawString("SCORE:"+score, 470-metrics.stringWidth("SCORE:"+score),430);
                        gra.drawString("LEVEL:"+level, 470-metrics.stringWidth("LEVEL:"+level),460);
                    }

                    

                    break;
                case GAMEOVER:
                    gra.setColor(Color.BLACK);
                    // gra.fillRect(100,100,100,40);
                    gra.setFont(new Font("SansSerif",Font.PLAIN,20));
                    gra.drawString("GAME OVER!!!", 30,80);
                    gra.drawString("SCORE:"+score, 30,120);
                    gra.drawString("Please,push the ESC button to return the title!", 30,160);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)){
                        screen=EnumShootingScreen.START;
                    }
                    break;
            }


            // gra.setColor(Color.BLACK);
            // gra.fillRect(100,100,100,100);

            gra.setColor(Color.BLACK);
            gra.setFont(new Font("SansSerif",Font.PLAIN,10));
            gra.drawString(FPS+"FPS", 0,450);

            player.draw(gra);
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