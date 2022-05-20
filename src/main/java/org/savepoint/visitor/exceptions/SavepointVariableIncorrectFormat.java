package org.savepoint.visitor.exceptions;

public class SavepointVariableIncorrectFormat extends SavepointException{
    public SavepointVariableIncorrectFormat(String variableName)
    {
        super(String.format("Variable '%s' is in incorrect format.", variableName));
    }

}
