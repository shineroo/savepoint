package org.savepoint.visitor;

import org.antlr.v4.runtime.misc.Pair;
import org.savepoint.visitor.exceptions.SavepointVariableAlreadyDeclaredException;
import org.savepoint.visitor.exceptions.SavepointVariableIncorrectFormat;
import org.savepoint.visitor.exceptions.SavepointVariableNotDeclaredException;

import java.util.HashMap;
import java.util.Map;
import org.json.*;


public class SavepointScope {
    private final Map<String, Pair<Object, Pair<String, Boolean>>> symbols = new HashMap<>();
    private final SavepointScope parent;

    private JSONObject vardata = new JSONObject();

    public SavepointScope(SavepointScope parent) {
        this.parent = parent;
    }
    public SavepointScope()
    {
        this.parent = null;
        //vardata = getJsonData();
    }


    public void declareVariable(String type, String variableName, Object value)
    {
        if(isAlreadyDeclared(variableName))
            throw new SavepointVariableAlreadyDeclaredException(variableName);
        if(!evalType(type, value))
            throw new SavepointVariableIncorrectFormat(variableName);
        symbols.put(variableName, new Pair(value, new Pair(type, false)));
    }

    private boolean isAlreadyDeclared(String variableName){
        if(symbols.containsKey(variableName))
            return true;
        return parent != null && parent.isAlreadyDeclared(variableName);
    }

    private JSONObject getJsonData()
    {
        String json = InOut.readFile("samples/vardata.json");
        assert json != null;
        return new JSONObject(json);
    }

    public void printJsonData()
    {
        for(var key : symbols.keySet()) {
            var values = symbols.get(key);
            if(values.b.b)
                putJsonData(key, values.b.a, values.a);
        }
        InOut.writeFile(vardata.toString(), "samples/vardata.json");
    }

    private void putJsonData(String name, String type, Object value)
    {
        var newVar = new JSONObject();
        newVar.put("type", type);
        newVar.put("value", value);
        vardata.put(name, newVar);
    }

    public void declareSPvariable(String type, String name, Object value)
    {
        if(isAlreadyDeclared(name))
            throw new SavepointVariableAlreadyDeclaredException(name);
        if(evalType(type, value))
            throw new SavepointVariableIncorrectFormat(name);
        JSONObject obj = getJsonData();
        try{
            var tempVar = obj.getJSONObject(name);
            symbols.put(name, new Pair(tempVar.get("value"), new Pair(tempVar.getString("type"), true)));
            putJsonData(name, tempVar.getString("type"), tempVar.get("value"));
        }
        catch(JSONException ex){
            symbols.put(name, new Pair(value, new Pair(type, true)));
            putJsonData(name, type, value);
        }
    }


    public void changeVariable(String variableName, Object value)
    {
        if(!isAlreadyDeclared(variableName))
            throw new SavepointVariableNotDeclaredException(variableName);

        if(symbols.containsKey(variableName)) {
            boolean special = symbols.get(variableName).b.b;
            String type = symbols.get(variableName).b.a;
            if (!evalType(type, value))
                throw new SavepointVariableIncorrectFormat(variableName);
            symbols.put(variableName, new Pair(value, new Pair(type, special)));
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

    public static boolean evalType(String type, Object value)
    {
        if(value == null)
            return false;
        String thing = String.valueOf(value);
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
                return  true;
                //return thing2[0] == '\"' && thing2[thing2.length - 1] == '\"';
            default:
                return false;
        }
    }
    public static String getType(Object value) {
        if (value.getClass() == Integer.class){
            return "int";
        }
        if (value.getClass() == String.class){
            return "string";
        }
        if (value.getClass() == Double.class){
            return "double";
        }
        if (value.getClass() == Boolean.class){
            return "bool";
        }
        return null;
    }

    public static String autism(Object value1, Object value2) {
        String val1type = getType(value1);
        String val2type = getType(value2);

        if (val1type.equals(val2type)){
            return val1type;
        }
        if ((val1type.equals("int") && val2type.equals("double"))
                || (val1type.equals("double") && val2type.equals("int")))
            return "double";
        return "";
    }

}
