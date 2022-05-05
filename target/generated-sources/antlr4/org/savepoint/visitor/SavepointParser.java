// Generated from org\savepoint\visitor\Savepoint.g4 by ANTLR 4.9.3
package org.savepoint.visitor;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SavepointParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, PRINT=13, INTEGER=14, DECIMAL=15, BOOLEAN=16, 
		STRING=17, IDENTIFIER=18, COMMENT=19, WS=20;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_assignment = 2, RULE_systemFunctionCall = 3, 
		RULE_constant = 4, RULE_expression = 5, RULE_booleanUnaryOp = 6, RULE_booleanBinaryOp = 7, 
		RULE_numericMultiOp = 8, RULE_numericAddOp = 9, RULE_stringBinaryOp = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "assignment", "systemFunctionCall", "constant", 
			"expression", "booleanUnaryOp", "booleanBinaryOp", "numericMultiOp", 
			"numericAddOp", "stringBinaryOp"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'('", "')'", "'!'", "'||'", "'&&'", "'*'", "'/'", "'%'", 
			"'+'", "'-'", "'..'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "PRINT", "INTEGER", "DECIMAL", "BOOLEAN", "STRING", "IDENTIFIER", 
			"COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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

	@Override
	public String getGrammarFileName() { return "Savepoint.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SavepointParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SavepointParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(22);
				statement();
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PRINT || _la==IDENTIFIER );
			setState(27);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public SystemFunctionCallContext systemFunctionCall() {
			return getRuleContext(SystemFunctionCallContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(31);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				assignment();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				systemFunctionCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SavepointParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(IDENTIFIER);
			setState(34);
			match(T__0);
			setState(35);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SystemFunctionCallContext extends ParserRuleContext {
		public SystemFunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_systemFunctionCall; }
	 
		public SystemFunctionCallContext() { }
		public void copyFrom(SystemFunctionCallContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrintFunctionCallContext extends SystemFunctionCallContext {
		public TerminalNode PRINT() { return getToken(SavepointParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintFunctionCallContext(SystemFunctionCallContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitPrintFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SystemFunctionCallContext systemFunctionCall() throws RecognitionException {
		SystemFunctionCallContext _localctx = new SystemFunctionCallContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_systemFunctionCall);
		try {
			_localctx = new PrintFunctionCallContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(PRINT);
			setState(38);
			match(T__1);
			setState(39);
			expression(0);
			setState(40);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(SavepointParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(SavepointParser.DECIMAL, 0); }
		public TerminalNode BOOLEAN() { return getToken(SavepointParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(SavepointParser.STRING, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << BOOLEAN) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParenthesesExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesesExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitParenthesesExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanBinaryOpExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BooleanBinaryOpContext booleanBinaryOp() {
			return getRuleContext(BooleanBinaryOpContext.class,0);
		}
		public BooleanBinaryOpExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitBooleanBinaryOpExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumericAddOpExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NumericAddOpContext numericAddOp() {
			return getRuleContext(NumericAddOpContext.class,0);
		}
		public NumericAddOpExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitNumericAddOpExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstantExpressionContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitConstantExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierExpressionContext extends ExpressionContext {
		public TerminalNode IDENTIFIER() { return getToken(SavepointParser.IDENTIFIER, 0); }
		public IdentifierExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanUnaryOpExpressionContext extends ExpressionContext {
		public BooleanUnaryOpContext booleanUnaryOp() {
			return getRuleContext(BooleanUnaryOpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BooleanUnaryOpExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitBooleanUnaryOpExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringBinaryOpExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public StringBinaryOpContext stringBinaryOp() {
			return getRuleContext(StringBinaryOpContext.class,0);
		}
		public StringBinaryOpExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitStringBinaryOpExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumericMultiOpExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NumericMultiOpContext numericMultiOp() {
			return getRuleContext(NumericMultiOpContext.class,0);
		}
		public NumericMultiOpExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitNumericMultiOpExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case DECIMAL:
			case BOOLEAN:
			case STRING:
				{
				_localctx = new ConstantExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(45);
				constant();
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new IdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				match(IDENTIFIER);
				}
				break;
			case T__1:
				{
				_localctx = new ParenthesesExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(47);
				match(T__1);
				setState(48);
				expression(0);
				setState(49);
				match(T__2);
				}
				break;
			case T__3:
				{
				_localctx = new BooleanUnaryOpExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(51);
				booleanUnaryOp();
				setState(52);
				expression(5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(74);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(72);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new BooleanBinaryOpExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(56);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(57);
						booleanBinaryOp();
						setState(58);
						expression(5);
						}
						break;
					case 2:
						{
						_localctx = new NumericMultiOpExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(60);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(61);
						numericMultiOp();
						setState(62);
						expression(4);
						}
						break;
					case 3:
						{
						_localctx = new NumericAddOpExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(64);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(65);
						numericAddOp();
						setState(66);
						expression(3);
						}
						break;
					case 4:
						{
						_localctx = new StringBinaryOpExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(68);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(69);
						stringBinaryOp();
						setState(70);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BooleanUnaryOpContext extends ParserRuleContext {
		public BooleanUnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanUnaryOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitBooleanUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanUnaryOpContext booleanUnaryOp() throws RecognitionException {
		BooleanUnaryOpContext _localctx = new BooleanUnaryOpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_booleanUnaryOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanBinaryOpContext extends ParserRuleContext {
		public BooleanBinaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanBinaryOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitBooleanBinaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanBinaryOpContext booleanBinaryOp() throws RecognitionException {
		BooleanBinaryOpContext _localctx = new BooleanBinaryOpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_booleanBinaryOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericMultiOpContext extends ParserRuleContext {
		public NumericMultiOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericMultiOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitNumericMultiOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericMultiOpContext numericMultiOp() throws RecognitionException {
		NumericMultiOpContext _localctx = new NumericMultiOpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_numericMultiOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericAddOpContext extends ParserRuleContext {
		public NumericAddOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericAddOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitNumericAddOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericAddOpContext numericAddOp() throws RecognitionException {
		NumericAddOpContext _localctx = new NumericAddOpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_numericAddOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringBinaryOpContext extends ParserRuleContext {
		public StringBinaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringBinaryOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SavepointVisitor ) return ((SavepointVisitor<? extends T>)visitor).visitStringBinaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringBinaryOpContext stringBinaryOp() throws RecognitionException {
		StringBinaryOpContext _localctx = new StringBinaryOpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_stringBinaryOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26Z\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\6\2\32\n\2\r\2\16\2\33\3\2\3\2\3\3\3\3\5\3\"\n\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\79\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\7\7K\n\7\f\7\16\7N\13\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\f\2\3\f\r\2\4\6\b\n\f\16\20\22\24\26\2\6\3\2\20\23\3\2\7\b\3\2"+
		"\t\13\3\2\f\r\2W\2\31\3\2\2\2\4!\3\2\2\2\6#\3\2\2\2\b\'\3\2\2\2\n,\3\2"+
		"\2\2\f8\3\2\2\2\16O\3\2\2\2\20Q\3\2\2\2\22S\3\2\2\2\24U\3\2\2\2\26W\3"+
		"\2\2\2\30\32\5\4\3\2\31\30\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2\33\34\3"+
		"\2\2\2\34\35\3\2\2\2\35\36\7\2\2\3\36\3\3\2\2\2\37\"\5\6\4\2 \"\5\b\5"+
		"\2!\37\3\2\2\2! \3\2\2\2\"\5\3\2\2\2#$\7\24\2\2$%\7\3\2\2%&\5\f\7\2&\7"+
		"\3\2\2\2\'(\7\17\2\2()\7\4\2\2)*\5\f\7\2*+\7\5\2\2+\t\3\2\2\2,-\t\2\2"+
		"\2-\13\3\2\2\2./\b\7\1\2/9\5\n\6\2\609\7\24\2\2\61\62\7\4\2\2\62\63\5"+
		"\f\7\2\63\64\7\5\2\2\649\3\2\2\2\65\66\5\16\b\2\66\67\5\f\7\7\679\3\2"+
		"\2\28.\3\2\2\28\60\3\2\2\28\61\3\2\2\28\65\3\2\2\29L\3\2\2\2:;\f\6\2\2"+
		";<\5\20\t\2<=\5\f\7\7=K\3\2\2\2>?\f\5\2\2?@\5\22\n\2@A\5\f\7\6AK\3\2\2"+
		"\2BC\f\4\2\2CD\5\24\13\2DE\5\f\7\5EK\3\2\2\2FG\f\3\2\2GH\5\26\f\2HI\5"+
		"\f\7\4IK\3\2\2\2J:\3\2\2\2J>\3\2\2\2JB\3\2\2\2JF\3\2\2\2KN\3\2\2\2LJ\3"+
		"\2\2\2LM\3\2\2\2M\r\3\2\2\2NL\3\2\2\2OP\7\6\2\2P\17\3\2\2\2QR\t\3\2\2"+
		"R\21\3\2\2\2ST\t\4\2\2T\23\3\2\2\2UV\t\5\2\2V\25\3\2\2\2WX\7\16\2\2X\27"+
		"\3\2\2\2\7\33!8JL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}