import java.awt.Rectangle;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String src;
    protected Rectangle hitbox;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.hitbox.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.hitbox.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.hitbox.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
        this.hitbox.height = height;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public Sprite() {
        super();
        this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
	}

	public Sprite(int x, int y, int width, int height, String src) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.src = src;
        this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
	}
}
