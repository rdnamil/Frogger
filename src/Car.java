import java.awt.Container;

public class Car extends Sprite implements Runnable {
    private int dir;
    private Container content;
    private int speed;

    private Thread t;

    public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Container getContent() {
		return content;
	}

	public void setContent(Container content) {
		this.content = content;
	}

	public Car(Container content) {
        super(0, 0, 60, 60, "car_0" +((int)(Math.random() *4) +1) +".png");
        this.dir = 1;
        this.content = content;
        this.speed = 5;
        t = new Thread(this, "Level1");
		t.start();
    }

    public Car(int x, int y, int dir, int speed, Container content) {
        super(x, y, 60, 60, "car_0" +((int)(Math.random() *4) +1) +".png");
        this.dir = dir;
        this.content = content;
        this.speed = speed;
        t = new Thread(this, "Level1");
		t.start();
    }

	@Override
	public void run() {
		while(true) {
            int pos = x + speed *dir;

            if (pos > GameProperties.SCREEN_WIDTH +width) setX(-width);
            else if (pos < -width) setX(GameProperties.SCREEN_WIDTH +width);
            else setX(pos);

            lbl.setLocation(x, y);

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
