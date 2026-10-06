import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements Runnable, ActionListener {
    private Container content;
    private Sprite background;
    private Frog frogger;
    private Rectangle waterHazard;
    private Log[][] logs;
    private Car[][] cars;
    private Goal[] goals;
    private Sprite[] lives;
    private Sprite gameOverScreen;
    private Sprite winScreen;
    private JButton playBtn;
    private Boolean running;
    private Score scoreboard;
    private Thread t;

    public Main() {
        super("Frogger");
        this.content = getContentPane();
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.background = new Sprite(0, 0, GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT, "background.png");
        this.frogger = new Frog(390, 720, this.content);
        this.waterHazard = new Rectangle(0, 0, GameProperties.SCREEN_WIDTH, 360);

        logs = new Log[5][3];
        for (int r = 0; r < logs.length; r++) for (int c = 0; c < logs[r].length; c++) {
				logs[r][c] = new Log(
					GameProperties.SCREEN_WIDTH /3 *c +120 *r,
					300 -60 *r,
					r %2 > 0? -1 : 1,
					2,
					this.frogger
				);
			}

        cars = new Car[5][3];
		for (int r = 0; r < cars.length; r++) for (int c = 0; c < cars[r].length; c++) {
				cars[r][c] = new Car(
					GameProperties.SCREEN_WIDTH /3 *c +60 *r,
					660 -60 *r,
					r %2 > 0? -1 : 1,
					2,
					(r %4) +1
				);
			}

        goals = new Goal[5];
		for (int g = 0; g < goals.length; g++) goals[g] = new Goal(
			30 +180 *g,
			0
		);

		lives = new Sprite[3];
		for (int l = 0; l < lives.length; l++) lives[l] = new Sprite(
            810 -30 *l,
            750,
            30,
            30,
            "life.png"
		);

		gameOverScreen = new Sprite(
			GameProperties.SCREEN_WIDTH /2 -193,
			GameProperties.SCREEN_HEIGHT /2 -20,
			385, 40, "game_over.png"
		);
		winScreen = new Sprite(
			GameProperties.SCREEN_WIDTH /2 -144,
			GameProperties.SCREEN_HEIGHT /2 -20,
			287, 40, "you_win.png"
		);

        this.content.setFocusable(true);

		this.running = false;

		scoreboard = new Score();

        playBtn = new JButton("START");
        playBtn.setSize(100, 45);
        playBtn.setLocation(370, 420);
        this.content.add(playBtn);
        playBtn.addActionListener(this);
        this.content.add(this.background.lbl);
    }

    public void start() {
        this.running = true;
        this.t = new Thread(this, "Level1");
		this.t.start();
        this.content.removeAll();
		// this.content.add(this.scoreLbl);
		content.add(scoreboard.getContent());
        this.frogger.setMoving(true);
		this.content.add(this.frogger.lbl);
		for (Log[] row : this.logs) for (Log log : row) {
			log.setMoving(true);
			this.content.add(log.lbl);
		}
        for (Car[] row : this.cars) for (Car car : row) {
			car.setMoving(true);
			this.content.add(car.lbl);
        }
        for (Sprite lives : this.lives) this.content.add(lives.lbl);
        this.content.add(this.background.lbl);
        content.repaint();
    }

    public void stop() {
        this.running = false;
        this.frogger.setMoving(false);
		this.content.remove(this.frogger.lbl);
		for (Log[] row : logs) for (Log log : row) {
            log.setMoving(false);
            this.content.remove(log.lbl);
		}
        for (Car[] row : cars) for (Car car : row) {
            car.setMoving(false);
            this.content.remove(car.lbl);
        }
        this.content.repaint();
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
		// int score = 0;
		int lives = 3;

		while(running) {
			frogger.setHealth(1);

			for (Car[] row : cars) for (Car car : row) if (frogger.hitbox.intersects(car.hitbox)) frogger.decreaseHealth();

			for (Log[] row : logs) for (Log log : row) if (frogger.hitbox.intersects(log.hitbox)) frogger.increaseHealth();

			if (frogger.hitbox.intersects(waterHazard)) frogger.decreaseHealth();

			if (frogger.getX() < 0 || frogger.getX() > GameProperties.SCREEN_WIDTH -frogger.getWidth()) frogger.decreaseHealth();

			for (Goal goal : goals) if (frogger.hitbox.intersects(goal.hitbox)) {
				if (!goal.getScored()) {
                    frogger.increaseHealth();
					scoreboard.increaseScore(1);
					content.add(goal.lbl);
					content.setComponentZOrder(goal.lbl, 1);
					goal.setScored(true);

					frogger.setX(goal.getX());
					frogger.setY(goal.getY());
					frogger.setSrc("goal.png");
					resetFrogger();
				}
			}

			if (frogger.getHealth() == 0) {
				lives--;
				content.remove(this.lives[lives].lbl);
				content.repaint();
				frogger.setSrc("frog_dead.png");
				resetFrogger();
			}

			if (lives == 0) {
				System.out.println("You lose :(");
				content.add(gameOverScreen.lbl);
				content.setComponentZOrder(gameOverScreen.lbl, 0);
				playBtn.setText("REPLAY");
				content.add(playBtn);
				content.setComponentZOrder(playBtn, 0);
				stop();
			}

			if (scoreboard.getScore() == 5) {
				System.out.println("You win!");
				content.add(winScreen.lbl);
				content.setComponentZOrder(winScreen.lbl, 0);
				playBtn.setText("REPLAY");
				content.add(playBtn);
				content.setComponentZOrder(playBtn, 0);
				stop();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playBtn) {
            content.remove(playBtn);
			start();

		} else System.out.println("Unkown command");
	}
}
