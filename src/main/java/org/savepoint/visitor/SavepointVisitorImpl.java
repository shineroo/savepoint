package org.savepoint.visitor;

import org.antlr.v4.runtime.misc.Pair;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class SavepointVisitorImpl extends SavepointBaseVisitor<Object> {

    private final Map<String, Object> symbols = new HashMap<>();

    @Override
    public Object visitPrintFunctionCall(SavepointParser.PrintFunctionCallContext ctx) {
        String text = visit(ctx.expression()).toString();
        if(text.startsWith("\"") && text.endsWith("\""))
            text = text.substring(1, text.length()-1);
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
    public Object visitAssignment(SavepointParser.AssignmentContext ctx)
    {
        String type = ctx.TYPE().getText();
        Object value = visit(ctx.expression());
        String varName = ctx.IDENTIFIER().getText();
        if(!evalType(type, value)) {
            System.out.println("Object in incorrect format.");
            System.exit(1);
        }
        this.symbols.put(varName, value);
        return null;
    }


    @Override
    public Object visitIdentifierExpression(SavepointParser.IdentifierExpressionContext ctx) {
        return symbols.get(ctx.IDENTIFIER().getText());
    }

    public boolean evalType(String type, Object value)
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
