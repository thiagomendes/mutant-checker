package br.com.mutant.checker.vo;

public class Position {

    public static final int HORIZONTAL_DIRECTION = 0;

    public static final int VERTICAL_DIRECTION = 1;

    public static final int DIAGONAL_DIRECTION_TOP_TO_RIGHT = 2;

    public static final int DIAGONAL_DIRECTION_TOP_TO_LEFT = 3;

    private final int line;

    private final int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
