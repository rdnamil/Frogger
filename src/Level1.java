import java.awt.Container;

public class Level1 {
    private Sprite background;
    private Container content;
    private Frog frogger;

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
        this.frogger = new Frog(390, 720);
	}

	public void display() {
        this.content.add(this.frogger.lbl);
        this.frogger.lbl.setLocation(this.frogger.x, this.frogger.y);
        this.content.add(this.background.lbl);
	}
}
