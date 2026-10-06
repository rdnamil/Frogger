import javax.swing.JPanel;

public class Score {
    private JPanel content;
    private int score;
    private Sprite prefix;
    private Sprite[] scoreboard;

    public JPanel getContent() {
		return content;
	}

	public void setContent(JPanel content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;

		for (int i = 0; i < scoreboard.length; i++) this.scoreboard[i].setSrc("scoreboard/digit_" +String.format("%04d", this.score).charAt(i) +".png");
	}

	public Sprite getPrefix() {
		return prefix;
	}

	public void setPrefix(Sprite prefix) {
		this.prefix = prefix;
	}

	public Sprite[] getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Sprite[] scoreboard) {
		this.scoreboard = scoreboard;
	}

	public Score() {
        score = 0;

        content = new JPanel(null);
        content.setOpaque(false);
        content.setBounds(10, 10, 179, 16);

        prefix = new Sprite(0, 0, 89, 16, "scoreboard/prefix.png");
        content.add(prefix.lbl);
        scoreboard = new Sprite[4];
        for (int i = 0; i < scoreboard.length; i++) {
            scoreboard[i] = new Sprite(
                99 +20 *i,
                0, 20, 16, "scoreboard/digit_" +String.format("%04d", score).charAt(i) +".png"
            );
            content.add(scoreboard[i].lbl);
        }
    }

    public void increaseScore(int i) {
        this.score += i;
    }

    public void decreaseScore(int i) {
        this.score -= i;
    }
}
