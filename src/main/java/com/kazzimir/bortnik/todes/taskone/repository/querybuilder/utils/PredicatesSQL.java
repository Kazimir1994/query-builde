package com.kazzimir.bortnik.todes.taskone.repository.querybuilder.utils;

public final class PredicatesSQL {
    private PredicatesSQL() {
    }

    public static PredicateBasic equal(final String exp, final Object value) {
        return queryParameters -> {
            String parameter = queryParameters.setParameter(value);
            return String.format("%s =:%s", exp, parameter);
        };
    }

    public static PredicateBasic like(final String exp, final Object value) {
        return queryParameters -> {
            String parameter = queryParameters.setParameter(value);
            return String.format("%s like :%s", exp, parameter);
        };
    }

    public static PredicateBasicJoin join(final Class aClass, final String prefix, final String fieldPrefix,
                                          final String filedData) {
        return queryParameters ->
                String.format("%s %s on %s.%s=%s", aClass.getSimpleName(), prefix, prefix, fieldPrefix, filedData);
    }
}