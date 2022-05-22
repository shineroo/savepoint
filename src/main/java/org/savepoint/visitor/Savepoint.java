package org.savepoint.visitor;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Savepoint {
    public static void main(String[] args) {
        try {
            execute(CharStreams.fromFileName(args[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object execute(CharStream stream) {
        SavepointLexer lexer = new SavepointLexer(stream);
        SavepointParser parser = new SavepointParser(new CommonTokenStream(lexer));
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();

        SavepointVisitorImpl visitor = new SavepointVisitorImpl();
        //VisitorMathOps visitor2 = new VisitorMathOps();
        //visitor2.visit(tree);
        return visitor.visit(tree);
    }
}
