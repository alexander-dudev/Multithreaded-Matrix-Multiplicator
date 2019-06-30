import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionHandler {

    public static BufferedReader createBufferedReaderFor(String inputFilename) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFilename));
        } catch (FileNotFoundException fileException) {
            fileException.printStackTrace();
            System.out.println("The provided input file \"" + inputFilename + "\" doesn't exist!");
            System.exit(0);
        }

        return reader;
    }

    public static String readOneLineFromFile(BufferedReader reader) {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("An error occured while trying to read a line from the provided input file!");
            System.exit(0);
        }
        return line;
    }

    public static PrintWriter createPrintWriterFor(String outputFilename) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outputFilename);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("An error occured while trying to open the file \"" + outputFilename + "\" for writing!");
            System.exit(0);
        }

        return writer;
    }
}
