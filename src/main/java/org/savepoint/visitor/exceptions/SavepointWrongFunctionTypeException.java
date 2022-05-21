package org.savepoint.visitor.exceptions;

public class SavepointWrongFunctionTypeException extends SavepointException{
    public SavepointWrongFunctionTypeException(String funcType) {
        super(String.format("Return value needs to match function type '%s'", funcType));
    }
}
