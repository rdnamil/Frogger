public class Car extends Sprite implements Runnable {
    private int dir;
    private int speed;

    private Thread t;

    public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
		t = new Thread(this, "Car");
		t.start();
	}

	public Car() {
        super(0, 0, 60, 60, "car_01.png");
        this.dir = 1;
        this.speed = 5;
        t = new Thread(this, "Car");
		t.start();
    }

    public Car(int x, int y, int dir, int speed, int car) {
        super(x, y, 60, 60, "car_0" +car +".png");
        this.dir = dir;
        this.speed = speed;
        t = new Thread(this, "Car");
		t.start();
    }

	@Override
	public void run() {
		while(moving) {
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
