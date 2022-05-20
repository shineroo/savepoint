package org.savepoint.visitor;


import java.util.Stack;

public class SavepointVisitorImpl extends SavepointBaseVisitor<Object> {

    //private final Map<String, Object> symbols = new HashMap<>();

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
            return ctx.STRING().getText();
        }
        return null;
    }

    @Override
    public Object visitVariableDeclaration(SavepointParser.VariableDeclarationContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        Object value = visit(ctx.expression());

        //if(!evalType(, value)) {
        //    throw new RuntimeException("Object in incorrect format.");
        //}

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
        else if(currentScope.evalType("double", thing1) && currentScope.evalType("double", thing2))
            return switch (ctx.numericAddOp().getText())
                    {
                        case "+" -> (Double)val1+(Double) val2;
                        case "-" -> (Double)val1-(Double) val2;
                        default -> null;
                    };
        else if(currentScope.evalType("string", thing1) && currentScope.evalType("string", thing2))
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
        if(currentScope.evalType("int", thing1) && currentScope.evalType("int", thing2))
            return switch (ctx.numericMultiOp().getText())
                    {
                        case "*" -> (Integer)val1*(Integer) val2;
                        case "/" -> (Integer)val1/(Integer) val2;
                        case "%" -> (Integer)val1%(Integer) val2;
                        default -> null;
                    };
        else if(currentScope.evalType("double", thing1) && currentScope.evalType("double", thing2))
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
    public Object visitComparisonExpression(SavepointParser.ComparisonExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        String thing1 = val1.toString();
        String thing2 = val2.toString();

        // booleanCompareOp: '>' | '<' | '<=' | '>=' | '==' | '!=';
        if(evalType("int", thing1) && evalType("int", thing2))
            return switch (ctx.booleanCompareOp().getText())
                    {
                        case "==" -> ((Integer) val1).equals((Integer) val2);
                        case "!=" -> !((Integer) val1).equals((Integer) val2);
                        case ">" -> ((Integer)val1) > ((Integer)val2);
                        case ">=" -> ((Integer)val1) >= ((Integer)val2);
                        case "<" -> ((Integer)val1) < ((Integer)val2);
                        case "<=" -> ((Integer)val1) <= ((Integer)val2);
                        default -> null;
                    };
        else if(evalType("double", thing1) && evalType("double", thing2))
            return switch (ctx.booleanCompareOp().getText())
                    {
                        case "==" -> ((Double) val1).equals((Double) val2);
                        case "!=" -> !((Double) val1).equals((Double) val2);
                        case ">" -> ((Double)val1) > ((Double)val2);
                        case ">=" -> ((Double)val1) >= ((Double)val2);
                        case "<" -> ((Double)val1) < ((Double)val2);
                        case "<=" -> ((Double)val1) <= ((Double)val2);
                        default -> null;
                    };
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
            visit(ctx.block(0));
        }
        else {
            visit(ctx.block(1));
        }
        return null;
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
