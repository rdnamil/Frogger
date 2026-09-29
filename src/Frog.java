public class Frog extends Sprite {
    private int dir;

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

    public Frog() {
        super(0, 0, 0, 0, "frog.png");
    }
}
