package br.com.mutant.checker.vo;

public class Position {

    public static final int HORIZONTAL_DIRECTION = 0;

    public static final int VERTICAL_DIRECTION = 1;

    private final int x;

    private final  int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
