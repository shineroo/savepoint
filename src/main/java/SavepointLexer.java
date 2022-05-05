// Generated from Savepoint.g4 by ANTLR 4.5.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SavepointLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, PRINT=13, INTEGER=14, DECIMAL=15, BOOLEAN=16, 
		STRING=17, IDENTIFIER=18, COMMENT=19, WS=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "PRINT", "INTEGER", "DECIMAL", "BOOLEAN", "STRING", 
		"IDENTIFIER", "COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'('", "')'", "'!'", "'||'", "'&&'", "'*'", "'/'", "'%'", 
		"'+'", "'-'", "'..'", "'print'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "PRINT", "INTEGER", "DECIMAL", "BOOLEAN", "STRING", "IDENTIFIER", 
		"COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SavepointLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Savepoint.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26\u0098\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\6\17N\n\17\r\17\16\17O\3\20"+
		"\6\20S\n\20\r\20\16\20T\3\20\3\20\6\20Y\n\20\r\20\16\20Z\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21f\n\21\3\22\3\22\3\22\3\22\7\22l\n"+
		"\22\f\22\16\22o\13\22\3\22\3\22\3\23\3\23\7\23u\n\23\f\23\16\23x\13\23"+
		"\3\24\3\24\3\24\3\24\7\24~\n\24\f\24\16\24\u0081\13\24\3\24\3\24\3\24"+
		"\3\24\7\24\u0087\n\24\f\24\16\24\u008a\13\24\3\24\3\24\5\24\u008e\n\24"+
		"\3\24\3\24\3\25\6\25\u0093\n\25\r\25\16\25\u0094\3\25\3\25\3\u0088\2\26"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26\3\2\t\3\2\62;\3\2$$\6\2\f\f\17\17$$^^\4\2"+
		"\f\f\17\17\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\16\17\"\"\u00a2\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3"+
		"\2\2\2\13\63\3\2\2\2\r\66\3\2\2\2\179\3\2\2\2\21;\3\2\2\2\23=\3\2\2\2"+
		"\25?\3\2\2\2\27A\3\2\2\2\31C\3\2\2\2\33F\3\2\2\2\35M\3\2\2\2\37R\3\2\2"+
		"\2!e\3\2\2\2#g\3\2\2\2%r\3\2\2\2\'\u008d\3\2\2\2)\u0092\3\2\2\2+,\7?\2"+
		"\2,\4\3\2\2\2-.\7*\2\2.\6\3\2\2\2/\60\7+\2\2\60\b\3\2\2\2\61\62\7#\2\2"+
		"\62\n\3\2\2\2\63\64\7~\2\2\64\65\7~\2\2\65\f\3\2\2\2\66\67\7(\2\2\678"+
		"\7(\2\28\16\3\2\2\29:\7,\2\2:\20\3\2\2\2;<\7\61\2\2<\22\3\2\2\2=>\7\'"+
		"\2\2>\24\3\2\2\2?@\7-\2\2@\26\3\2\2\2AB\7/\2\2B\30\3\2\2\2CD\7\60\2\2"+
		"DE\7\60\2\2E\32\3\2\2\2FG\7r\2\2GH\7t\2\2HI\7k\2\2IJ\7p\2\2JK\7v\2\2K"+
		"\34\3\2\2\2LN\t\2\2\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\36\3\2"+
		"\2\2QS\t\2\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VX\7\60"+
		"\2\2WY\t\2\2\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[ \3\2\2\2\\]\7"+
		"v\2\2]^\7t\2\2^_\7w\2\2_f\7g\2\2`a\7h\2\2ab\7c\2\2bc\7n\2\2cd\7u\2\2d"+
		"f\7g\2\2e\\\3\2\2\2e`\3\2\2\2f\"\3\2\2\2gm\t\3\2\2hl\n\4\2\2ij\7^\2\2"+
		"jl\n\5\2\2kh\3\2\2\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2"+
		"om\3\2\2\2pq\t\3\2\2q$\3\2\2\2rv\t\6\2\2su\t\7\2\2ts\3\2\2\2ux\3\2\2\2"+
		"vt\3\2\2\2vw\3\2\2\2w&\3\2\2\2xv\3\2\2\2yz\7\61\2\2z{\7\61\2\2{\177\3"+
		"\2\2\2|~\n\5\2\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2"+
		"\2\u0080\u008e\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7\61\2\2\u0083\u0084"+
		"\7,\2\2\u0084\u0088\3\2\2\2\u0085\u0087\13\2\2\2\u0086\u0085\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008b\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7,\2\2\u008c\u008e\7\61\2\2\u008d"+
		"y\3\2\2\2\u008d\u0082\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\b\24\2\2"+
		"\u0090(\3\2\2\2\u0091\u0093\t\b\2\2\u0092\u0091\3\2\2\2\u0093\u0094\3"+
		"\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0097\b\25\2\2\u0097*\3\2\2\2\16\2OTZekmv\177\u0088\u008d\u0094\3\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}