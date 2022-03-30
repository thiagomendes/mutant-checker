package br.com.mutant.checker.domain.vo;

public class Position {

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

    @Override
    public String toString() {
        return "Position{line=" + line + ", column= " + column + "}";
    }
}
