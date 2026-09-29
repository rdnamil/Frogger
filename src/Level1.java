import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Container;

public class Level1 {
    private String bakSrc;
    private JLabel bakLbl;
    private ImageIcon bakImg;
    private Container content;

	public String getBakSrc() {
		return bakSrc;
	}

	public void setBakSrc(String bakSrc) {
		this.bakSrc = bakSrc;
	}

	public JLabel getBakLbl() {
		return bakLbl;
	}

	public void setBakLbl(JLabel bakLbl) {
		this.bakLbl = bakLbl;
	}

	public ImageIcon getBakImg() {
		return bakImg;
	}

	public void setBakImg(ImageIcon bakImg) {
		this.bakImg = bakImg;
	}

	public Container getContent() {
		return content;
	}

	public void setContent(Container content) {
		this.content = content;
	}

	public Level1(Container content) {
        this.content = content;
        this.bakSrc = "level1Background.png";
        this.bakLbl = new JLabel();
        this.bakImg = new ImageIcon(getClass().getResource("assets/" +this.bakSrc));
	}

	public void display() {
        this.bakLbl.setIcon(this.bakImg);
        this.bakLbl.setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
        this.content.add(this.bakLbl);
        this.bakLbl.setLocation(0, 0);
	}
}
