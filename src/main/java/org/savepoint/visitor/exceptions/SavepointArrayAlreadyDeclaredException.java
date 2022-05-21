package org.savepoint.visitor.exceptions;

public class SavepointArrayAlreadyDeclaredException extends SavepointException{
    public SavepointArrayAlreadyDeclaredException(String msg)
    {
        super(String.format("Array '%s' is already declared.", msg));
    }

}
