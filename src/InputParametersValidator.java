import java.io.BufferedReader;
import java.io.IOException;

public class InputParametersValidator {
    private int dimensionsProvidedInInputfile[];

    public void validateNumberOfRowsInMatrix(int numberOfRows) {
        assert numberOfRows > 0 : "The number of rows in a matrix must be a positive integer!";
    }

    public void validateNumberOfColumnsInMatrix(int numberOfColumns) {
        assert numberOfColumns > 0 : "The number of columns in a matrix must be a positive integer!";
    }

    public void validateMaximumNumberOfRunningThreads(int numberOfRunningThreads) {
        assert numberOfRunningThreads > 0 : "The maximum number of threads must be a positive integer!";
    }

    public void validateGranularityLevel(int granularityLevel) {
        assert granularityLevel > 0 : "The granularity level must be a positive integer!";
    }

    public void validateInputMatrices(String inputFilename)  {
        try (BufferedReader reader = ExceptionHandler.createBufferedReaderFor(inputFilename)) {
            String stringWithNumbers;
            // additional validation is performed by the Integer.parseInt(String s) and Double.parseDouble(String s) methods in
            // StringWithNumbersParser class - they throw a NumberFormatException if the string does not contain a parsable number

            stringWithNumbers = ExceptionHandler.readOneLineFromFile(reader);
            assert stringWithNumbers != null : "No information about the matrices is provided in the input file!";
            validateMatricesDimensions(stringWithNumbers);

            boolean leftMatrixIsToBeValidated = true;
            validateMatrix(leftMatrixIsToBeValidated, reader);
            validateMatrix(!leftMatrixIsToBeValidated, reader);

            stringWithNumbers = ExceptionHandler.readOneLineFromFile(reader);
            assert stringWithNumbers == null : "Additional information which is not neccesary is provided in the input file!";
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("An error occured while trying to close the stream that was reading from the input file!");
            System.exit(0);
        }
    }

    private void validateMatricesDimensions(String stringWithMatricesDimensions) {
        StringWithNumbersParser parser = new StringWithNumbersParser(stringWithMatricesDimensions);
        int dimension;
        dimensionsProvidedInInputfile = new int[3];

        for (int i = 0; i < 3; i++) {
            assert (!parser.isStringWithNumbersEmpty()) : "There are one or more missing dimensions in the file with input matrices!";
            dimension = parser.extractFirstInteger();
            assert dimension > 0 : "Matrices dimensions must be positive integers!";
            dimensionsProvidedInInputfile[i] = dimension;
        }

        assert parser.isStringWithNumbersEmpty() : "More than the required amount of dimensions is provided in the input file!";
    }

    private void validateMatrix(boolean validatingLeftMatrix, BufferedReader reader) {
        int numberOfRows;
        int numberOfColumns;
        if (validatingLeftMatrix) {
            numberOfRows = dimensionsProvidedInInputfile[0];
            numberOfColumns = dimensionsProvidedInInputfile[1];
        }
        else {
            numberOfRows = dimensionsProvidedInInputfile[1];
            numberOfColumns = dimensionsProvidedInInputfile[2];
        }

        String stringWithDoubles;
        for (int i = 0; i < numberOfRows; i++) {
            stringWithDoubles = ExceptionHandler.readOneLineFromFile(reader);
            assert stringWithDoubles != null : "There are one or more missing rows from a matrix in the input file!";
            validateRow(stringWithDoubles, numberOfColumns);
        }
    }

    private void validateRow(String stringWithDoubles, int numberOfColumns) {
        StringWithNumbersParser parser = new StringWithNumbersParser(stringWithDoubles);

        for (int i = 0; i < numberOfColumns; i++) {
            assert !parser.isStringWithNumbersEmpty() : "Not enough numbers are provided in a row from the input file!";
            parser.extractFirstDouble();
        }

        assert parser.isStringWithNumbersEmpty() : "More than the required amount of numbers are provided in a row from the input file!";
    }
}