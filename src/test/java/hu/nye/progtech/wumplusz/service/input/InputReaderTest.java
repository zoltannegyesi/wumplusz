package hu.nye.progtech.wumplusz.service.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class InputReaderTest {

    private InputReader underTest;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final String INPUT_STRING = "Progtech";

    private static final Integer INPUT_INTEGER = 4;

    private static final String INPUT_INTEGER_STRING = "4";

    private static final String READ_ERROR_MESSAGE = "Hiba a beolvasás közben\n";

    private static final String ADD_NUMBER_MESSAGE = "Számot adj meg!";

    @Test
    void testReadStringShouldReturnInputString() {
        // given
        Scanner scanner = new Scanner(INPUT_STRING);
        underTest = new InputReader(scanner);

        // when
        String input = underTest.readString();

        // then
        assertEquals(INPUT_STRING, input);
    }

    @Test
    void testReadStringShouldMakeError() {
        // given
        Scanner scanner = new Scanner(INPUT_STRING);
        scanner.close();
        underTest = new InputReader(scanner);
        System.setErr(new PrintStream(outputStreamCaptor));

        // when
        underTest.readString();

        // then
        assertEquals(READ_ERROR_MESSAGE, outputStreamCaptor.toString());
    }

    @Test
    void testReadIntegerShouldReturnInputInteger() {
        // given
        Scanner scanner = new Scanner(INPUT_INTEGER_STRING);
        underTest = new InputReader(scanner);

        // when
        Integer input = underTest.readInteger();

        // then
        assertEquals(INPUT_INTEGER, input);
    }

    @Test
    void testReadIntegerShouldMakeError() {
        // given
        Scanner scanner = new Scanner(INPUT_STRING);
        scanner.close();
        underTest = new InputReader(scanner);
        System.setErr(new PrintStream(outputStreamCaptor));

        // when
        underTest.readInteger();

        // then
        assertEquals(READ_ERROR_MESSAGE, outputStreamCaptor.toString());
    }

    /*
    @Test
    void testReadIntegerShouldWriteOutAddNumber() {
        // given
        Scanner scanner = new Scanner(INPUT_STRING);
        underTest = new InputReader(scanner);
        System.setOut(new PrintStream(outputStreamCaptor));

        // when
        InputMismatchException exception = assertThrows(InputMismatchException.class,
                () -> underTest.readInteger());

        // then
        assertEquals(ADD_NUMBER_MESSAGE, exception.getMessage());
    }
     */
}
