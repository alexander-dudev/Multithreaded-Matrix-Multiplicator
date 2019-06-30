    /* Apart from the command-line arguments described in the project requirements, one more can be provided:
    option: "-g" followed by a positive integer indicaing the granularity level
    value of 1 means using the finest granularity
    each next natural number means using coarser granularity
    */

public class Main {
    public static void main(String[] args) {
        InputParametersParser parser = new InputParametersParser(args);
        MultiThreadedMatrixMultiplicator multiplicator;
        InputParametersForMultiplicator parameters = packInputParametersForMultiplicator(parser);

        if (parser.matrixDimensionsAreProvided()) {
            addMatricesDimensionsAsParameters(parser, parameters);
            multiplicator = new MultiThreadedMatrixMultiplicator(parameters);
            multiplicator.writeInfoAboutInitialMatricesIn("randomlyGeneratedMatrices.txt");
        } else {
            multiplicator = new MultiThreadedMatrixMultiplicator(parameters);
            multiplicator.readInputMatricesFrom(parser.getInputFilename());
        }

        multiplicator.multiply();

        if (parser.outputFilenameIsProvided()) {
            multiplicator.writeResultIn(parser.getOutputFilename());
        }
    }

    private static InputParametersForMultiplicator packInputParametersForMultiplicator(InputParametersParser parser) {
        InputParametersForMultiplicator parameters = new InputParametersForMultiplicator();
        parameters.setMaximumNumberOfRunningThreads(parser.getMaximumNumberOfRunningThreads());
        parameters.setGranularityLevel(parser.getGranularityLevel());
        parameters.setQuietOption(parser.isQuietOptionSet());
        return parameters;
    }

    private static void addMatricesDimensionsAsParameters(InputParametersParser parser, InputParametersForMultiplicator parameters) {
        parameters.setNumberOfRowsInLeftMatrix(parser.getNumberOfRowsInLeftMatrix());
        parameters.setNumberOfColumnsInLeftMatrix(parser.getNumberOfColumnsInLeftMatrix());
        parameters.setNumberOfColumnsInRightMatrix(parser.getNumberOfColumnsInRightMatrix());
    }
}
