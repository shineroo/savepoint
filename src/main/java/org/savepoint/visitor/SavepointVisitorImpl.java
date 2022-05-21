package org.savepoint.visitor;


import org.antlr.v4.runtime.tree.RuleNode;
import org.savepoint.visitor.exceptions.SavepointException;
import org.savepoint.visitor.exceptions.SavepointVariableIncorrectFormat;

import java.util.Stack;

public class SavepointVisitorImpl extends SavepointBaseVisitor<Object> {

    //private final Map<String, Object> symbols = new HashMap<>();

    private final Stack<SavepointScope> scopeStack = new Stack<>();
    private SavepointScope currentScope = new SavepointScope();

    @Override
    public Object visitPrintFunctionCall(SavepointParser.PrintFunctionCallContext ctx) {
        String text = visit(ctx.expression()).toString();
        text = text.replaceAll("\"", "");
        System.out.println(text);
        return null;
    }

    @Override
    public Object visitConstantExpression(SavepointParser.ConstantExpressionContext ctx) {
        return visit(ctx.constant());
    }

    @Override
    public Object visitConstant(SavepointParser.ConstantContext ctx) {
        if (ctx.INTEGER() != null)
            return Integer.parseInt(ctx.INTEGER().getText());
        else if(ctx.DECIMAL() != null)
            return Double.parseDouble(ctx.DECIMAL().getText());
        else if(ctx.BOOLEAN() != null)
            return Boolean.parseBoolean(ctx.BOOLEAN().getText());
        else if(ctx.STRING() != null) {
            return ctx.STRING().getText();
        }
        return null;
    }

    @Override
    public Object visitVariableDeclaration(SavepointParser.VariableDeclarationContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        Object value = visit(ctx.expression());

        this.currentScope.declareVariable(ctx.TYPE().getText(), varName, value);
        return null;
    }

    @Override
    public Object visitAssignment(SavepointParser.AssignmentContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        Object value = visit(ctx.expression());
        this.currentScope.changeVariable(varName, value);
        return null;
    }

    @Override
    public Object visitIdentifierExpression(SavepointParser.IdentifierExpressionContext ctx) {
        return this.currentScope.resolveVariable(ctx.IDENTIFIER().getText());
    }

    @Override
    public Object visitNumericAddOpExpression(SavepointParser.NumericAddOpExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        String thing1 = val1.toString();
        String thing2 = val2.toString();
        if(currentScope.evalType("int", thing1) && currentScope.evalType("int", thing2)) //TODO: make it so you can add/multiply ints with doubles
            return switch (ctx.numericAddOp().getText())
                    {
                        case "+" -> (Integer)val1+(Integer) val2;
                        case "-" -> (Integer)val1-(Integer) val2;
                        default -> null;
                    };
        else if(currentScope.evalType("double", thing1) && currentScope.evalType("double", thing2)){
            double first = Double.parseDouble(thing1);
            double second = Double.parseDouble(thing2);

            return switch (ctx.numericAddOp().getText())
                    {
                        case "+" -> first+second;
                        case "-" -> first-second;
                        default -> null;
                    };}
        else if(currentScope.evalType("string", thing1) && currentScope.evalType("string", thing2)) {
            if (ctx.numericAddOp().getText().equals("+"))
                return val1 + (String) val2;
            return null;
        }
        else return null;
    }

    @Override
    public Object visitNumericMultiOpExpression(SavepointParser.NumericMultiOpExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        String thing1 = val1.toString();
        String thing2 = val2.toString();
        if(currentScope.evalType("int", thing1) && currentScope.evalType("int", thing2))
            return switch (ctx.numericMultiOp().getText())
                    {
                        case "*" -> (Integer)val1*(Integer) val2;
                        case "/" -> (Integer)val1/(Integer) val2;
                        case "%" -> (Integer)val1%(Integer) val2;
                        default -> null;
                    };
        else if(currentScope.evalType("double", thing1) && currentScope.evalType("double", thing2)){
            double first = Double.parseDouble(thing1);
            double second = Double.parseDouble(thing2);

            return switch (ctx.numericMultiOp().getText())
                    {
                        case "*" -> first*second;
                        case "/" -> first/second;
                        case "%" -> first%second;
                        default -> null;
                    };}
        else return null;
    }

