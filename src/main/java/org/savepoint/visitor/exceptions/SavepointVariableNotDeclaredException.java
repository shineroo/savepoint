package org.savepoint.visitor.exceptions;

public class SavepointVariableNotDeclaredException extends SavepointException{
    public SavepointVariableNotDeclaredException(String variableName)
    {
        super(String.format("Variable '%s' is not declared.", variableName));
    }
}
