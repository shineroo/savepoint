package org.savepoint.visitor;

import org.antlr.v4.runtime.misc.Pair;
import org.savepoint.visitor.exceptions.*;
import org.savepoint.visitor.exceptions.SavepointArrayAlreadyDeclaredException;

import java.util.HashMap;
import java.util.Map;

public class ArrayScope{

    private final Map<String, Pair<Object[], String>> arrays = new HashMap<>();
    private final ArrayScope parent;


    public ArrayScope(ArrayScope parent) {
        this.parent = parent;
    }
    public ArrayScope()
    {
        this.parent = null;
    }

    private boolean isAlreadyDeclared(String variableName){
        if(arrays.containsKey(variableName))
            return true;
        return parent != null && parent.isAlreadyDeclared(variableName);
    }

    public void declareArray(String name, int length, String type)
    {
        if(isAlreadyDeclared(name))
            throw new SavepointArrayAlreadyDeclaredException(name);
        arrays.put(name, new Pair(new Object[length], type));
    }

    public Object[] resolveArray(String name)
    {
        if(!isAlreadyDeclared(name))
            throw new SavepointArrayNotDeclaredException(name);
        if(arrays.containsKey(name))
            return arrays.get(name).a;
        else {
            assert parent != null;
            return parent.resolveArray(name);
        }
    }

    public Object resolveArrayItem(String name, int index)
    {
        if(!isAlreadyDeclared(name))
            throw new SavepointArrayNotDeclaredException(name);
        if(arrays.containsKey(name))
            return arrays.get(name).a[index];
        else {
            assert parent != null;
            return parent.resolveArrayItem(name, index);
        }
    }

    public void changeElement(String name, int index, Object value)
    {
        if(!isAlreadyDeclared(name))
            throw new SavepointArrayNotDeclaredException(name);
        if(arrays.containsKey(name)) {
            if(!SavepointScope.evalType(arrays.get(name).b, value))
                throw new SavepointVariableIncorrectFormat(name);
            arrays.get(name).a[index] = value;
        }
        else {
            assert parent != null;
            parent.changeElement(name, index,  value);
        }
    }




}
