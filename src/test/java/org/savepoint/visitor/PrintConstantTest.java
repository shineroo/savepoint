package org.savepoint.visitor;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintConstantTest {

    @Test
    void print_integer_constant() {
        String program = """
                WriteLine(5);
                """;

        String expected =
                """
                5
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void print_decimal_constant() {
        String program = """
                WriteLine(5.5);
                """;

        String expected =
                """
                5.5
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void print_boolean_constant() {
        String program = """
                WriteLine(true);
                """;

        String expected =
                """
                true
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void print_string_constant() {
        String program = """
                WriteLine("5");
                """;

        String expected =
                """
                5
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }
}