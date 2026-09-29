import java.awt.Container;

public class Log extends Sprite {
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

	public Log(Container content) {
        super(0, 0, 150, 60, "log.png");
        dir = 0;
        this.content = content;
    }

    public Log(int x, int y, Container content) {
        super(x, y, 150, 60, "log.png");
        dir = 0;
        this.content = content;
    }
}
