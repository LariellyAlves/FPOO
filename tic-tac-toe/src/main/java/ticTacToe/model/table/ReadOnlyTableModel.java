package ticTacToe.model.table;

import ticTacToe.model.Mark;

public interface ReadOnlyTableModel {
	
	Mark getMark(int lin, int col);

}
