package org.savepoint.visitor;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LogicalOperatorTest {

    @Test
    void equals() {
        String program = """
                if(5==6){
                WriteLine(1);
                }
                else{
                WriteLine(2);
                }
                """;

        String expected =
                """
                2
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void not_operator() {
        String program = """
                bool test = false;
                if(!test){
                WriteLine(1);
                }
                else{
                WriteLine(2);
                }
                """;

        String expected =
                """
                1
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }
    @Test
    void more_operator() {
        String program = """
                if(5>6){
                WriteLine(1);
                }
                else{
                WriteLine(2);
                }
                """;

        String expected =
                """
                2
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void less_operator() {
        String program = """
                if(5<6){
                WriteLine(1);
                }
                else{
                WriteLine(2);
                }
                """;

        String expected =
                """
                1
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void more_equals_operator() {
        String program = """
                if(5>=6){
                WriteLine(1);
                }
                else{
                WriteLine(2);
                }
                """;

        String expected =
                """
                2
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void less_equals_operator() {
        String program = """
                if(5<=6){
                WriteLine(1);
                }
                else{
                WriteLine(2);
                }
                """;

        String expected =
                """
                1
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }

    @Test
    void not_equals_operator() {
        String program = """
                if(5!=6){
                WriteLine(1);
                }
                else{
                WriteLine(2);
                }
                """;

        String expected =
                """
                1
                """;

        String actual = (String)Savepoint.execute(CharStreams.fromString(program));

        assertEquals(expected, actual);
    }
}
