package com.kazzimir.bortnik.todes.taskone.repository.querybuilder;

import java.util.HashMap;
import java.util.Map;

public abstract class QueryParameters {
    private int paramIndex;
    private Map<String, Object> parameters = new HashMap<>();

    public String setParameter(Object value) {
        String parameter = allocateParameter();
        parameters.put(parameter, value);
        return parameter;
    }

    protected Map<String, Object> getParameters() {
        return parameters;
    }

    private String allocateParameter() {
        return "param" + paramIndex++;
    }
}
