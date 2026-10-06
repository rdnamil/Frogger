import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String src;
    protected Rectangle hitbox;
    protected JLabel lbl;
    protected Boolean moving;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.hitbox.x = x;
		this.lbl.setLocation(this.x, this.y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.hitbox.y = y;
		this.lbl.setLocation(this.x, this.y);
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
		this.lbl.setIcon(new ImageIcon(getClass().getResource("assets/" +this.src)));
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public JLabel getLbl() {
		return lbl;
	}

	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}

	public Sprite() {
        super();
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.src = "";
        this.hitbox = new Rectangle(x, y, width, height);
        this.lbl = new JLabel();
        this.moving = false;
	}

	public Sprite(int x, int y, int width, int height, String src) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.src = src;
		this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
        this.lbl = new JLabel();
        this.lbl.setIcon(new ImageIcon(getClass().getResource("assets/" +this.src)));
        this.lbl.setSize(this.width, this.height);
        this.lbl.setLocation(this.x, this.y);
        this.moving = false;
	}
}
