import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void multiplyMatricesFromFileUsingOneThreadAndGranularityOne() {
        String arguments[] = new String[8];
        arguments[0] = "-i";
        arguments[1] = "test-randomMatrices.txt";
        arguments[2] = "-t";
        arguments[3] = "1";
        arguments[4] = "-g";
        arguments[5] = "1";
        arguments[6] = "-o";
        arguments[7] = "output.txt";
        Main.main(arguments);

        testIfResultInFileIsCorrect("output.txt", getExpectedMatrixAccordingToFileWithRandomMatrices());
    }

    @Test
    public void multiplyMatricesFromFileUsingThreeThreadsAndGranularityOne() {
        String arguments[] = new String[8];
        arguments[0] = "-i";
        arguments[1] = "test-randomMatrices.txt";
        arguments[2] = "-t";
        arguments[3] = "3";
        arguments[4] = "-g";
        arguments[5] = "1";
        arguments[6] = "-o";
        arguments[7] = "output.txt";
        Main.main(arguments);

        testIfResultInFileIsCorrect("output.txt", getExpectedMatrixAccordingToFileWithRandomMatrices());
    }

    @Test
    public void multiplyMatricesFromFileUsingOneThreadAndGranularityThree() {
        String arguments[] = new String[8];
        arguments[0] = "-i";
        arguments[1] = "test-randomMatrices.txt";
        arguments[2] = "-t";
        arguments[3] = "1";
        arguments[4] = "-g";
        arguments[5] = "3";
        arguments[6] = "-o";
        arguments[7] = "output.txt";
        Main.main(arguments);

        testIfResultInFileIsCorrect("output.txt", getExpectedMatrixAccordingToFileWithRandomMatrices());
    }

    @Test
    public void multiplyMatricesFromFileUsingThreeThreadsAndGranularityThree() {
        String arguments[] = new String[8];
        arguments[0] = "-i";
        arguments[1] = "test-randomMatrices.txt";
        arguments[2] = "-t";
        arguments[3] = "3";
        arguments[4] = "-g";
        arguments[5] = "3";
        arguments[6] = "-o";
        arguments[7] = "output.txt";
        Main.main(arguments);

        testIfResultInFileIsCorrect("output.txt", getExpectedMatrixAccordingToFileWithRandomMatrices());
    }

    @Test
    public void multiplyIdentityMatrixWithRandomFromFile() {
        String arguments[] = new String[8];
        arguments[0] = "-i";
        arguments[1] = "test-identityMatrix.txt";
        arguments[2] = "-t";
        arguments[3] = "3";
        arguments[4] = "-g";
        arguments[5] = "2";
        arguments[6] = "-o";
        arguments[7] = "output.txt";
        Main.main(arguments);

        testIfResultInFileIsCorrect("output.txt", getExpectedMatrixAccordingToFileWithIdentityMatrix());
    }

    // it is tested and confirmed that the program correctly multiplies matices that are given in a file;
    // using that, the multiplication of randomly generated matrices can be tested
    @Test
    public void multiplyRandomlyGeneratedMatrices() {
        String arguments[] = new String[8];
        arguments[0] = "-m";
        arguments[1] = "3";
        arguments[2] = "-n";
        arguments[3] = "3";
        arguments[4] = "-k";
        arguments[5] = "3";
        arguments[6] = "-o";
        arguments[7] = "outputFromRandomlyGeneratedMatrices.txt";
        Main.main(arguments);

        String otherArguments[] = new String[4];
        otherArguments[0] = "-i";
        otherArguments[1] = "randomlyGeneratedMatrices.txt";
        otherArguments[2] = "-o";
        otherArguments[3] = "correctOutput.txt";
        Main.main(otherArguments);

        MatrixReader reader = new MatrixReader();
        MatrixOfDoubles expectedMatrix = reader.readSingleMatrixFrom("correctOutput.txt");
        testIfResultInFileIsCorrect("outputFromRandomlyGeneratedMatrices.txt", expectedMatrix);
    }

    private void testIfResultInFileIsCorrect(String outputFilename, MatrixOfDoubles expectedMatrix) {
        MatrixReader reader = new MatrixReader();
        MatrixOfDoubles resultingMatrix = reader.readSingleMatrixFrom(outputFilename);
        testWhetherMatricesAreEqual(resultingMatrix, expectedMatrix);
    }

    private MatrixOfDoubles getExpectedMatrixAccordingToFileWithRandomMatrices() {
        MatrixOfDoubles expectedMatrix = new MatrixOfDoubles(2, 3);
        expectedMatrix.setElementAt(0,0 , 3);
        expectedMatrix.setElementAt(0,1, 1);
        expectedMatrix.setElementAt(0,2,2);
        expectedMatrix.setElementAt(1,0, 6);
        expectedMatrix.setElementAt(1,1, 2);
        expectedMatrix.setElementAt(1,2, 4);
        return expectedMatrix;
    }

    private MatrixOfDoubles getExpectedMatrixAccordingToFileWithIdentityMatrix() {
        MatrixOfDoubles expectedMatrix = new MatrixOfDoubles(3, 3);
        expectedMatrix.setElementAt(0,0 , 1);
        expectedMatrix.setElementAt(0,1, 1);
        expectedMatrix.setElementAt(0,2,4);
        expectedMatrix.setElementAt(1,0, 1);
        expectedMatrix.setElementAt(1,1, 1);
        expectedMatrix.setElementAt(1,2, 7);
        expectedMatrix.setElementAt(2,0, 1);
        expectedMatrix.setElementAt(2,1, 1);
        expectedMatrix.setElementAt(2,2, 1);
        return expectedMatrix;
    }

    private void testWhetherMatricesAreEqual(MatrixOfDoubles resultingMatrix, MatrixOfDoubles expectedMatrix) {
        assertEquals("The number of rows in both matrices must be the same!",
                resultingMatrix.getNumberOfRows(), expectedMatrix.getNumberOfRows());
        assertEquals("The number of columns in both matrices must be the same!",
                resultingMatrix.getNumberOfColumns(), expectedMatrix.getNumberOfColumns());

        for (int i = 0; i < resultingMatrix.getNumberOfRows(); i++) {
            for (int j = 0; j < resultingMatrix.getNumberOfColumns(); j++) {
                assertEquals("The respective elements on position [" + i + ", " + j + "] must be the same!",
                        resultingMatrix.getElementAt(i,j), expectedMatrix.getElementAt(i,j), 0.01);
            }
        }
    }
}
