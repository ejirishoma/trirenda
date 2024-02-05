// public class Bullet {
//     public int x,y;
//     public int color;
//     // blue:0,red:1,green:2
//     public Bullet(int x,int y,int color){
//         this.x=x;
//         this.y=y;
//         this.color=color;
//     }
// }
import java.awt.*;

public class Bullet {
    boolean isPlayer;

    public int x,y;
    public int color;

    // blue:0,red:1,green:2
    public Bullet(boolean ip, int x, int y, int color){
        this.isPlayer=ip;
        this.x=x;
        this.y=y;
        this.color=color;
    }

    public void setPlayerPosition(int playerX, int playerY) {
        this.x = playerX + 12;
        this.y = playerY;
    }

    public void draw(Graphics g) {
        switch (color) {
            case 0:
                g.setColor(Color.BLUE);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.GREEN);
                break;
            default:
                g.setColor(Color.BLACK); // デフォルトの色
        }
        g.fillRect(x, y, 7, 7);
    }

    // 弾の移動ロジック
    public void move(int playerX, int playerY) {
        if(isPlayer) y -= 7;
        else y += 7;
    }

    // 画面外に出たかどうかを判定
    public boolean isOffScreen() {
        if(isPlayer) return y < -10;
        else return y > 500;
    }
}

class mobBullet extends Bullet {
    public mobBullet(boolean ip, int x, int y, int color) {
        super(ip, x, y, color);
    }
}

// 継承元のBulletクラスより三倍速く移動する弾丸を定義
class FastBullet extends Bullet {
    public FastBullet(boolean ip, int x, int y, int color) {
        super(ip, x, y, color);
    }

    @Override
    public void move(int playerX, int playerY) { y += 8; }
}

class SwingBullet extends Bullet {
    public SwingBullet(boolean ip, int x, int y, int color) {
        super(ip, x, y, color);
    }

    @Override
    public void move(int playerX, int playerY) {
        if (y >= playerY){
            y += Math.abs((int)(y - playerY)/(Math.sqrt(Math.pow(x-playerX, 2)+Math.pow(y-playerY, 2))));
        }
        y+=3;
 
        if(x > playerX){
            x -= (int)(x - playerX)/(Math.sqrt(Math.pow(x-playerX, 2)+Math.pow(y-playerY, 2)));
        }
        else if(x <= playerX){
            x += Math.abs((int)(x - playerX)/(Math.sqrt(Math.pow(x-playerX, 2)+Math.pow(y-playerY, 2))));
        }
    }
}
