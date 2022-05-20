package org.savepoint.visitor.exceptions;

public class SavepointVariableAlreadyDeclaredException extends SavepointException{
    public SavepointVariableAlreadyDeclaredException(String variableName)
    {
        super(String.format("Variable '%s' is already declared.", variableName));
    }
}
