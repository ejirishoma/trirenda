import java.awt.*;

public class Player {
    // 自機の位置
    public int x, y, color, HP;

    // 自機の大きさ
    final static int WIDTH = 20;
    final static int HEIGHT = 20;

    public Player(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.color = c;
    }

    public void moveLeft() {
        if (x > 0) {
            x -= 7;
        }
    }

    // TODO: 現在はステージの横幅を 500 とハードコーディングしている
    // TODO: そのため，のちのちplayerクラスにStageクラスを追加し，ステージの縦横の幅を取得できるようにする必要がある
    public void moveRight() {
        if (x < 440) {
            // System.err.println("Hello");
            x += 7;
        }
    }

    // TODO: moveRightと同様にステージの縦横を 500 とハードコーディングしている
    public void moveDown() {
        if (y < 440) {
            y += 7;
        }
    }

    public void moveUp() {
        if (y > 0) {
            y -= 7;
        }
    }

    // TODO: 自機のx座標から右に +12 したところから射出するようハードコーディングしている
    public Bullet shoot() {
        Bullet bullet = new Bullet(true, x, y, color);
        bullet.setPlayerPosition(x, y); // 新しいメソッドを呼び出す
        return bullet;
        // return new FastBullet(x + 12, y, 0);
    }

    public void  changeColor() {
        color+=1;
        if(color==3)color=0;
    }

    public void draw(Graphics g, int invincibleTime) {
        if (invincibleTime > 0 && invincibleTime % 8 != 0) return;

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
                g.setColor(Color.BLACK);
        }

        g.fillRect(x+10,y,10,10);
        g.fillRect(x,y+10,30,10);
    }

    
}