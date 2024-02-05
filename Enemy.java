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
    public int x,y,enemy,color;
    public Enemy(int x,int y,int enemy, int color){
        this.x=x;
        this.y=y;
        this.enemy = enemy;
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
    //public Bullet shoot() {
    //    return new Bullet(false, x + 12, y, color );
        // return new FastBullet(x + 12, y, 0);
    //}
    
}

class mobEnemy extends Enemy {
    public mobEnemy(int x, int y, int enemy, int color) {
        super(x, y, enemy, color);
    }

    // @Override
    public Bullet mobBullet() {
        return new mobBullet(false, x + 12, y, color );
    }
}

class FastEnemy extends Enemy {
    public FastEnemy(int x, int y, int enemy, int color) {
        super(x, y, enemy, color);
    }

    public Bullet FastBullet() {
        return new FastBullet(false, x + 12, y, color);
    }

    @Override
    public void move() { y += 8; }
}


class SwingEnemy extends Enemy {
    public SwingEnemy(int x, int y, int enemy, int color) {
        super(x, y, enemy, color);
    }

    //int cnt = 0;

    // @Override
    //public void move() {
    //    if(cnt % 2== 0) {
    //        x += 5;
    //    }
    //    else {
    //        y += 5;
    //    }
    //    cnt++;
    //}

    // @Override
    public Bullet SwingBullet() {
        return new SwingBullet(false, x + 12, y, color);
    }
}