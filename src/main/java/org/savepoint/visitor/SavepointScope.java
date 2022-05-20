package org.savepoint.visitor;

import org.antlr.v4.runtime.misc.Pair;
import org.savepoint.visitor.exceptions.SavepointVariableAlreadyDeclaredException;
import org.savepoint.visitor.exceptions.SavepointVariableIncorrectFormat;
import org.savepoint.visitor.exceptions.SavepointVariableNotDeclaredException;

import java.util.HashMap;
import java.util.Map;

public class SavepointScope {
    private final Map<String, Pair<Object, String>> symbols = new HashMap<>();
    private final SavepointScope parent;

    public SavepointScope(SavepointScope parent) {
        this.parent = parent;
    }
    public SavepointScope()
    {
        this.parent = null;
    }

    public void declareVariable(String type, String variableName, Object value)
    {
        if(isAlreadyDeclared(variableName))
            throw new SavepointVariableAlreadyDeclaredException(variableName);
        if(!evalType(type, value))
            throw new SavepointVariableIncorrectFormat(variableName);
        symbols.put(variableName, new Pair(value, type));
    }

    private boolean isAlreadyDeclared(String variableName){
        if(symbols.containsKey(variableName))
            return true;
        return parent != null && parent.isAlreadyDeclared(variableName);
    }

    public void changeVariable(String variableName, Object value)
    {
        if(!isAlreadyDeclared(variableName))
            throw new SavepointVariableNotDeclaredException(variableName);

        if(symbols.containsKey(variableName)) {
            String type = symbols.get(variableName).b;
            if (!evalType(type, value))
                throw new SavepointVariableIncorrectFormat(variableName);
            symbols.put(variableName, new Pair(value, type));
        }
        else {
            assert parent != null;
            parent.changeVariable(variableName, value);
        }
    }

    public Object resolveVariable(String variableName)
    {
        if(!isAlreadyDeclared(variableName))
            throw new SavepointVariableNotDeclaredException(variableName);
        if(symbols.containsKey(variableName))
            return symbols.get(variableName).a;
        else {
            assert parent != null;
            return parent.resolveVariable(variableName);
        }
    }

    public boolean evalType(String type, Object value) //TODO: change this to switch or even into a better idea entirely
    {
        if(value==null)
            return false;
        String thing = value.toString();
        switch (type) {
            case "int":
                try {
                    Integer.parseInt(thing);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            case "double":
                try {
                    Double.parseDouble(thing);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            case "bool":
                return thing.equals("true") || thing.equals("false");
            case "string":
                var thing2 = thing.toCharArray();
                return thing2[0] == '\"' && thing2[thing2.length - 1] == '\"';
            default:
                return false;
        }
    }


}
