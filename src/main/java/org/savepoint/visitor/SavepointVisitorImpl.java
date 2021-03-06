package org.savepoint.visitor;


import org.antlr.v4.runtime.tree.RuleNode;
import org.savepoint.visitor.exceptions.SavepointVariableIncorrectFormat;
import org.savepoint.visitor.exceptions.SavepointWrongFunctionTypeException;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileWriter;

public class SavepointVisitorImpl extends SavepointBaseVisitor<Object> {

    public final StringBuilder SYSTEM_OUT = new StringBuilder();

    private final Stack<SavepointScope> scopeStack = new Stack<>();
    private SavepointScope currentScope = new SavepointScope();
    private final Stack<ArrayScope> arrayScopeStack = new Stack<>();
    private ArrayScope currentArrayScope = new ArrayScope();


    private final Map<String, SavepointParser.FunctionDeclarationContext> functions=new HashMap<>();

    @Override
    public Object visitComparisonExpression(SavepointParser.ComparisonExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        return VisitorMathOps.visitComparisonExpression(ctx, val1, val2);
    }

    @Override
    public Object visitBooleanUnaryOpExpression(SavepointParser.BooleanUnaryOpExpressionContext ctx) {
        return !(boolean)visit(ctx.expression());
    }

    @Override
    public Object visitBooleanBinaryOpExpression(SavepointParser.BooleanBinaryOpExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        return VisitorMathOps.visitBooleanBinaryOpExpression(ctx, val1, val2);
    }

    @Override
    public Object visitNumericAddOpExpression(SavepointParser.NumericAddOpExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        return VisitorMathOps.visitNumericAddOpExpression(ctx, val1, val2);
    }

    @Override
    public Object visitNumericMultiOpExpression(SavepointParser.NumericMultiOpExpressionContext ctx) {
        Object val1 = visit(ctx.expression(0));
        Object val2 = visit(ctx.expression(1));
        return VisitorMathOps.visitNumericMultiOpExpression(ctx, val1, val2);
    }

    @Override
    public Object visitProgram(SavepointParser.ProgramContext ctx) {
        super.visitProgram(ctx);
        currentScope.printJsonData();
        return SYSTEM_OUT.toString();
    }

    @Override
    public Object visitPrintFunctionCall(SavepointParser.PrintFunctionCallContext ctx) {
        String text = visit(ctx.expression()).toString();
        text = text.replaceAll("\"", "");
        System.out.println(text);
        SYSTEM_OUT.append(text).append("\n");
        return null;
    }

    @Override
    public Object visitArrayDeclaration(SavepointParser.ArrayDeclarationContext ctx) {
        int index = 0;
        if(ctx.INTEGER()!=null)
            index = Integer.parseInt(ctx.INTEGER().getText());
        else if(ctx.expression() !=null && SavepointScope.evalType("int", visit(ctx.expression())))
            index = (Integer)currentScope.resolveVariable(ctx.expression().getText());
        else throw new SavepointVariableIncorrectFormat(ctx.expression().getText());
        String type = ctx.TYPE().getText();
        String name = ctx.IDENTIFIER().getText();
        currentArrayScope.declareArray(name, index, type);
        return null;
    }

    @Override
    public Object visitArrayElementDeclaration(SavepointParser.ArrayElementDeclarationContext ctx) {
        int index = 0;
        int which = 0;
        if(ctx.INTEGER()!=null)
            index = Integer.parseInt(ctx.INTEGER().getText());
        else if(ctx.expression(0) !=null && SavepointScope.evalType("int", visit(ctx.expression(0)))) {
            index = (Integer) currentScope.resolveVariable(ctx.expression(0).getText());
            which = 1;
        }
        else throw new SavepointVariableIncorrectFormat(ctx.expression(which).getText());
        String name = ctx.IDENTIFIER().getText();
        Object value = visit(ctx.expression(which));
        currentArrayScope.changeElement(name, index, value);
        return null;
    }

    @Override
    public Object visitArrayIdentifierExpression(SavepointParser.ArrayIdentifierExpressionContext ctx) {
        int index = 0;
        if(ctx.INTEGER()!=null)
            index = Integer.parseInt(ctx.INTEGER().getText());
        else if(ctx.expression() !=null && SavepointScope.evalType("int", visit(ctx.expression())))
            index = (Integer)currentScope.resolveVariable(ctx.expression().getText());
        else throw new SavepointVariableIncorrectFormat(ctx.expression().getText());
        return this.currentArrayScope.resolveArrayItem(ctx.IDENTIFIER().getText(), index);
    }

    @Override
    public Object visitReadFunctionCall(SavepointParser.ReadFunctionCallContext ctx) {
        String location = visit(ctx.expression()).toString().replaceAll("\"", "");
        return InOut.readFile(location);
    }

    @Override
    public Object visitWriteFunctionCall(SavepointParser.WriteFunctionCallContext ctx) {
        String value = visit(ctx.expression(0)).toString().replaceAll("\"", "");
        String location = visit(ctx.expression(1)).toString().replaceAll("\"", "");
        InOut.writeFile(value, location);

        return null;
    }

    @Override
    public Object visitAppendFunctionCall(SavepointParser.AppendFunctionCallContext ctx) { //TODO: implement append to file call
        String value = visit(ctx.expression(0)).toString().replaceAll("\"", "");
        String location = visit(ctx.expression(1)).toString().replaceAll("\"", "");
        InOut.appendFile(value, location);

        return null;
    }

    @Override
    public Object visitConstantExpression(SavepointParser.ConstantExpressionContext ctx) {
        return visit(ctx.constant());
    }

