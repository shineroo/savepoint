package org.savepoint.visitor;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MathOperationsTest {

    @Test
    void Simple_int_Addition() {
        String program = """
                WriteLine(5+10);
                """;

        String expected =
                """
                15
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void Simple_int_Substraction() {
        String program = """
                WriteLine(5-10);
                """;

        String expected =
                """
                -5
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void Simple_double_Addition() {
        String program = """
                WriteLine(5.5+10.5);
                """;

        String expected =
                """
                16.0
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void Simple_double_substraction() {
        String program = """
                WriteLine(5.5 - 10.5);
                """;

        String expected =
                """
                -5.0
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void int_multiplication_addition_parenthesies() {
        String program = """
                WriteLine(5*(7+(10+2)));
                """;

        String expected =
                """
                95
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    void double_int_multiplication_addition_parenthesies() {
        String program = """
                WriteLine(5.5*(7.3+(10+2)));
                """;

        String expected =
                """
                106.15
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }
    @Test
    void Simple_int_division() {
        String program = """
                WriteLine(10 / 5);
                """;

        String expected =
                """
                2
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void Simple_int_Other_division() {
        String program = """
                WriteLine(10 % 3);
                """;

        String expected =
                """
                1
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void Simple_double_division() {
        String program = """
                WriteLine(7.5 / 2.5);
                """;

        String expected =
                """
                3.0
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void Simple_double_other_division() {
        String program = """
                WriteLine(7.5 % 3);
                """;

        String expected =
                """
                1.5
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

}
