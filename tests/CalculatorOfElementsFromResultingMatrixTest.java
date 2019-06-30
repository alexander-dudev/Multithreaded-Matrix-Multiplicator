import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorOfElementsFromResultingMatrixTest {

    @Test
    public void scalarMultiplicationOfVectorsFromRandomMatrices() {
        MatrixOfDoubles initialMatrices[] = readMatricesFromFile("test-randomMatrices.txt");
        MatrixOfDoubles leftMatrix = initialMatrices[0];
        MatrixOfDoubles rightMatrix = initialMatrices[1];
        MatrixOfDoubles resultingMatrix = new MatrixOfDoubles(2, 3);
        CalculatorOfElementsFromResultingMatrix calculator = new CalculatorOfElementsFromResultingMatrix(leftMatrix, rightMatrix, resultingMatrix);

        for (int i = 0; i < leftMatrix.getNumberOfRows(); i++) {
            for (int j = 0; j < rightMatrix.getNumberOfColumns(); j++) {
                calculator.calculateScalarMultiplication(i, j);
            }
        }
        assertAnswerIsCorrectAfterMultiplyingRandomMatrices(resultingMatrix);
    }

    @Test
    public void scalarMultiplicationOfVectorsFromIdentityMatrixAndRandomMatrix() {
        MatrixOfDoubles initialMatrices[] = readMatricesFromFile("test-identityMatrix.txt");
        MatrixOfDoubles leftMatrix = initialMatrices[0];
        MatrixOfDoubles rightMatrix = initialMatrices[1];
        MatrixOfDoubles resultingMatrix = new MatrixOfDoubles(3, 3);
        CalculatorOfElementsFromResultingMatrix calculator = new CalculatorOfElementsFromResultingMatrix(leftMatrix, rightMatrix, resultingMatrix);

        for (int i = 0; i < leftMatrix.getNumberOfRows(); i++) {
            for (int j = 0; j < rightMatrix.getNumberOfColumns(); j++) {
                calculator.calculateScalarMultiplication(i, j);
            }
        }
        asserAnswerIsCorrectAfterMultiplyingIdentityMatrixWithRandom(resultingMatrix);
    }

    @Test
    public void calculateResultingRowsFromRandomMatrices() {
        MatrixOfDoubles initialMatrices[] = readMatricesFromFile("test-randomMatrices.txt");
        MatrixOfDoubles leftMatrix = initialMatrices[0];
        MatrixOfDoubles rightMatrix = initialMatrices[1];
        MatrixOfDoubles resultingMatrix = new MatrixOfDoubles(2, 3);
        CalculatorOfElementsFromResultingMatrix calculator = new CalculatorOfElementsFromResultingMatrix(leftMatrix, rightMatrix, resultingMatrix);

        for (int i = 0; i < leftMatrix.getNumberOfRows(); i++) {
            calculator.calculateEntireRow(i);
        }

        assertAnswerIsCorrectAfterMultiplyingRandomMatrices(resultingMatrix);
    }

    @Test
    public void calculateResultingRowsFromIdentityMatrixAndRandomMatrix() {
        MatrixOfDoubles initialMatrices[] = readMatricesFromFile("test-identityMatrix.txt");
        MatrixOfDoubles leftMatrix = initialMatrices[0];
        MatrixOfDoubles rightMatrix = initialMatrices[1];
        MatrixOfDoubles resultingMatrix = new MatrixOfDoubles(3, 3);
        CalculatorOfElementsFromResultingMatrix calculator = new CalculatorOfElementsFromResultingMatrix(leftMatrix, rightMatrix, resultingMatrix);

        for (int i = 0; i < leftMatrix.getNumberOfRows(); i++) {
            calculator.calculateEntireRow(i);
        }

        asserAnswerIsCorrectAfterMultiplyingIdentityMatrixWithRandom(resultingMatrix);
    }

    private MatrixOfDoubles[] readMatricesFromFile(String inputFilename) {
        MatrixReader reader = new MatrixReader();
        MatrixOfDoubles initialMatrices[] = reader.readTwoMatricesFrom(inputFilename);
        return initialMatrices;
    }

    private void assertAnswerIsCorrectAfterMultiplyingRandomMatrices(MatrixOfDoubles resultingMatrix) {
        assertEquals("Element on position (0,0) must be equal to 3", resultingMatrix.getElementAt(0,0), 3, 0.01);
        assertEquals("Element on position (0,1) must be equal to 1", resultingMatrix.getElementAt(0,1), 1, 0.01);
        assertEquals("Element on position (0,2) must be equal to 2", resultingMatrix.getElementAt(0,2), 2, 0.01);
        assertEquals("Element on position (1,0) must be equal to 6", resultingMatrix.getElementAt(1,0), 6, 0.01);
        assertEquals("Element on position (1,1) must be equal to 2", resultingMatrix.getElementAt(1,1), 2, 0.01);
        assertEquals("Element on position (1,2) must be equal to 4", resultingMatrix.getElementAt(1,2), 4, 0.01);
    }

    private void asserAnswerIsCorrectAfterMultiplyingIdentityMatrixWithRandom(MatrixOfDoubles resultingMatrix) {
        assertEquals("Element on position (0,0) must be equal to 1", resultingMatrix.getElementAt(0,0), 1, 0.01);
        assertEquals("Element on position (0,1) must be equal to 1", resultingMatrix.getElementAt(0,1), 1, 0.01);
        assertEquals("Element on position (0,2) must be equal to 4", resultingMatrix.getElementAt(0,2), 4, 0.01);
        assertEquals("Element on position (1,0) must be equal to 1", resultingMatrix.getElementAt(1,0), 1, 0.01);
        assertEquals("Element on position (1,1) must be equal to 1", resultingMatrix.getElementAt(1,1), 1, 0.01);
        assertEquals("Element on position (1,2) must be equal to 7", resultingMatrix.getElementAt(1,2), 7, 0.01);
        assertEquals("Element on position (2,0) must be equal to 1", resultingMatrix.getElementAt(2,0), 1, 0.01);
        assertEquals("Element on position (2,1) must be equal to 1", resultingMatrix.getElementAt(2,1), 1, 0.01);
        assertEquals("Element on position (2,2) must be equal to 1", resultingMatrix.getElementAt(2,2), 1, 0.01);
    }
}
