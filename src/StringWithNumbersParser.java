public class StringWithNumbersParser {
    private String lineWithNumbers;

    public StringWithNumbersParser(String lineWithNumbers) {
        this.lineWithNumbers = lineWithNumbers;
    }

    public boolean isStringWithNumbersEmpty() {
        return lineWithNumbers.isEmpty();
    }

    public int extractFirstInteger() {
        int indexAfterFirstInteger = getIndexAfterFirstNumber();
        int firstInteger = Integer.parseInt(lineWithNumbers.substring(0, indexAfterFirstInteger));
        removeFirstNumber();
        return firstInteger;
    }

    public double extractFirstDouble() {
        int indexAfterFirstDouble = getIndexAfterFirstNumber();
        double firstDouble = Double.parseDouble(lineWithNumbers.substring(0, indexAfterFirstDouble));
        removeFirstNumber();
        return firstDouble;
    }

    private void removeFirstNumber() {
        int indexAfterFirstNumber = getIndexAfterFirstNumber();
        lineWithNumbers = getSubstringStartingAt(indexAfterFirstNumber);
        lineWithNumbers = lineWithNumbers.trim();
    }

    private int getIndexAfterFirstNumber() {
        int index = 0;
        while (index < lineWithNumbers.length() && lineWithNumbers.charAt(index) != ' ') {
            index++;
        }
        return index;
    }

    private String getSubstringStartingAt(int index) {
        return lineWithNumbers.substring(index);
    }
}