    @Override
    public Object visitNegativeConstant(SavepointParser.NegativeConstantContext ctx) {
        if(ctx.INTEGER()!=null){
            return Integer.parseInt("-"+ctx.INTEGER().getText());
        }
        if(ctx.DECIMAL()!=null){
            return Double.parseDouble("-"+ctx.DECIMAL().getText());
        }
        return null;
    }

    @Override
    public Object visitNegativeConstantExpression(SavepointParser.NegativeConstantExpressionContext ctx) {
        return super.visitNegativeConstantExpression(ctx);
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
    public Object visitVariableSPdeclaration(SavepointParser.VariableSPdeclarationContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        Object value = visit(ctx.expression());
        String type = ctx.TYPE().getText();

        currentScope.declareSPvariable(type, varName, value);
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
    public Object visitParenthesesExpression(SavepointParser.ParenthesesExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitIncrement(SavepointParser.IncrementContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Object value = currentScope.resolveVariable(identifier);
        if(SavepointScope.evalType("int", value)){
            this.currentScope.changeVariable(identifier, (Integer)value + 1);
        } else if (SavepointScope.evalType("double", value)) {
            this.currentScope.changeVariable(identifier, (Double)value + 1);
        }
        //this.currentScope.declareVariable(currentScope.getType(value), identifier, value);
        return null;
    }

    public Object visitDecrement(SavepointParser.DecrementContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Object value = currentScope.resolveVariable(identifier);
        if(SavepointScope.evalType("int", value)){
            this.currentScope.changeVariable(identifier, (Integer)value - 1);
        } else if (SavepointScope.evalType("double", value)) {
            this.currentScope.changeVariable(identifier, (Double)value - 1);
        }
        //this.currentScope.declareVariable(currentScope.getType(value), identifier, value);
        return null;
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
        this.arrayScopeStack.push(currentArrayScope);

        currentArrayScope = new ArrayScope(currentArrayScope);
        currentScope = new SavepointScope(currentScope);

        Object item = super.visitBlock(ctx);

        currentScope = scopeStack.pop();
        currentArrayScope = arrayScopeStack.pop();
        return item;
    }

    @Override
    public Object visitLoopW(SavepointParser.LoopWContext ctx) {
        boolean condition = (boolean)visit(ctx.expression());
        Object val = new Object();
        while(condition)
        {
            val = visit(ctx.block());
            if(val != null)
                break;
            condition = (boolean) visit(ctx.expression());
        }
        return val;
    }

    @Override
    public Object visitLoopF(SavepointParser.LoopFContext ctx) {
        scopeStack.push(currentScope);
        arrayScopeStack.push(currentArrayScope);

        currentScope = new SavepointScope(currentScope);
        currentArrayScope = new ArrayScope(currentArrayScope);

        try{visitStatement(ctx.statement());}
        catch(NullPointerException ignored){}
        boolean condition = (boolean)visit(ctx.expression());
        Object value = new Object();
        while(condition)
        {
            value = visit(ctx.block());
            if(value!=null)
                break;
            try{visitAssignment(ctx.assignment());}
            catch(NullPointerException ignored){}
            try{visitArrayElementDeclaration(ctx.arrayElementDeclaration());}
            catch(NullPointerException ignored){}
            try{visitIncrement(ctx.increment());}
            catch(NullPointerException ignored){}
            try{visitDecrement(ctx.decrement());}
            catch(NullPointerException ignored){}
            condition = (boolean) visit(ctx.expression());

        }
        currentScope = scopeStack.pop();
        currentArrayScope = arrayScopeStack.pop();
        return value;
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

    @Override
    public Object visitFunctionDeclaration(SavepointParser.FunctionDeclarationContext ctx) {
        String functionName=ctx.IDENTIFIER().getText();
        this.functions.put(functionName, ctx);
        return null;
    }

    @Override
    public Object visitFunctionCall(SavepointParser.FunctionCallContext ctx) {
        String functionName=ctx.IDENTIFIER().getText();
        SavepointParser.FunctionDeclarationContext function = this.functions.get(functionName);
        List<Object> arguments=new ArrayList<>();
        if(ctx.expressionList()!=null){
            for(var expr : ctx.expressionList().expression()){
                arguments.add(this.visit(expr));
            }
        }
        SavepointScope functionScope=new SavepointScope();
        if(function.paramList()!=null){
            for(int i=0; i<function.paramList().IDENTIFIER().size(); i++){
                String paramName=function.paramList().IDENTIFIER(i).getText();
                String type=function.paramList().TYPE(i).getText();
                functionScope.declareVariable(type, paramName, arguments.get(i));
            }
        }
        this.arrayScopeStack.push(currentArrayScope);
        this.scopeStack.push(currentScope);

        currentArrayScope = new ArrayScope();
        currentScope = functionScope;
        ReturnValue value= (ReturnValue)this.visitFunctionBody(function.functionBody());
        currentScope = scopeStack.pop();
        currentArrayScope = arrayScopeStack.pop();
        if(function.TYPE()==null){
            return null;
        }
        if(SavepointScope.evalType(function.TYPE().getText(), value.value())){
            return value.getValue() ;
        }
        else throw new SavepointWrongFunctionTypeException(function.TYPE().getText());
    }

    @Override
    public Object visitFunctionBody(SavepointParser.FunctionBodyContext ctx) {
        Object value=super.visitFunctionBody(ctx);
        if(value instanceof ReturnValue){
            return value;
        }
        return new ReturnValue(null);
    }
}
