import java.io.BufferedReader;
import java.io.IOException;

public class MatrixReader {
    private MatrixOfDoubles leftMatrix;
    private MatrixOfDoubles rightMatrix;
    private MatrixOfDoubles singleMatrix;

    public MatrixOfDoubles[] readTwoMatricesFrom(String inputFilename) {
        MatrixOfDoubles matrices[] = null;
        try (BufferedReader reader = ExceptionHandlerForStreamMethods.createBufferedReaderFor(inputFilename)) {
            String stringWithIntegers = ExceptionHandlerForStreamMethods.readOneLineFromFile(reader);
            processInfoAboutDimensionsOfTwoMatrices(stringWithIntegers);

            processAllLinesRegarding(leftMatrix, reader);
            processAllLinesRegarding(rightMatrix, reader);

            matrices = new MatrixOfDoubles[2];
            matrices[0] = leftMatrix;
            matrices[1] = rightMatrix;
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("An error occured while trying to close the stream that was reading from the input file!");
            System.exit(0);
        }
        return matrices;
    }

    public MatrixOfDoubles readSingleMatrixFrom(String inputFilename) {
        try (BufferedReader reader = ExceptionHandlerForStreamMethods.createBufferedReaderFor(inputFilename)) {
            String stringWithIntegers = ExceptionHandlerForStreamMethods.readOneLineFromFile(reader);
            processInfoAboutDimensionsOfSingleMatrix(stringWithIntegers);
            processAllLinesRegarding(singleMatrix, reader);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("An error occured while trying to close the stream that was reading from the input file!");
            System.exit(0);
        }
        return singleMatrix;
    }

    private void processInfoAboutDimensionsOfTwoMatrices(String stringWithIntegers) {
        StringWithNumbersParser parser = new StringWithNumbersParser(stringWithIntegers);

        int numberOfRowsInLeftMatrix = parser.extractFirstInteger();
        int numberOfColumnsInLeftMatrix = parser.extractFirstInteger();
        int numberOfColumnsInRightMatrix = parser.extractFirstInteger();

        leftMatrix = new MatrixOfDoubles(numberOfRowsInLeftMatrix, numberOfColumnsInLeftMatrix);
        rightMatrix = new MatrixOfDoubles(numberOfColumnsInLeftMatrix, numberOfColumnsInRightMatrix);
    }

    private void processInfoAboutDimensionsOfSingleMatrix(String stringWithIntegers) {
        StringWithNumbersParser parser = new StringWithNumbersParser(stringWithIntegers);
        int numberOfRowsInSingleMatrix = parser.extractFirstInteger();
        int numberOfColumnsInSingleMatrix = parser.extractFirstInteger();
        singleMatrix = new MatrixOfDoubles(numberOfRowsInSingleMatrix, numberOfColumnsInSingleMatrix);
    }

    private void processAllLinesRegarding(MatrixOfDoubles matrix, BufferedReader reader) {
        String stringWithDoubles;
        for (int rowIndex = 0; rowIndex < matrix.getNumberOfRows(); rowIndex++) {
            stringWithDoubles = ExceptionHandlerForStreamMethods.readOneLineFromFile(reader);
            processLineRegarding(matrix, stringWithDoubles, rowIndex);
        }
    }

    private void processLineRegarding(MatrixOfDoubles matrix, String stringWithDoubles, int rowIndex) {
        StringWithNumbersParser parser = new StringWithNumbersParser(stringWithDoubles);
        double number;

        for (int columnIndex = 0; columnIndex < matrix.getNumberOfColumns(); columnIndex++) {
            number = parser.extractFirstDouble();
            matrix.setElementAt(rowIndex, columnIndex, number);
        }
    }
}
