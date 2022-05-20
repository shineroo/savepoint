package org.savepoint.visitor;

import org.antlr.v4.runtime.misc.Pair;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SavepointVisitorImpl extends SavepointBaseVisitor<Object> {

    private final Map<String, Object> symbols = new HashMap<>();

    private final Stack<SavepointScope> scopeStack = new Stack<>();
    private SavepointScope currentScope = new SavepointScope();

    @Override
    public Object visitPrintFunctionCall(SavepointParser.PrintFunctionCallContext ctx) {
        String text = visit(ctx.expression()).toString();
        //if(text.startsWith("\"") && text.endsWith("\""))
         //   text = text.substring(1, text.length()-1);
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
            String item = ctx.STRING().getText();
            return item;
        }
        return null;
    }

    @Override
    public Object visitVariableDeclaration(SavepointParser.VariableDeclarationContext ctx) {
        /*String type = ctx.TYPE().getText();
        Object value = visit(ctx.expression());
        String varName = ctx.IDENTIFIER().getText();

        this.symbols.put(varName, value);
        return null;*/

        String varName = ctx.IDENTIFIER().getText();
        Object value = visit(ctx.expression());

        if(!evalType(ctx.TYPE().getText(), value)) {
            System.out.println("Object in incorrect format."); //TODO: make this show something more
            System.exit(1);
        }

        this.currentScope.declareVariable(varName, value);
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
        if(evalType("int", thing1) && evalType("int", thing2)) //TODO: make it so you can add/multiply ints with doubles
            return switch (ctx.numericAddOp().getText())
                    {
                        case "+" -> (Integer)val1+(Integer) val2;
                        case "-" -> (Integer)val1-(Integer) val2;
                        default -> null;
                    };
        else if(evalType("double", thing1) && evalType("double", thing2))
            return switch (ctx.numericAddOp().getText())
                    {
                        case "+" -> (Double)val1+(Double) val2;
                        case "-" -> (Double)val1-(Double) val2;
                        default -> null;
                    };
        else if(evalType("string", thing1) && evalType("string", thing2))
            return switch (ctx.numericAddOp().getText())
                    {
                        case "+" -> (String)val1+(String) val2;
                        default -> null;
                    };
        else return null;
    }

    @Override
    public Object visitNumericMultiOpExpression(SavepointParser.NumericMultiOpExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        String thing1 = val1.toString();
        String thing2 = val2.toString();
        if(evalType("int", thing1) && evalType("int", thing2))
            return switch (ctx.numericMultiOp().getText())
                    {
                        case "*" -> (Integer)val1*(Integer) val2;
                        case "/" -> (Integer)val1/(Integer) val2;
                        case "%" -> (Integer)val1%(Integer) val2;
                        default -> null;
                    };
        else if(evalType("double", thing1) && evalType("double", thing2))
            return switch (ctx.numericMultiOp().getText())
                    {
                        case "*" -> (Double)val1*(Double) val2;
                        case "/" -> (Double)val1/(Double) val2;
                        case "%" -> (Double)val1%(Double) val2;
                        default -> null;
                    };
        else return null;
    }

    @Override
    public Object visitParenthesesExpression(SavepointParser.ParenthesesExpressionContext ctx) {
        return visit(ctx.expression());
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
    public Object visitLoop(SavepointParser.LoopContext ctx) {
        boolean condition = (boolean)visit(ctx.expression());
        Object val = new Object();
        while(condition)
        {
            val = visit(ctx.block());
            condition = (boolean) visit(ctx.expression());
        }
        return null;
    }

    public boolean evalType(String type, Object value) //TODO: change this to switch or even into a better idea entirely
    {
        if(value==null)
            return false;
        String thing = value.toString();
        if(type.equals("int"))
        {
            try{ Integer.parseInt(thing);
            return true;}
            catch(Exception e){return false;}
        }
        else if(type.equals("double"))
        {
            try{ Double.parseDouble(thing);
                return true;}
            catch(Exception e){return false;}
        }
        else if(type.equals("bool"))
        {
            if(thing.equals("true") || thing.equals("false"))
                return true;
            return false;
        }
        else if(type.equals("string"))
        {
            var thing2 = thing.toCharArray();
            if(thing2[0]=='\"' && thing2[thing2.length-1]=='\"')
                return true;
            else return false;
        }
        else return false;
    }

}
