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
	private Rectangle goal;
    private Log[][] logs;
    private Car[][] cars;
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

        background = new Sprite(0, 0, GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT, "background.png");
        frogger = new Frog(390, 720, content);
        waterHazard = new Rectangle(0, 0, GameProperties.SCREEN_WIDTH, 360);
		goal = new Rectangle(0, 0, GameProperties.SCREEN_WIDTH, 60);
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

		content.add(scoreboard.getContent());
		this.content.add(this.frogger.lbl);
		for (Log[] row : this.logs) for (Log log : row) this.content.add(log.lbl);
        for (Car[] row : this.cars) for (Car car : row) this.content.add(car.lbl);
        this.content.add(this.background.lbl);
    }

    public void start() {
        this.running = true;
        this.scoreboard.setScore(0);
        this.t = new Thread(this, "Level1");
		this.t.start();
        this.frogger.setMoving(true);
		for (Log[] row : this.logs) for (Log log : row) log.setMoving(true);
        for (Car[] row : this.cars) for (Car car : row) car.setMoving(true);
    }

    public void stop() {
        this.running = false;
        this.frogger.setMoving(false);
		for (Log[] row : logs) for (Log log : row) log.setMoving(false);
        for (Car[] row : cars) for (Car car : row) car.setMoving(false);
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

		while(running) {
			frogger.setHealth(1);

			for (Car[] row : cars) for (Car car : row) if (frogger.getHitbox().intersects(car.getHitbox())) frogger.decreaseHealth();

			for (Log[] row : logs) for (Log log : row) if (frogger.getHitbox().intersects(log.getHitbox())) frogger.increaseHealth();

			if (frogger.getHitbox().intersects(waterHazard)) frogger.decreaseHealth();

			if (frogger.getX() < 0 || frogger.getX() > GameProperties.SCREEN_WIDTH -frogger.getWidth()) frogger.decreaseHealth();

			if (frogger.getHitbox().intersects(goal)) {
				frogger.increaseHealth();
				scoreboard.increaseScore(50);
				resetFrogger();

			}

			if (frogger.getHealth() == 0) {
				frogger.setSrc("frog_dead.png");
				resetFrogger();
			}

			if (scoreboard.getScore() == 250) {
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
