public class InputParametersParser {
    private int numberOfRowsInLeftMatrix;
    private int numberOfColumnsInLeftMatrix;
    private int numberOfColumnsInRightMatrix;
    private String inputFilename;
    private String outputFilename;
    private int maximumNumberOfRunningThreads;
    private boolean quietOptionIsSet;
    private int granularityLevel;
    private InputParametersValidator validator;

    private InputParametersParser() {
        numberOfRowsInLeftMatrix = 0;
        numberOfColumnsInLeftMatrix = 0;
        numberOfColumnsInRightMatrix = 0;
        inputFilename = "";
        outputFilename = "";
        maximumNumberOfRunningThreads = 1;
        quietOptionIsSet = false;
        granularityLevel = 2;
        validator = new InputParametersValidator();
    }

    public InputParametersParser(String[] arguments) {
        this();

        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].equals("-q")) {
                quietOptionIsSet = true;
            }
            else {
                parseOptionAndValue(arguments[i], arguments[i+1]);
                i++;
            }
        }

        assertThatDimentionsOrInputFilenameIsProvided();
        if (inputFilenameIsProvided() && !matrixDimensionsAreProvided()) {
            validator.validateInputMatrices(inputFilename);
        }
    }

    private void parseOptionAndValue(String option, String value) {
        // the Integer.parseInt(String s) method validates the values and throws a
        // NumberFormatException if the string does not contain a parsable integer

        if (option.equals("-m")) {
            numberOfRowsInLeftMatrix = Integer.parseInt(value);
            validator.validateNumberOfRowsInMatrix(numberOfRowsInLeftMatrix);
            return;
        }

        if (option.equals("-n")) {
            numberOfColumnsInLeftMatrix = Integer.parseInt(value);
            validator.validateNumberOfColumnsInMatrix(numberOfColumnsInLeftMatrix);
            return;
        }

        if (option.equals("-k")) {
            numberOfColumnsInRightMatrix = Integer.parseInt(value);
            validator.validateNumberOfColumnsInMatrix(numberOfColumnsInRightMatrix);
            return;
        }

        if (option.equals("-i")) {
            inputFilename = value;
            return;
        }

        if (option.equals("-o")) {
            outputFilename = value;
            return;
        }

        if (option.equals("-t")) {
            maximumNumberOfRunningThreads = Integer.parseInt(value);
            validator.validateMaximumNumberOfRunningThreads(maximumNumberOfRunningThreads);
            return;
        }

        if (option.equals("-g")) {
            granularityLevel = Integer.parseInt(value);
            validator.validateGranularityLevel(granularityLevel);
            return;
        }

        throw new IllegalArgumentException("An unsupported option has been provided as a command-line argument!");
    }

    private void assertThatDimentionsOrInputFilenameIsProvided() {
        assert matrixDimensionsAreProvided() || inputFilenameIsProvided()
                : "Neither matrix dimensions, nor an input filename is provided as a command-line argument!";
    }

    public boolean matrixDimensionsAreProvided() {
        return numberOfRowsInLeftMatrix > 0 && numberOfColumnsInLeftMatrix > 0 && numberOfColumnsInRightMatrix > 0;
    }

    public boolean inputFilenameIsProvided() {
        return !inputFilename.equals("");
    }

    public boolean outputFilenameIsProvided() {
        return !outputFilename.equals("");
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

    public String getInputFilename() {
        return inputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public int getMaximumNumberOfRunningThreads() {
        return maximumNumberOfRunningThreads;
    }

    public boolean isQuietOptionSet() {
        return quietOptionIsSet;
    }

    public int getGranularityLevel() {
        return granularityLevel;
    }
}