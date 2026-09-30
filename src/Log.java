public class Log extends Sprite implements Runnable {
    private int dir;
    private Frog frogger;
    private int speed;

    private Thread t;

    public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Frog getFrogger() {
		return frogger;
	}

	public void setFrogger(Frog frogger) {
		this.frogger = frogger;
	}

	public Log(Frog frogger) {
        super(0, 0, 150, 60, "log.png");
        this.dir = 1;
		this.speed = 2;
        this.frogger = frogger;
        t = new Thread(this, "Level1");
		t.start();
    }

    public Log(int x, int y, int dir, int speed, Frog frogger) {
        super(x, y, 150, 60, "log.png");
        this.dir = dir;
		this.speed = speed;
        this.frogger = frogger;
        t = new Thread(this, "Level1");
		t.start();
    }

	@Override
	public void run() {
		while(true) {

            if (hitbox.intersects(frogger.hitbox)) {
                frogger.setX(frogger.getX() +speed *dir);
                frogger.lbl.setLocation(frogger.getX(), frogger.getY());
            }

            int pos = x + speed *dir;

            if (pos > GameProperties.SCREEN_WIDTH +width) setX(-width);
            else if (pos < -width) setX(GameProperties.SCREEN_WIDTH +width);
            else setX(pos);

			try {
				Thread.sleep(17);

			} catch (InterruptedException e) {
				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();

			} finally {

			}
		}
	}
}
