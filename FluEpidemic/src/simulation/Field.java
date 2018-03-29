package simulation;

import java.util.ArrayList;

/**
 * @author ZHONG Ming
 */
import beings.Being;

public class Field {
    private int width;
    private int height;
    private Being[][] field;

    public Field(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.field = new Being[height][width];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Being[][] getField() {
        return field;
    }

    public void setField(Being[][] field) {
        this.field = field;
    }

    public Being place(int row, int col, Being o) {
        Being ret = field[row][col];
        field[row][col] = o;
        return ret;
    }

    public Being get(int row, int col) {
        return field[row][col];
    }

    public Being[] getNeighbour(int row, int col) {
        ArrayList<Being> list = new ArrayList<Being>();
        for ( int i=-1; i<2; i++ ) {
            for ( int j=-1; j<2; j++ ) {
                int r = row+i;
                int c = col+j;
                if ( r >-1 && r<height && c>-1 && c<width && !(r== row && c == col) ) {
                    list.add(field[r][c]);
                }
            }
        }
        return list.toArray(new Being[list.size()]);
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = null;
            }
        }
    }
}
