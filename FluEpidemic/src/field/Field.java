package field;

public class Field {
    private int width;
    private int height;
    private Object[][] field;

    public Field(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.field = new Object[height][width];
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

    public Object[][] getField() {
        return field;
    }

    public void setField(Object[][] field) {
        this.field = field;
    }

    public Object place(int row, int col, Object o) {
        Object ret = field[row][col];
        field[row][col] = o;
        return ret;
    }

    public Object get(int row, int col) {
        return field[row][col];
    }

    public Object[] getNeighbour(int row, int col) {
        return null;
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = null;
            }
        }
    }
}
