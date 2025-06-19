package ticTacToe.model.table;

import ticTacToe.model.Mark;

public class SimpleTableModel implements TableModel{
	
	private Mark [] [] table = new Mark[3][3];
	
	public SimpleTableModel() {
		reset();
	}

	@Override
	public Mark getMark(int lin, int col) {
		return table[lin][col];
	}

	@Override
	public void setMark(int lin, int col, Mark mark) {
		table[lin][col] = mark;
		
	}

	@Override
	public void reset() {
		for(int lin = 0; lin < table.length; lin++){
			for(int col = 0; col < table[lin].length; col++) {
				table[lin][col] = Mark.BLANK;
			}
		}
		
	}

}
