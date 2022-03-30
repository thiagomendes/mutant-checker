package br.com.mutant.checker.helper;

public class MutantCheckerTestHelper {

    public static char[][] getHumanDnaMatrixWithSixPositionsCharArray() {
        return new char[][]{
                "TTGCTA".toCharArray(),
                "CAGTGC".toCharArray(),
                "TTATGT".toCharArray(),
                "AGAAGG".toCharArray(),
                "CCCATA".toCharArray(),
                "TCACTG".toCharArray()
        };
    }

    public static String[] getHumanDnaMatrixWithSixPositionsStringArray() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCATA",
                "TCACTG"
        };
    }

    public static String[] getMutantDnaMatrixInHorizontalPosition() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
    }

    public static String[] getMutantDnaMatrixInVerticalPosition() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCAGA",
                "TCACTG"
        };
    }

    public static String[] getMutantDnaMatrixInTopToRightPosition() {
        return new String[]{
                "TTGCTA",
                "CATTGC",
                "TTATGT",
                "AGAATG",
                "CCCATA",
                "TCACTG"
        };
    }

    public static String[] getMutantDnaMatrixInTopToLeftPosition() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "TTTTGT",
                "ATAAGG",
                "CCCATA",
                "TCACTG"
        };
    }

    public static String[] getMutantDnaMatrixInBottomToLeftPosition() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "CTATGT",
                "ACAAGG",
                "CCCATA",
                "TCACTG"
        };
    }

    public static String[] getMutantDnaMatrixInBottomToRightPosition() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "TTATGA",
                "AGAAAG",
                "CCCATA",
                "TCACTG"
        };
    }

    public static String[] getDnaWithInvalidChar() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "TTATGT",
                "AGAXGG",
                "CCCATA",
                "TCACTG"
        };
    }

    public static String[] getInvalidTable() {
        return new String[]{
                "TTGCTA",
                "CAGTGC",
                "TTATGTX",
                "AGAAGG",
                "CCCATA",
                "TCACTG"
        };
    }
}
