public class InputParametersForMultiplicator {
    private int numberOfRowsInLeftMatrix;
    private int numberOfColumnsInLeftMatrix;
    private int numberOfColumnsInRightMatrix;
    private int maximumNumberOfRunningThreads;
    private int granularityLevel;
    private boolean quietOptionIsSet;

    public InputParametersForMultiplicator() {
        numberOfRowsInLeftMatrix = 0;
        numberOfColumnsInLeftMatrix = 0;
        numberOfColumnsInRightMatrix = 0;
    }

    public void setNumberOfRowsInLeftMatrix(int numberOfRowsInLeftMatrix) {
        this.numberOfRowsInLeftMatrix = numberOfRowsInLeftMatrix;
    }

    public void setNumberOfColumnsInLeftMatrix(int numberOfColumnsInLeftMatrix) {
        this.numberOfColumnsInLeftMatrix = numberOfColumnsInLeftMatrix;
    }

    public void setNumberOfColumnsInRightMatrix(int numberOfColumnsInRightMatrix) {
        this.numberOfColumnsInRightMatrix = numberOfColumnsInRightMatrix;
    }

    public void setMaximumNumberOfRunningThreads(int maximumNumberOfRunningThreads) {
        this.maximumNumberOfRunningThreads = maximumNumberOfRunningThreads;
    }

    public void setGranularityLevel(int granularityLevel) {
        this.granularityLevel = granularityLevel;
    }

    public void setQuietOption(boolean quietOptionIsSet) {
        this.quietOptionIsSet = quietOptionIsSet;
    }

    public int getNumberOfRowsInLeftMatrix() {
        return numberOfRowsInLeftMatrix;
    }

    public int getNumberOfColumnsInLeftMatrix() {
        return numberOfColumnsInLeftMatrix;
    }

    public int getNumberOfColumnsInRightMatrix() {
        return numberOfColumnsInRightMatrix;
    }

    public int getMaximumNumberOfRunningThreads() {
        return maximumNumberOfRunningThreads;
    }

    public int getGranularityLevel() {
        return granularityLevel;
    }

    public boolean isQuietOptionSet() {
        return quietOptionIsSet;
    }
}