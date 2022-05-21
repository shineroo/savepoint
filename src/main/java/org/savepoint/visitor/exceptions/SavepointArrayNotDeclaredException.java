package org.savepoint.visitor.exceptions;

public class SavepointArrayNotDeclaredException extends SavepointException{
    public SavepointArrayNotDeclaredException(String msg)
    {
        super(String.format("Array '%s' is not declared.", msg));
    }
}
