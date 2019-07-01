import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;

public class MultiThreadedMatrixMultiplicator {
    private MatrixOfDoubles leftMatrix;
    private MatrixOfDoubles rightMatrix;
    private MatrixOfDoubles resultingMatrix;

    private ExecutorService threadExecutor;
    /*
    regarding the instance variable granularityLevel:
    value of 1 means using the finest granularity - each thread calculates a scalar multiplication;
    each next natural number means using coarser granularity - each thread calculates (granularityLevel - 1)
    sequential rows from the resulting matrix;
    */
    private int granularityLevel;
    private boolean quietOptionIsSet;
    private int numberOfDelegatedTasks;

    private final int minutesToWaitForTasksToComplete = 5;
    private final double minimumDoubleValue = -1_000;
    private final double maximumDoubleValue = 1_000;
    private final int nanosecondsInOneSecond = 1_000_000_000;
    private long timeMarkerRightBeforeMultiplication;
    private long timeForMultiplicationInNanoseconds;

    public MultiThreadedMatrixMultiplicator(InputParametersForMultiplicator parameters) {
        threadExecutor = Executors.newFixedThreadPool(parameters.getMaximumNumberOfRunningThreads());
        this.granularityLevel = parameters.getGranularityLevel();
        this.quietOptionIsSet = parameters.isQuietOptionSet();
        this.numberOfDelegatedTasks = 0;

        if (matricesDimensionsAreProvided(parameters)) {
            leftMatrix = new MatrixOfDoubles(parameters.getNumberOfRowsInLeftMatrix(), parameters.getNumberOfColumnsInLeftMatrix());
            rightMatrix = new MatrixOfDoubles(parameters.getNumberOfColumnsInLeftMatrix(), parameters.getNumberOfColumnsInRightMatrix());
            resultingMatrix = new MatrixOfDoubles(parameters.getNumberOfRowsInLeftMatrix(), parameters.getNumberOfColumnsInRightMatrix());
            generateRandomDoublesInMatrices();
        }
    }

    private boolean matricesDimensionsAreProvided(InputParametersForMultiplicator parameters) {
        return parameters.getNumberOfRowsInLeftMatrix() > 0 && parameters.getNumberOfColumnsInLeftMatrix() > 0
                && parameters.getNumberOfColumnsInRightMatrix() > 0;
    }

    private void generateRandomDoublesInMatrices() {
        generateRandomDoublesIn(leftMatrix);
        generateRandomDoublesIn(rightMatrix);
    }

    private void generateRandomDoublesIn(MatrixOfDoubles matrix) {
        ThreadLocalRandom randomDoublesGenerator = ThreadLocalRandom.current();
        double randomValue;

        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                randomValue = randomDoublesGenerator.nextDouble(minimumDoubleValue, maximumDoubleValue);
                matrix.setElementAt(i, j, randomValue);
            }
        }
    }

    public void readInputMatricesFrom(String inputFilename) {
        MatrixReader reader = new MatrixReader();
        MatrixOfDoubles inputMatrices[] = reader.readTwoMatricesFrom(inputFilename);
        leftMatrix = inputMatrices[0];
        rightMatrix = inputMatrices[1];
        resultingMatrix = new MatrixOfDoubles(leftMatrix.getNumberOfRows(), rightMatrix.getNumberOfColumns());
    }

    public void multiply() {
        setTimeMarkerBeforeMultiplicationBegins();

        if (granularityLevel == 1) {
            submitScalarMultiplicationTasks();
        }
        else {
            submitRowsCalculatingTasks();
        }

        shutDownThreadExecutor();
        waitUntilAllTasksAreCompleted();
        calculateTimeNeededForMultiplication();
        printTimeNeededForMultiplication();
    }

    private void setTimeMarkerBeforeMultiplicationBegins() {
        timeMarkerRightBeforeMultiplication = System.nanoTime();
    }

    private void calculateTimeNeededForMultiplication() {
        timeForMultiplicationInNanoseconds = System.nanoTime() - timeMarkerRightBeforeMultiplication;
    }

    private void printTimeNeededForMultiplication() {
        System.out.print("The time in seconds required to multiply the matrices was: ");
        System.out.println((double) timeForMultiplicationInNanoseconds / nanosecondsInOneSecond);
    }

    private void submitScalarMultiplicationTasks() {
        InputParametersForTask parameters;

        for (int i = 0; i < leftMatrix.getNumberOfRows(); i++) {
            for (int j = 0; j < rightMatrix.getNumberOfColumns(); j++) {
                parameters = packInputParemetersForTask();
                CalculatingScalarMultiplicationRunnable task = new CalculatingScalarMultiplicationRunnable(parameters, i, j);
                threadExecutor.execute(task);
            }
        }
    }

    private void submitRowsCalculatingTasks() {
        InputParametersForTask parameters;

        for (int i = 0; i < leftMatrix.getNumberOfRows(); i+= granularityLevel - 1) {
            parameters = packInputParemetersForTask();
            CalculatingSequentialRowsRunnable task = new CalculatingSequentialRowsRunnable(parameters, i, granularityLevel - 1);
            threadExecutor.execute(task);
        }
    }

    private void shutDownThreadExecutor() {
        if (threadExecutor != null) {
            threadExecutor.shutdown();
        }
    }

    private void waitUntilAllTasksAreCompleted() {
        try {
            threadExecutor.awaitTermination(minutesToWaitForTasksToComplete, TimeUnit.MINUTES);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            System.out.println("The program was interrupted because it couldn't finish execution in "
                    + minutesToWaitForTasksToComplete + " minutes");
            System.exit(0);
        }
    }

    private InputParametersForTask packInputParemetersForTask() {
        numberOfDelegatedTasks++;

        InputParametersForTask parameters = new InputParametersForTask();
        parameters.setLeftMatrix(leftMatrix);
        parameters.setRightMatrix(rightMatrix);
        parameters.setResultingMatrix(resultingMatrix);
        parameters.setQuietOption(quietOptionIsSet);
        parameters.setTaskID(numberOfDelegatedTasks);
        return parameters;
    }

    public void writeResultIn(String outputFilename) {
        writeMatrixInfoIn(outputFilename, resultingMatrix);
    }

    private void writeMatrixInfoIn(String filename, MatrixOfDoubles matrix) {
        try (PrintWriter writer = ExceptionHandlerForStreamMethods.createPrintWriterFor(filename)) {
            printMatrixDimensions(matrix, writer);
            writer.println();
            printMatrixElements(matrix, writer);
        }
    }

    private void printMatrixDimensions(MatrixOfDoubles matrix, PrintWriter writer) {
        writer.print(matrix.getNumberOfRows());
        writer.print(" ");
        writer.print(matrix.getNumberOfColumns());
    }

    private void printMatrixElements(MatrixOfDoubles matrix, PrintWriter writer) {
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                writer.print(matrix.getElementAt(i, j));
                if (j < matrix.getNumberOfColumns() - 1) {
                    writer.print(" ");
                }
            }
            writer.println();
        }
    }

    // the next method is included for testing purposes
    // using it is the only way to check that the program correctly calculates
    // the multiplication of randomly generated matrices

    public void writeInfoAboutInitialMatricesIn(String filename) {
        try (PrintWriter writer = ExceptionHandlerForStreamMethods.createPrintWriterFor(filename)) {
            printMatrixDimensions(leftMatrix, writer);
            writer.println(" " + rightMatrix.getNumberOfColumns());

            printMatrixElements(leftMatrix, writer);
            printMatrixElements(rightMatrix, writer);

        }
    }
}