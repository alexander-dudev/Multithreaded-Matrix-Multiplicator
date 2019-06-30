public class InputParametersForTask {
    private MatrixOfDoubles leftMatrix;
    private MatrixOfDoubles rightMatrix;
    private MatrixOfDoubles resultingMatrix;
    private boolean quietOptionIsSet;
    private int taskID;

    public MatrixOfDoubles getLeftMatrix() {
        return leftMatrix;
    }

    public MatrixOfDoubles getRightMatrix() {
        return rightMatrix;
    }

    public MatrixOfDoubles getResultingMatrix() {
        return resultingMatrix;
    }

    public boolean isQuietOptionSet() {
        return quietOptionIsSet;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setLeftMatrix(MatrixOfDoubles leftMatrix) {
        this.leftMatrix = leftMatrix;
    }

    public void setRightMatrix(MatrixOfDoubles rightMatrix) {
        this.rightMatrix = rightMatrix;
    }

    public void setResultingMatrix(MatrixOfDoubles resultingMatrix) {
        this.resultingMatrix = resultingMatrix;
    }

    public void setQuietOption(boolean quietOptionIsSet) {
        this.quietOptionIsSet = quietOptionIsSet;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