    @Override
    public Object visitComparisonExpression(SavepointParser.ComparisonExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        String thing1 = val1.toString();
        String thing2 = val2.toString();

        // booleanCompareOp: '>' | '<' | '<=' | '>=' | '==' | '!=';
        if(currentScope.evalType("int", thing1) && currentScope.evalType("int", thing2))
            return switch (ctx.booleanCompareOp().getText())
                    {
                        case "==" -> val1.equals(val2);
                        case "!=" -> !val1.equals(val2);
                        case ">" -> ((Integer)val1) > ((Integer)val2);
                        case ">=" -> ((Integer)val1) >= ((Integer)val2);
                        case "<" -> ((Integer)val1) < ((Integer)val2);
                        case "<=" -> ((Integer)val1) <= ((Integer)val2);
                        default -> null;
                    };
        else if(currentScope.evalType("double", thing1) && currentScope.evalType("double", thing2)){
            double first = Double.parseDouble(thing1);
            double second = Double.parseDouble(thing2);

            return switch (ctx.booleanCompareOp().getText())
                    {
                        case "==" -> (first)==(second);
                        case "!=" -> (first)!=(second);
                        case ">" -> (first) > (second);
                        case ">=" -> (first) >= (second);
                        case "<" -> (first) < (second);
                        case "<=" -> (first) <= (second);
                        default -> null;
                    };}
        else return null;

        /*if (getType(thing1) == getType(thing2)){  // maybe like this
            switch(getType(thing1)) {
                case "int" -> {
                    return switch (ctx.booleanCompareOp().getText()) {
                        case "==" -> ((Integer) val1).equals((Integer) val2);
                        default -> null;
                    };
                }
            }
        }*/
    }

    @Override
    public Object visitParenthesesExpression(SavepointParser.ParenthesesExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitIfElseStatement(SavepointParser.IfElseStatementContext ctx) {
        boolean value = (boolean)visit(ctx.expression());
        if (value) {
            return visit(ctx.block(0));
        }
        else {
            if(ctx.block(1)!=null)
                return visit(ctx.block(1));
            return null;
        }
    }

    @Override
    public Object visitBlock(SavepointParser.BlockContext ctx) {
        this.scopeStack.push(currentScope);
        currentScope = new SavepointScope(currentScope);
        super.visitBlock(ctx);
        currentScope = scopeStack.pop();
        return null;
    }

    @Override
    public Object visitLoopW(SavepointParser.LoopWContext ctx) {
        boolean condition = (boolean)visit(ctx.expression());
        Object val = new Object();
        while(condition)
        {
            val = visit(ctx.block());
            condition = (boolean) visit(ctx.expression());
        }
        return null;
    }

    @Override
    public Object visitLoopF(SavepointParser.LoopFContext ctx) {
        try{visitStatement(ctx.statement());}
        catch(NullPointerException ex){}
        boolean condition = (boolean)visit(ctx.expression());
        while(condition)
        {
            visit(ctx.block());
            visitAssignment(ctx.assignment());
            condition = (boolean) visit(ctx.expression());
        }
        return null;
    }


    @Override
    protected boolean shouldVisitNextChild(RuleNode node, Object currentResult) {
        return !(currentResult instanceof ReturnValue);
    }

    @Override
    public Object visitReturnStatement(SavepointParser.ReturnStatementContext ctx) {
        if(ctx.expression() == null)
            return new ReturnValue(null);
        else
            return new ReturnValue(this.visit(ctx.expression()));
    }



}
