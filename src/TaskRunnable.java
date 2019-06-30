public abstract class TaskRunnable implements Runnable {
    protected MatrixOfDoubles leftMatrix;
    private MatrixOfDoubles rightMatrix;
    private MatrixOfDoubles resultingMatrix;
    private boolean quietOptionIsSet;
    private int taskID;
    protected CalculatorOfElementsFromResultingMatrix calculator;

    protected TaskRunnable(InputParametersForTask parameters) {
        this.leftMatrix = parameters.getLeftMatrix();
        this.rightMatrix = parameters.getRightMatrix();
        this.resultingMatrix = parameters.getResultingMatrix();
        this.quietOptionIsSet = parameters.isQuietOptionSet();
        this.taskID = parameters.getTaskID();
        calculator = new CalculatorOfElementsFromResultingMatrix(leftMatrix, rightMatrix, resultingMatrix);
    }

    public void announceBeginningOfTaskExecution() {
        if (!quietOptionIsSet) {
            System.out.println("Task " + taskID + " has just been started!");
        }
    }

    public void announceEndOfTaskExecution() {
        if (!quietOptionIsSet) {
            System.out.println("Task " + taskID + " has just been finished!");
        }
    }
}
