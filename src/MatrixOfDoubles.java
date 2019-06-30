public class MatrixOfDoubles {
    private double[][] matrix;

    public MatrixOfDoubles(int numberOfRows, int numberOfColumns) {
        matrix = new double[numberOfRows][numberOfColumns];
    }

    public void setElementAt(int rowIndex, int columnIndex, double element) {
        matrix[rowIndex][columnIndex] = element;
    }

    public double getElementAt(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex];
    }

    public int getNumberOfRows() {
        return matrix.length;
    }

    public int getNumberOfColumns() {
        return matrix[0].length;
    }
}
