public class Frog extends Sprite {
    private int dir;

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

    public Frog() {
        super(0, 0, 60, 60, "frog.png");
        this.dir = 0;
    }

    public Frog(int x, int y) {
        super(x, y, 60, 60, "frog.png");
        this.dir = 0;
    }
}
