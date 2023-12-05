public class Enemy {
    public int x,y,color;
    public Enemy(int x,int y,int color){
        this.x=x;
        this.y=y;
        this.color=color;
    }

    // TODO: 敵のx座標から右に +12 したところから射出するようハードコーディングしている
    public Bullet shoot() {
        return new Bullet(x + 12, y, color );
        // return new FastBullet(x + 12, y, 0);
    }
}