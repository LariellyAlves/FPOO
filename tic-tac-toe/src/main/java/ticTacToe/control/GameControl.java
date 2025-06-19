package ticTacToe.control;

import ticTacToe.component.button.ButtonClickEvent;
import ticTacToe.component.button.ButtonClickListener;
import ticTacToe.gui.util.CellClickListener;
import ticTacToe.model.Mark;
import ticTacToe.model.score.ScoreModel;
import ticTacToe.model.table.TableModel;

public class GameControl {
	
	private final TableModel tableModel;
	private final ScoreModel scoreModel;
	private VirtualPlayer virtualPlayer;
	
	private Mark userMark = Mark.X;
	private Mark winner = Mark.BLANK;
	private boolean gameOver = false;
	
	public GameControl(TableModel tableModel,
						ScoreModel scoreModel,
						VirtualPlayer virtualPlayer) {
		
    this.tableModel = tableModel;
	this.scoreModel = scoreModel;
	this.virtualPlayer = virtualPlayer;
	this.tableModel.reset();

	}
	
	
	//--Listening to user plays-----------------------
	public CellClickListener cellClickListener() {
		
	
		return (cell)->{
			if(gameOver)
				return;
			if(tableModel.getMark(cell.lin, cell.col) != Mark.BLANK)
				return;
			doUserPlay(cell.lin, cell.col);
		};
	}
	
	
	private void doVirtualPlay() {
		
		virtualPlayer.play();
		verifyGameOver();
		
		}
	
		private void doUserPlay(int lin, int col) {
			
		tableModel.setMark(lin, col, userMark);
		verifyGameOver();
		
		if(!gameOver)
			doVirtualPlay();
		}
		
		
		private void verifyGameOver() {
			
			verifyWinnerAtLines();
			verifyWinnerAtLines();
			verifyWinnerAtLines();
			verifyWinnerAtLines();
				if(gameOverWithNoWinner())
					setGameOver(Mark.BLANK);
			}
		
		
		
		private boolean gameOverWithNoWinner() {
			
			for(int lin=0; lin<3; lin++) {
				for(int col=0; col<3; col++) {
					if(tableModel.getMark(lin, col) == Mark.BLANK)
						return false;
					}
				}
				return true;
		}
		
		
		

		private Mark getMark(int lin, int col) {
			
			return tableModel.getMark(lin, col);
			
			}
		
		
		private boolean haveWinner(Mark markA, Mark markB, Mark markC) {
			
			return ((markA != null) && (markA != Mark.BLANK) &&
					(markA == markB) && (markA == markC));
			}
		
		
			private void verifyWinnerAtFirstDiagonal() {
				
				if (haveWinner(getMark(0,0), getMark(1,1), getMark(2,2)))
					setGameOver(getMark(0,0));
			}
			
			
			private void verifyWinnerAtSecondDiagonal() {
				if(haveWinner(getMark(0, 2), getMark(1, 1), getMark(2, 0)))
					setGameOver(getMark(1,1));
			}
			
			
		
			private void verifyWinnerAtLines() {
				
				for(int lin=0; lin<3; lin++)
					if(haveWinner(getMark(lin, 0), getMark(lin, 1), getMark(lin, 2)))
						setGameOver(getMark(lin,0));
				}
			
			
			
				private void verifyWinnerAtColumns() {
					
				for(int col=0; col<3; col++)
					if(haveWinner(getMark(0, col), getMark(1, col), getMark(2, col)))
						setGameOver(getMark(0,col));
				}


		//--Listening to user plays------------
		private void setGameOver(Mark winner) {
		
			
		this.winner = winner;
		this.gameOver = true;
		
		if(winner == Mark.O)
			this.scoreModel.incScoreO();
		
		if(winner == Mark.X)
			this.scoreModel.incScoreX();
		}



	//--Listening to new game button------------------
	public ButtonClickListener newGameButtonClickListener() {
	
		return new ButtonClickListener(){
	@Override
	public void onClick(ButtonClickEvent e) {
		tableModel.reset();
		gameOver = false;
		if(winner != userMark)
			virtualPlayer.play();
		
			}
		};
	}
}
