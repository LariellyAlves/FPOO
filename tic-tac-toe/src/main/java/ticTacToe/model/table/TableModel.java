package ticTacToe.model.table;

import ticTacToe.model.Mark;

public interface TableModel extends ReadOnlyTableModel {
	void setMark(int lin, int col, Mark mark);
	void reset();
}
