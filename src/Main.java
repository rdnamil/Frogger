import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Rectangle;

public class Main extends JFrame implements Runnable {
    private Container content;
    private Sprite background;
    private Frog frogger;
    private Rectangle waterHazard;
    private Log[][] logs = new Log[5][3];
    private Car[][] cars = new Car[5][3];
    private Goal[] goals = new Goal[5];
    private Thread t;

    public Main() {
        super("Frogger");
        this.content = getContentPane();
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		for (int g = 0; g < goals.length; g++) goals[g] = new Goal(
			30 +180 *g,
			0
		);

		this.content.add(this.frogger.lbl);
		for (Log[] row : logs) for (Log log : row) this.content.add(log.lbl);
        for (Car[] row : cars) for (Car car : row) this.content.add(car.lbl);
        this.content.add(this.background.lbl);
		this.content.setFocusable(true);
		t = new Thread(this, "Level1");
		t.start();
    }

    public void resetFrogger() {
		try {
			this.frogger.setMoving(false);
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			this.frogger.setMoving(true);
			e.printStackTrace();

		} catch (Exception e) {
			this.frogger.setMoving(true);
			e.printStackTrace();

		} finally {
			this.frogger.setMoving(true);
			this.frogger.setX(390);
			this.frogger.setY(720);
			this.frogger.setSrc("frog_up.png");
		}
	}

    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }

    @Override
	public void run() {
		Boolean running = true;
		int score = 0;
		int lives = 3;

		while(running) {
			int health = 1;

			for (Car[] row : cars) for (Car car : row) if (frogger.hitbox.intersects(car.hitbox)) health--;

			for (Log[] row : logs) for (Log log : row) if (frogger.hitbox.intersects(log.hitbox)) health++;

			if (frogger.hitbox.intersects(waterHazard)) health--;

			if (frogger.getX() < 0 || frogger.getX() > GameProperties.SCREEN_WIDTH -frogger.getWidth()) health--;

			for (Goal goal : goals) if (frogger.hitbox.intersects(goal.hitbox)) {
				if (goal.getScored()) {
					health--;

				} else {
					health++;
					score++;
					content.add(goal.lbl);
					content.setComponentZOrder(goal.lbl, 0);
					goal.setScored(true);

					frogger.setX(goal.getX());
					frogger.setY(goal.getY());
					frogger.setSrc("goal.png");
					resetFrogger();
				}
			}

			if (health == 0) {
				lives--;
				frogger.setSrc("frog_dead.png");
				resetFrogger();
			}

			if (lives == 0) {
				System.out.println("You lose :(");
				running = false;
			}

			if (score == 5) {
				System.out.println("You win!");
				running = false;
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
