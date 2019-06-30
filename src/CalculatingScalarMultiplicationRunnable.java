public class CalculatingScalarMultiplicationRunnable extends TaskRunnable {
    private int rowIndexFromLeftMatrix;
    private int columnIndexFromRightMatrix;

    public CalculatingScalarMultiplicationRunnable(InputParametersForTask parameters, int rowIndexFromLeftMatrix, int columnIndexFromRightMatrix) {
        super(parameters);
        this.rowIndexFromLeftMatrix = rowIndexFromLeftMatrix;
        this.columnIndexFromRightMatrix = columnIndexFromRightMatrix;
    }

    @Override
    public void run() {
        announceBeginningOfTaskExecution();
        calculator.calculateScalarMultiplication(rowIndexFromLeftMatrix, columnIndexFromRightMatrix);
        announceEndOfTaskExecution();
    }
}
