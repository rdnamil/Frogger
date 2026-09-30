import java.awt.Container;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Level1 implements Runnable {
    private Sprite background;
    private Container content;
    private Frog frogger;
    private Rectangle waterHazard;
    private Log[][] logs = new Log[5][3];
    private Car[][] cars = new Car[5][3];

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

        for (int r = 0; r < logs.length; r++) for (int c = 0; c < logs[r].length; c++) {
				logs[r][c] = new Log(
					GameProperties.SCREEN_WIDTH /3 *c +120 *r,
					300 -60 *r,
					r %2 > 0? -1 : 1,
					2,
					this.frogger
				);
			}

		for (int r = 0; r < cars.length; r++) for (int c = 0; c < cars[r].length; c++) {
				cars[r][c] = new Car(
					GameProperties.SCREEN_WIDTH /3 *c +60 *r,
					660 -60 *r,
					r %2 > 0? -1 : 1,
					2,
					(r %4) +1
				);
			}
	}

	public void display() {
        this.content.add(this.frogger.lbl);
		for (Log[] row : logs) for (Log log : row) this.content.add(log.lbl);
        for (Car[] row : cars) for (Car car : row) this.content.add(car.lbl);
        this.content.add(this.background.lbl);
		this.content.setFocusable(true);
		t = new Thread(this, "Level1");
		t.start();
	}

	@Override
	public void run() {
		while(true) {
			int health = 1;

			for (Car[] row : cars) for (Car car : row) if (frogger.hitbox.intersects(car.hitbox)) health--;

			for (Log[] row : logs) for (Log log : row) if (frogger.hitbox.intersects(log.hitbox)) health++;

			if (frogger.hitbox.intersects(waterHazard)) health--;

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
