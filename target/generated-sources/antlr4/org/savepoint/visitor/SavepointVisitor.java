// Generated from org\savepoint\visitor\Savepoint.g4 by ANTLR 4.9.3
package org.savepoint.visitor;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SavepointParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SavepointVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SavepointParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SavepointParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SavepointParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(SavepointParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printFunctionCall}
	 * labeled alternative in {@link SavepointParser#systemFunctionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintFunctionCall(SavepointParser.PrintFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(SavepointParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesesExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesesExpression(SavepointParser.ParenthesesExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanBinaryOpExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanBinaryOpExpression(SavepointParser.BooleanBinaryOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numericAddOpExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericAddOpExpression(SavepointParser.NumericAddOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(SavepointParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(SavepointParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanUnaryOpExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanUnaryOpExpression(SavepointParser.BooleanUnaryOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringBinaryOpExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringBinaryOpExpression(SavepointParser.StringBinaryOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numericMultiOpExpression}
	 * labeled alternative in {@link SavepointParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericMultiOpExpression(SavepointParser.NumericMultiOpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#booleanUnaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanUnaryOp(SavepointParser.BooleanUnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#booleanBinaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanBinaryOp(SavepointParser.BooleanBinaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#numericMultiOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericMultiOp(SavepointParser.NumericMultiOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#numericAddOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericAddOp(SavepointParser.NumericAddOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SavepointParser#stringBinaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringBinaryOp(SavepointParser.StringBinaryOpContext ctx);
}