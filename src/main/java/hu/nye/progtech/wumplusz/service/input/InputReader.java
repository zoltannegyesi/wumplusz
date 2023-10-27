package hu.nye.progtech.wumplusz.service.input;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Komponens, ami beolvassa a felhasználó bevitelt konzolról.
 */

public class InputReader {

    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Beolvassa az inputot konzolról, majd Stringként visszaadja.
     * Ha a Scanner nem talál sort, vagy le van zárva, akkor null-lal tér vissza.
     *
     * @return a felhasználó neve Stringként.
     */

    public String readString() {
        String input = null;
        try {
            input = scanner.next();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("Hiba a beolvasás közben");
        }
        return input;
    }

    /**
     * Beolvassa a következő integert az inputról.
     * Ha nem számot adott meg, újra bekéri.
     * Ha nem talál sort, le van zárva a scanner, akkor null-lal tér vissza.
     */
    public Integer readInteger() throws InputMismatchException {
        Integer input = null;
        try {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
            } else {
                throw new InputMismatchException("Számot adj meg!");
            }

        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("Hiba a beolvasás közben");
        }
        return input;
    }
}
