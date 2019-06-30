import org.junit.Test;
import static org.junit.Assert.*;

public class InputParametersParserTest {

    @Test(expected = AssertionError.class)
    public void noneObligatoryParametersAreProvided() {
        String arguments[] = new String[5];
        arguments[0] = "-t";
        arguments[1] = "-5";
        arguments[2] = "-g";
        arguments[3] = "-3";
        arguments[4] = "-q";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

    @Test(expected = AssertionError.class)
    public void notAllobligatoryParametersAreProvided() {
        String arguments[] = new String[5];
        arguments[0] = "-m";
        arguments[1] = "-512";
        arguments[2] = "-n";
        arguments[3] = "-1024";
        arguments[4] = "-q";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unsupportedOptionIsProvided() {
        String arguments[] = new String[7];
        arguments[0] = "-i";
        arguments[1] = "test-identityMatrix.txt";
        arguments[2] = "-g";
        arguments[3] = "2";
        arguments[4] = "-q";
        arguments[5] = "-p";
        arguments[6] = "3";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

    @Test(expected = AssertionError.class)
    public void emptyInputFileIsProvided() {
        String arguments[] = new String[7];
        arguments[0] = "-i";
        arguments[1] = "test-emptyFile.txt";
        arguments[2] = "-g";
        arguments[3] = "2";
        arguments[4] = "-q";
        arguments[5] = "-t";
        arguments[6] = "3";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

    @Test
    public void allParametersAreProvidedValidlyAndParsedCorrectly() {
        String arguments[] = new String[15];
        arguments[0] = "-m";
        arguments[1] = "512";
        arguments[2] = "-n";
        arguments[3] = "1024";
        arguments[4] = "-k";
        arguments[5] = "2048";
        arguments[6] = "-i";
        arguments[7] = "test-identityMatrix.txt";
        arguments[8] = "-o";
        arguments[9] = "output.txt";
        arguments[10] = "-q";
        arguments[11] = "-g";
        arguments[12] = "3";
        arguments[13] = "-t";
        arguments[14] = "5";

        InputParametersParser parser = new InputParametersParser(arguments);
        assertEquals("The number of rows in the left matrix must be 512", parser.getNumberOfRowsInLeftMatrix(), 512);
        assertEquals("The number of columns in the left matrix must be 1024", parser.getNumberOfColumnsInLeftMatrix(), 1024);
        assertEquals("The number of columns in the right matrix must be 2048", parser.getNumberOfColumnsInRightMatrix(), 2048);
        assertTrue(parser.matrixDimensionsAreProvided());

        assertEquals("The input filename must be: test-identityMatrix.txt", parser.getInputFilename(), "test-identityMatrix.txt");
        assertEquals("The output filename must be: output.txt", parser.getOutputFilename(), "output.txt");
        assertTrue(parser.inputFilenameIsProvided());
        assertTrue(parser.outputFilenameIsProvided());

        assertTrue(parser.isQuietOptionSet());
        assertEquals("Granularity level must be equal to 3", parser.getGranularityLevel(), 3);

        assertEquals("The maximum number of running threads must be equal to 5", parser.getMaximumNumberOfRunningThreads(), 5);
    }

    @Test(expected = AssertionError.class)
    public void negativeDimensionIsProvided() {
        String arguments[] = new String[7];
        arguments[0] = "-m";
        arguments[1] = "1024";
        arguments[2] = "-n";
        arguments[3] = "512";
        arguments[4] = "-k";
        arguments[5] = "-2048";
        arguments[6] = "q";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

    @Test(expected = AssertionError.class)
    public void negativeNumberOfThreadsIsProvided() {
        String arguments[] = new String[8];
        arguments[0] = "-m";
        arguments[1] = "1024";
        arguments[2] = "-n";
        arguments[3] = "512";
        arguments[4] = "-k";
        arguments[5] = "2048";
        arguments[6] = "-t";
        arguments[7] = "-3";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

    @Test(expected = AssertionError.class)
    public void negativeGranularityLevelIsProvided() {
        String arguments[] = new String[8];
        arguments[0] = "-m";
        arguments[1] = "1024";
        arguments[2] = "-n";
        arguments[3] = "512";
        arguments[4] = "-k";
        arguments[5] = "2048";
        arguments[6] = "-g";
        arguments[7] = "-5";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

    @Test(expected = AssertionError.class)
    public void invalidInputMatrixIsProvided() {
        String arguments[] = new String[7];
        arguments[0] = "-i";
        arguments[1] = "test-invalidMatrix.txt";
        arguments[2] = "-q";
        arguments[3] = "-t";
        arguments[4] = "5";
        arguments[5] = "-g";
        arguments[6] = "5";

        InputParametersParser parser = new InputParametersParser(arguments);
    }

}
