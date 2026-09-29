import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Color;

public class Main extends JFrame {
    private Container content;
    private Level1 level1;

    public Main() {
        super("Frogger");
        this.content = getContentPane();
		this.content.setBackground(Color.gray);
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.level1 = new Level1(this.content);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
        main.level1.display();
    }
}
