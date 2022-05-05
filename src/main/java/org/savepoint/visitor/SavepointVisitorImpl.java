package org.savepoint.visitor;

public class SavepointVisitorImpl extends SavepointBaseVisitor<Object> {
    //TODO implement
    @Override
    public Object visitPrintFunctionCall(SavepointParser.PrintFunctionCallContext ctx) {
        String text = visit(ctx.expression()).toString();
        System.out.println(text);
        return null;
    }

    @Override
    public Object visitConstantExpression(SavepointParser.ConstantExpressionContext ctx) {
        return visit(ctx.constant());
    }

    @Override
    public Object visitConstant(SavepointParser.ConstantContext ctx) {
        if (ctx.INTEGER() != null) {
            return Integer.parseInt(ctx.INTEGER().getText());
        }
        return null;
    }
}
