
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Stage {
    public ShootingFrame shootingFrame;
    // public int player.x,player.y,playerMAXHP;
    public int playerMAXHP;
    public Graphics gra;
    // public Stage(int player.x,int player.y,int playerMAXHP,Graphics gra,ShootingFrame shootingFrame){
        public Stage(int playerMAXHP,Graphics gra,ShootingFrame shootingFrame){
        // this.player.x=player.x;
        // this.player.y=player.y;
        this.playerMAXHP=playerMAXHP;
        this.gra=gra;
        this.shootingFrame=shootingFrame;
    }
    public void run() throws IOException{
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

        // int player.x=player.x,player.y=player.y;
        int otherX=0,otherY=0;
        int bulletInterval=0,playerInterval=0,otherInterval=0;
        int playercolor=0;
        int othercolor=0;
        
        int otherHP=0;
        int playerMAXHP=10;
        
        int otherMAXHP=10;
        int level=0;
        int PlayerinvincibleTime=0;
        int OtherinvincibleTime=0;
        int bg1=0;
        int bg2=-500;
        long enemyTimer=0;
        int enemyindex=0;
        ArrayList<Bullet> bullets_player=new ArrayList<>();
        // ArrayList<Bullet> bullets_other=new ArrayList<>();
        ArrayList<Bullet> bullets_enemy=new ArrayList<>();
        ArrayList<Enemy> enemies= new ArrayList<>();
        Player player = new Player(235, 400, playercolor);
        player.HP=playerMAXHP;
        // ArrayList<Enemy> enemies= new ArrayList<>();
        Random random=new Random();
        File f=new File("./src/purotoku3.csv");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        int[][] stage_map=new int[6000][5];
        // List<Integer> stage_map=new ArrayList<>();
        int looper=0;
        int stage_time=0;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",", 0);
            // System.out.print(data[0] + ",");
            // System.out.print(data[1] + ",");
            // System.out.print(data[2] + ",");
            // System.out.println(data[3]);
            
            for(int i=0;i<data.length;i++){
                
                stage_map[looper][i]=Integer.parseInt(data[i]);
            }
            // System.out.println(data);
            // System.out.print(data[0] + ",");
            // System.out.print(data[1] + ",");
            // System.out.print(data[2] + ",");
            // System.out.println(data[3]);
            // for (String elem : data) {
            //     System.out.println(elem);
            // }
            looper++;
        }
        br.close();
        
        File cat=new File("./src/spacew.jpeg");
        BufferedImage img = ImageIO.read(cat);
        // BufferedImage img = ImageIO.read(new File("C:\\Users\\ejiri\\OneDrive\\デスクトップ\\Torirenda\\src\\cat.png"));
        BufferedImage simg = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        simg.createGraphics().drawImage(img.getScaledInstance(500, 500, Image.SCALE_AREA_AVERAGING),0, 0, 500, 500, null);

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
                
            }
            FPSCount++;
            startTime=System.currentTimeMillis();
            // System.out.println("OK");
            gra.setColor(Color.WHITE);
            gra.fillRect(0,0,500,500);


            gra.drawImage(simg, 0,bg1,null);
            gra.drawImage(simg, 0,bg2,null);
            bg1+=1;
            bg2+=1;
            if(bg1>=500){
                bg1=-500;
            }
            if(bg2>=500){
                bg2=-500;
            }


            gra.setColor(Color.BLACK);
            for(int i=0;i<player.HP;i++){
                gra.fillRect(i*500/playerMAXHP,430,500/playerMAXHP,30);
            }
            // if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)&&player.y<440)player.y+=7;
            // if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)&&player.x>0)player.x-=7;
            // if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)&&player.x<440)player.x+=7;
            // if(Keyboard.isKeyPressed(KeyEvent.VK_UP)&&player.y>0)player.y-=7;
            if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) player.moveLeft();
            if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) player.moveRight();
            if(Keyboard.isKeyPressed(KeyEvent.VK_UP)) player.moveUp();
            if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) player.moveDown();
            
            
            // if(Keyboard.isKeyPressed(KeyEvent.VK_V)&&playerInterval==0){
            //     playercolor+=1;
            //     if(playercolor==3)playercolor=0;
            //     playerInterval=10;
            // }
            if(Keyboard.isKeyPressed(KeyEvent.VK_V)&&playerInterval==0){
                player.changeColor();
                playerInterval=10;
            }
            if(playerInterval>0)playerInterval--;

            if(playerInterval>0)playerInterval--;
            if(playercolor==0)gra.setColor(Color.BLUE);
            if(playercolor==1)gra.setColor(Color.RED);
            if(playercolor==2)gra.setColor(Color.GREEN);
            // if(PlayerinvincibleTime==0||(PlayerinvincibleTime%8==0&&PlayerinvincibleTime>0)){
            //     gra.fillRect(player.x+10,player.y,10,10);
            //     gra.fillRect(player.x,player.y+10,30,10);
                
            // }
            if(PlayerinvincibleTime>0){
                PlayerinvincibleTime--;
            }

            if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&bulletInterval==0){
                // bullets_player.add(new Bullet(player.x+12,player.y,playercolor));
                bullets_player.add(player.shoot());
                bulletInterval=7;
            }
            if(bulletInterval>0)bulletInterval--;

            for(int i=0;i<bullets_player.size();i++){
                Bullet bullet=bullets_player.get(i);
                bullet.draw(gra); // 弾の描画
                
                bullet.move(player.x, player.y); // 弾の移動

                if (bullet.isOffScreen()) {
                    bullets_player.remove(i);
                    i--;
                }
                // if(bullet.color==0)gra.setColor(Color.BLUE);
                // if(bullet.color==1)gra.setColor(Color.RED);
                // if(bullet.color==2)gra.setColor(Color.GREEN);
                // gra.fillRect(bullet.x,bullet.y,5,5);
                // bullet.y-=10;
                // if(bullet.y<-10){
                //     bullets_player.remove(i);
                //     // i--;
                // }

                for(int j=0;j<enemies.size();j++){
                    Enemy enemy =enemies.get(j);
                    if(bullet.x>=enemy.x&&bullet.x<=enemy.x+30&&bullet.y>=enemy.y&&bullet.y<=enemy.y+20){
                        enemies.remove(j);
                        score+=10;
                    }
                }
            }
            // if(random.nextInt(level<50?80-level:30)==1)enemies.add(new Enemy(random.nextInt(470),0,random.nextInt(3)));
            // int[][][] map={{{100,0,0},{200,0,0},{300,0,0},{400,0,0}},{{100,0,1},{200,0,1},{300,0,1},{400,0,1}},{{9}}};
            // if(System.currentTimeMillis()-enemyTimer>10*100){
            // System.out.println(stage_time);
            // System.out.println(enemyindex);
            if(stage_time==stage_map[enemyindex][0]){
                // System.out.println(stage_time);
                // enemyTimer=System.currentTimeMillis();
                // if(map[enemyindex][0][0]!=9){
                //     for(int i=0;i<map[enemyindex].length;i++){
                //         enemies.add(new Enemy(map[enemyindex][i][0],map[enemyindex][i][1],map[enemyindex][i][2]));
                //     }
                // }
                
                while(stage_time==stage_map[enemyindex][0]){
                    if(String.valueOf(stage_map[enemyindex][0])!=null){

                        if(stage_map[enemyindex][3] == 0){
                            enemies.add(new mobEnemy(stage_map[enemyindex][1],stage_map[enemyindex][2],stage_map[enemyindex][3],stage_map[enemyindex][4]));
                        }

                        else if(stage_map[enemyindex][3] == 1){
                            enemies.add(new FastEnemy(stage_map[enemyindex][1],stage_map[enemyindex][2],stage_map[enemyindex][3],stage_map[enemyindex][4]));
                        }

                        else if(stage_map[enemyindex][3] == 2){
                            enemies.add(new SwingEnemy(stage_map[enemyindex][1],stage_map[enemyindex][2],stage_map[enemyindex][3],stage_map[enemyindex][4]));
                        }
                        enemyindex++;
                    }
                }
                enemyindex++;
            }
            if((enemyindex>=27)){
                // System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
                enemyindex=0;
                stage_time=-1;
            }
            // stage_time=0;
            
            for(int i=0;i<enemies.size();i++){
                Enemy enemy =enemies.get(i);
                //System.out.println(enemy);
                enemy.move();
                enemy.draw(gra);
                // if(enemy.color==0)gra.setColor(Color.BLUE);
                // if(enemy.color==1)gra.setColor(Color.RED);
                // if(enemy.color==2)gra.setColor(Color.GREEN);
                // gra.fillRect(enemy.x,enemy.y,30,10);
                // gra.fillRect(enemy.x+10,enemy.y+10,10,10);
                // enemy.y+=5;
                if(enemy.y>500){
                    enemies.remove(i);
                    i--;
                }
                if(PlayerinvincibleTime==0&&((enemy.x>=player.x&&enemy.x<=player.x+30&&enemy.y>=player.y&&enemy.y<=player.y+20)||
                (enemy.x+30>=player.x&&enemy.x+30<=player.x+30&&enemy.y+20>=player.y&&enemy.y+20<=player.y+20))){
                    player.HP--;
                    PlayerinvincibleTime=100;
                    enemies.remove(i);
                    if(player.HP<=0){
                        // screen=EnumShootingScreen.GAMEOVER;
                        // score+=(level-1)*100;
                        return;
                    }
                    // screen=EnumShootingScreen.GAMEOVER;
                    
                }
                if(random.nextInt(40)==1){
                    if(enemy.enemy == 0){
                        bullets_enemy.add(new mobBullet(false,enemy.x+12,enemy.y,enemy.color));
                    }
                    else if(enemy.enemy == 1){
                        bullets_enemy.add(new FastBullet(false,enemy.x+12,enemy.y,enemy.color));
                    }
                    else if(enemy.enemy == 2){
                        bullets_enemy.add(new SwingBullet(false,enemy.x+12,enemy.y,enemy.color));
                    }
                }
            }

            //敵の弾処理
            for(int i=0;i<bullets_enemy.size();i++){
                Bullet bullet=bullets_enemy.get(i);
                // System.out.println(bullet);
                bullet.draw(gra); // 弾の描画
                bullet.move(player.x, player.y); // 弾の移動

                if (bullet.isOffScreen()) {
                    bullets_enemy.remove(i);
                    i--;
                }
                // if(bullet.color==0)gra.setColor(Color.BLUE);
                // if(bullet.color==1)gra.setColor(Color.RED);
                // if(bullet.color==2)gra.setColor(Color.GREEN);
                // gra.fillRect(bullet.x,bullet.y,5,5);
                // bullet.y+=10;
                // if(bullet.y>500){
                //     bullets_enemy.remove(i);
                //     i--;
                // }
                if(PlayerinvincibleTime==0&&bullet.x>=player.x&&bullet.x<=player.x+30&&bullet.y>=player.y&&bullet.y<=player.y+20&&(playercolor==bullet.color||playercolor==bullet.color+1||(playercolor==0&&bullet.color==2))){
                    if(playercolor==bullet.color){
                        player.HP--;
                    }
                    if(playercolor==bullet.color+1||(playercolor==0&&bullet.color==2)){
                        player.HP-=5;
                    }
                    PlayerinvincibleTime=100;
                    bullets_enemy.remove(i);
                    if(player.HP<=0){
                        // screen=EnumShootingScreen.GAMEOVER;
                        // score+=(level-1)*100;
                        return;
                    }
                    
                }
            }
            player.draw(gra, PlayerinvincibleTime);

            //処理終了
            stage_time++;
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
