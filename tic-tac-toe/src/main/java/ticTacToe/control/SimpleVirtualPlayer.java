package ticTacToe.control;

import ticTacToe.model.Mark;
import ticTacToe.model.table.TableModel;

public class SimpleVirtualPlayer implements VirtualPlayer{
	
	private final TableModel tableModel;
	private final Mark mark;
	
	public SimpleVirtualPlayer(TableModel tableModel, Mark mark) {
		this.tableModel = tableModel;
		this.mark = mark;
	}
	
	@Override
	public void play() {
		
		if(playToWin())
			return;
		
		if(playToNotLost())
			return;
		
		playRandom();
	}
	
	private boolean playToWin() {
		
		for(int lin=0; lin<3; lin++) {
		   for(int col=0; col<3; col++) {
			   if(tableModel.getMark(lin, col) == Mark.BLANK) {
				   if(canWinAtLine(lin, mark) || canWinAtColumn(col, mark)) {
					   tableModel.setMark(lin, col, mark);
						return true;
				   }
			   }
		   }
		}
		
		return false;
	}
	
    private boolean playToNotLost() {
		
    	Mark enemyMark = (mark == Mark.O ? Mark.X : Mark.O);
    	
		for(int lin=0; lin<3; lin++) {
		   for(int col=0; col<3; col++) {
			   if(tableModel.getMark(lin, col) == Mark.BLANK) {
				   if(canWinAtLine(lin, enemyMark) || canWinAtColumn(col, enemyMark)) {
					   tableModel.setMark(lin, col, mark);
						return true;
				   }
			   }
		   }
		}
		
		return false;
	}
	
	private boolean canWinAtLine(int line, Mark mark) {
		
		Mark mark0 = tableModel.getMark(line, 0);
		Mark mark1 = tableModel.getMark(line, 1);
		Mark mark2 = tableModel.getMark(line, 2);
		Mark BLANK = Mark.BLANK;
		
		if((mark0 == BLANK && (mark1 == BLANK || mark2 == BLANK)) ||
		   (mark1 == BLANK && (mark0 == BLANK || mark2 == BLANK)) ||
		   (mark2 == BLANK && (mark0 == BLANK || mark1 == BLANK)) )
			return false;
			
		if ((mark0 != BLANK && mark0 != mark) ||
			(mark1 != BLANK && mark1 != mark) ||
			(mark2 != BLANK && mark2 != mark) )
			return false;
		
		return true;
	}
	
    private boolean canWinAtColumn(int col, Mark mark) {
		
		Mark mark0 = tableModel.getMark(0, col);
		Mark mark1 = tableModel.getMark(1, col);
		Mark mark2 = tableModel.getMark(2, col);
		Mark BLANK = Mark.BLANK;
		
		if((mark0 == BLANK && (mark1 == BLANK || mark2 == BLANK)) ||
		   (mark1 == BLANK && (mark0 == BLANK || mark2 == BLANK)) ||
		   (mark2 == BLANK && (mark0 == BLANK || mark1 == BLANK)) )
			return false;
			
		if ((mark0 != BLANK && mark0 != mark) ||
			(mark1 != BLANK && mark1 != mark) ||
			(mark2 != BLANK && mark2 != mark) )
			return false;
		
		return true;
	}
	
	private void playRandom() {
		
		int x =0;
		int y =0;
		for(int lin=0; lin<3; lin++) {
			for(int col=0; col<3; col++) {
				if(tableModel.getMark(lin, col) == Mark.BLANK) {
					if(Math.random()>0.5 ) {
						tableModel.setMark(lin, col, mark);
						return;
					}
					else {
						x=lin;
						y=col;
					}
				}
			}
		}
		
		tableModel.setMark(x, y, mark);
	}
}
