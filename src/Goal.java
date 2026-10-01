public class Goal extends Sprite {
    private Boolean scored;

    public Boolean getScored() {
		return scored;
	}

	public void setScored(Boolean scored) {
		this.scored = scored;
	}

	public Goal() {
        super(0, 0, 60, 60, "goal.png");
    }

    public Goal(int x, int y) {
        super(x, y, 60, 60, "goal.png");
        scored = false;
    }
}
