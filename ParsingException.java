/**
 * takes care of printing an informative announcement when the program discovers an I/O exception
 */
public class ParsingException extends java.io.IOException {
    public ParsingException(String message) {
        super(message);
    }
}
