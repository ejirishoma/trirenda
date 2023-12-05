import java.awt.*;

public class Player {
    // 自機の位置
    private int x, y;

    // 自機の大きさ
    final static int WIDTH = 20;
    final static int HEIGHT = 20;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
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
        return new Bullet(true, x + 12, y, 0);
        // return new FastBullet(x + 12, y, 0);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
