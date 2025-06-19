package ticTacToe.model.score;

public class SimpleScoreModel implements ReadOnlyScoreModel, ScoreModel {

	private int scoreX = 0;
	private int scoreO = 0;
	
	@Override
	public void incScoreX() {
		scoreX++;
	}

	@Override
	public void incScoreO() {
		scoreO++;
	}

	@Override
	public void reset() {
		scoreX = scoreO = 0;
	}

	@Override
	public int scoreX() {
		return scoreX;
	}

	@Override
	public int scoreO() {
		return scoreO;
	}
}
