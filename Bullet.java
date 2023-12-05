import java.awt.*;

public class Bullet {
    public int x,y;
    public int color;
    // blue:0,red:1,green:2
    public Bullet(int x,int y,int color){
        this.x=x;
        this.y=y;
        this.color=color;
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
        g.fillRect(x, y, 5, 5);
    }

    public void move() {
        y -= 10; // 弾の移動ロジック
    }

    public boolean isOffScreen() {
        return y < -10; // 画面外に出たかどうかを判定
    }
}
