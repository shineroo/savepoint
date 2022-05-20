package org.savepoint.visitor;

public record ReturnValue(Object value) {

    public Object getValue() {
        return value;
    }
}
