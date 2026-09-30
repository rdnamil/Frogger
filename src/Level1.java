import java.awt.Container;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Level1 implements Runnable {
    private Sprite background;
    private Container content;
    private Frog frogger;
    private Rectangle waterHazard;
    private Log log;
    private Car[] cars = new Car[3];

    private Thread t;

	public Sprite getBackground() {
		return background;
	}

	public void setBackground(Sprite background) {
		this.background = background;
	}

	public Container getContent() {
		return content;
	}

	public void setContent(Container content) {
		this.content = content;
	}

	public Frog getFrogger() {
		return frogger;
	}

	public void setFrogger(Frog frogger) {
		this.frogger = frogger;
	}

	public Level1(Container content) {
        this.content = content;
        this.background = new Sprite(0, 0, GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT, "level1Background.png");
        this.frogger = new Frog(390, 720, this.content);
        this.waterHazard = new Rectangle(0, 0, GameProperties.SCREEN_WIDTH, 360);
        this.log = new Log(0, 300, 1, 2, this.content, this.frogger);
        // this.car = new Car(0, 660, 1, 10, this.content);

        // init cars
        for (int c = 0; c < cars.length; c++) {
			int x = GameProperties.SCREEN_WIDTH /3;

			cars[c] = new Car(x *c, 660, 1, 3, this.content);
        }
	}

	public void display() {
        this.content.add(this.frogger.lbl);
        this.content.add(this.log.lbl);
        // this.content.add(this.car.lbl);
        for (Car car : cars) this.content.add(car.lbl);
        this.content.add(this.background.lbl);
		this.content.setFocusable(true);
		t = new Thread(this, "Level1");
		t.start();
	}

	@Override
	public void run() {
		while(true) {
			int health = 1;

			// if (frogger.hitbox.intersects(car.hitbox)) health--;

			if (frogger.hitbox.intersects(waterHazard)) health--;

			if (frogger.hitbox.intersects(log.hitbox)) health++;

			if (frogger.getX() < 0 || frogger.getX() > GameProperties.SCREEN_WIDTH -frogger.getWidth()) health--;

			if (health <= 0) {
				frogger.setSrc("frog_dead.png");
				frogger.lbl.setIcon(new ImageIcon(getClass().getResource("assets/" +frogger.src)));
			}

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
