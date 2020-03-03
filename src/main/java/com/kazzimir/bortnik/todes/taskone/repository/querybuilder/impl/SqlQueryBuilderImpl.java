package com.kazzimir.bortnik.todes.taskone.repository.querybuilder.impl;

import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.AbstractQueryBuilder;
import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.Query;

import java.util.Map;

public class SqlQueryBuilderImpl implements Query {
    private Map<String, Object> parameter;
    private String query;

    private SqlQueryBuilderImpl(Map<String, Object> parameter, String query) {
        this.parameter = parameter;
        this.query = query;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public Map<String, Object> getQueryParameters() {
        return parameter;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder extends AbstractQueryBuilder {
        private Builder() {
        }

        @Override
        public Query build() {
            String query = buildQuery();
            return new SqlQueryBuilderImpl(getParameters(), query);
        }
    }
}
