public class CalculatorOfElementsFromResultingMatrix {
    private MatrixOfDoubles leftMatrix;
    private MatrixOfDoubles rightMatrix;
    private MatrixOfDoubles resultingMatrix;

    public CalculatorOfElementsFromResultingMatrix(MatrixOfDoubles leftMatrix, MatrixOfDoubles rightMatrix, MatrixOfDoubles resultingMatrix) {
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
        this.resultingMatrix = resultingMatrix;
    }

    public void calculateScalarMultiplication(int rowIndexFromLeftMatrix, int columnIndexFromRightMatrix) {
        double accumulatedResult = 0;
        for (int i = 0; i < leftMatrix.getNumberOfColumns(); i++) {
            accumulatedResult += leftMatrix.getElementAt(rowIndexFromLeftMatrix, i) * rightMatrix.getElementAt(i, columnIndexFromRightMatrix);
        }
        resultingMatrix.setElementAt(rowIndexFromLeftMatrix, columnIndexFromRightMatrix, accumulatedResult);
    }

    public void calculateEntireRow(int rowIndexFromLeftMatrix) {
        for (int columnIndexFromRightMatrix = 0; columnIndexFromRightMatrix < rightMatrix.getNumberOfColumns(); columnIndexFromRightMatrix++) {
            calculateScalarMultiplication(rowIndexFromLeftMatrix, columnIndexFromRightMatrix);
        }
    }
}