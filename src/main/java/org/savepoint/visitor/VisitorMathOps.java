package org.savepoint.visitor;

public class VisitorMathOps {


    public static Object visitNumericAddOpExpression(SavepointParser.NumericAddOpExpressionContext ctx, Object val1, Object val2) {
        switch(SavepointScope.autism(val1, val2)){
            case "int" -> {
                return switch (ctx.numericAddOp().getText()) {
                    case "+" -> (Integer) val1 + (Integer) val2;
                    case "-" -> (Integer) val1 - (Integer) val2;
                    default -> null;
                };
            }
            case "double" -> {
                double first = Double.parseDouble(val1.toString());
                double second = Double.parseDouble(val2.toString());
                return switch (ctx.numericAddOp().getText()) {
                    case "+" -> first+second;
                    case "-" -> first-second;
                    default -> null;
                };
            }
            case "string" -> {
                if (ctx.numericAddOp().getText().equals("+"))
                    return val1 + val2.toString();
                return null;
            }
        }
        return null;
    }


    public static Object visitNumericMultiOpExpression(SavepointParser.NumericMultiOpExpressionContext ctx, Object val1, Object val2) {
        switch(SavepointScope.autism(val1, val2)) {
            case "int" -> {
                return switch (ctx.numericMultiOp().getText()) {
                    case "*" -> (Integer)val1*(Integer) val2;
                    case "/" -> (Integer)val1/(Integer) val2;
                    case "%" -> (Integer)val1%(Integer) val2;
                    default -> null;
                };
            }
            case "double" -> {
                double first = Double.parseDouble(val1.toString());
                double second = Double.parseDouble(val2.toString());
                return switch (ctx.numericMultiOp().getText()) {
                    case "*" -> first*second;
                    case "/" -> first/second;
                    case "%" -> first%second;
                    default -> null;
                };
            }
        }
        return null;
    }


    public static Object visitComparisonExpression(SavepointParser.ComparisonExpressionContext ctx, Object val1, Object val2) {
        if (SavepointScope.getType(val1).equals(SavepointScope.getType(val2))){  // maybe like this
            switch(SavepointScope.getType(val1)) {
                case "int" -> {
                    return switch (ctx.booleanCompareOp().getText()) {
                        case "==" -> val1.equals(val2);
                        case "!=" -> !val1.equals(val2);
                        case ">" -> ((Integer)val1) > ((Integer)val2);
                        case ">=" -> ((Integer)val1) >= ((Integer)val2);
                        case "<" -> ((Integer)val1) < ((Integer)val2);
                        case "<=" -> ((Integer)val1) <= ((Integer)val2);
                        default -> null;
                    };
                }
                case "double" -> {
                    double first = Double.parseDouble(val1.toString());
                    double second = Double.parseDouble(val2.toString());
                    return switch (ctx.booleanCompareOp().getText()) {
                        case "==" -> (first) == (second);
                        case "!=" -> (first)!=(second);
                        case ">" -> (first) > (second);
                        case ">=" -> (first) >= (second);
                        case "<" -> (first) < (second);
                        case "<=" -> (first) <= (second);
                        default -> null;
                    };
                }
            }
        }
        return false;
    }
}
