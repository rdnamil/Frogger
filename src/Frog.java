import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frog extends Sprite implements KeyListener, ActionListener {
    private int dir;
    private Container content;

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

	public Frog(Container content) {
        super(0, 0, 60, 60, "frog.png");
        this.dir = 0;
        this.content = content;
    }

    public Frog(int x, int y, Container content) {
        super(x, y, 60, 60, "frog.png");
        this.dir = 0;
        this.content = content;
        this.content.addKeyListener(this);
    }

    @Override
	public void actionPerformed(ActionEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (this.y -GameProperties.CHARACTER_STEP >= 0) {
					this.y = this.y -GameProperties.CHARACTER_STEP;
					this.setDir(0);
				}
				break;
			case KeyEvent.VK_DOWN:
				if (this.y +GameProperties.CHARACTER_STEP <= GameProperties.SCREEN_HEIGHT -this.height) {
					this.y = this.y +GameProperties.CHARACTER_STEP;
					this.setDir(1);
				}
				break;
			case KeyEvent.VK_LEFT:
				if (this.getX() -GameProperties.CHARACTER_STEP >= 0) {
					this.x = this.getX() -GameProperties.CHARACTER_STEP;
					this.setDir(3);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (this.getX() +GameProperties.CHARACTER_STEP <= GameProperties.SCREEN_WIDTH -this.width) {
					this.x = this.getX() +GameProperties.CHARACTER_STEP;
					this.setDir(4);
				}
				break;
		}

		this.lbl.setLocation(this.getX(), this.y);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
