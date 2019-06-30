public class CalculatingSequentialRowsRunnable extends TaskRunnable {
    private int indexOfFirstRowFromLeftMatrix;
    private int numberOfRowsToCalculate;

    public CalculatingSequentialRowsRunnable(InputParametersForTask parameters, int indexOfFirstRowFromLeftMatrix, int numberOfRowsToCalculate) {
        super(parameters);
        this.indexOfFirstRowFromLeftMatrix = indexOfFirstRowFromLeftMatrix;
        this.numberOfRowsToCalculate = numberOfRowsToCalculate;
    }

    @Override
    public void run() {
        announceBeginningOfTaskExecution();

        for (int i = indexOfFirstRowFromLeftMatrix;
             i < leftMatrix.getNumberOfRows() && i < indexOfFirstRowFromLeftMatrix + numberOfRowsToCalculate; i++)
        {
            calculator.calculateEntireRow(i);
        }

        announceEndOfTaskExecution();
    }
}
