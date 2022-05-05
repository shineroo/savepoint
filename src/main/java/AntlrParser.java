import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import org.savepoint.visitor.*;

import java.io.IOException;

public class AntlrParser {

    public static Object main(String[] args) throws IOException {
        CharStream charStream = CharStreams.fromFileName("testing");
        SavepointLexer lexer = new SavepointLexer(charStream);
        SavepointParser parser = new SavepointParser((new CommonTokenStream(lexer)));
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();

        SavepointVisitorImpl visitor = new SavepointVisitorImpl();
        return visitor.visit(tree);
    }
}
