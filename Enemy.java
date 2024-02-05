// public class Enemy {
//     public int x,y,color;
//     public Enemy(int x,int y,int color){
//         this.x=x;
//         this.y=y;
//         this.color=color;
//     }
// }

import java.awt.*;

public class Enemy {
    public int x,y,color;
    public Enemy(int x,int y,int color){
        this.x=x;
        this.y=y;
        this.color=color;
    }

    public void draw(Graphics g) {
        // 敵の色を設定
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
        // 敵の描画
        g.fillRect(x, y, 30, 10);
        g.fillRect(x + 10, y + 10, 10, 10);
    }

    public void move() {
        y += 5; // 敵の縦方向への移動
    }

    public boolean isOffScreen() {
        return y > 500; // 画面外に出たかどうかを判定
    }


    // TODO: 敵のx座標から右に +12 したところから射出するようハードコーディングしている
    public Bullet shoot() {
        return new Bullet(false, x + 12, y, color );
        // return new FastBullet(x + 12, y, 0);
    }
}

class FastEnemy extends Enemy {
    public FastEnemy(int x, int y, int color) {
        super(x, y, color);
    }

    // @Override
    public void move() { y += 30; }
}


class SwingEnemy extends Enemy {
    public SwingEnemy(int x, int y, int color) {
        super(x, y, color);
    }

    int cnt = 0;

    // @Override
    public void move() {
        if(cnt % 2== 0) {
            x += 2;
        }
        else {
            y += 4;
        }
        cnt++;
    }

    // @Override
    public Bullet shoot() {
        return new FastBullet(false, x + 12, y, color );
    }
}