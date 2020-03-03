package com.kazzimir.bortnik.todes.taskone.repository.querybuilder;

import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.utils.PredicateBasic;
import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.utils.PredicateBasicJoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class AbstractQueryBuilder extends QueryParameters implements QueryBuilder {
    protected List<String> tables;
    protected List<String> columns;
    protected List<String> joins;
    protected List<String> wheres;

    @Override
    public QueryBuilder select() {
        return this;
    }

    @Override
    public QueryBuilder select(String... columns) {
        if (this.columns == null) {
            List<String> strings = Arrays.asList(columns);
            this.columns = new ArrayList<>(strings);
        } else {
            this.columns.addAll(Arrays.asList(columns));
        }
        return this;
    }

    @Override
    public QueryBuilder from(Class aClass) {
        addForm(aClass.getSimpleName());
        return this;
    }

    @Override
    public QueryBuilder from(Class aClass, String prefix) {
        addForm(aClass.getSimpleName() + " " + prefix);
        return this;
    }

    @Override
    public QueryBuilder from(String aClass, String prefix) {
        addForm(aClass + " " + prefix);
        return this;
    }

    @Override
    public QueryBuilder join(PredicateBasicJoin predicateJoin) {
        String body = predicateJoin.body(this);
        if (joins == null) {
            joins = new ArrayList<>();
            joins.add(body);
        } else {
            joins.add(body);
        }
        return this;
    }

    @Override
    public QueryBuilder where(PredicateBasic predicateBasic) {
        if (wheres == null) {
            wheres = new ArrayList<>();
            wheres.add(predicateBasic.body(this));
        } else {
            wheres.add(predicateBasic.body(this));
        }
        return this;
    }

    protected String buildQuery() {
        StringBuilder query = new StringBuilder("select");
        if (Objects.nonNull(columns)) {
            appendList(query, columns, " ", ", ");
        } else {
            query.append(" * ");
        }

        if (Objects.nonNull(tables)) {
            appendList(query, tables, "from ", ", ");
        } else {
            throw new IllegalArgumentException();
        }
        if (Objects.nonNull(joins)) {
            appendList(query, joins, "join ", " join ");
        }
        if (Objects.nonNull(wheres)) {
            appendList(query, wheres, "where ", " and ");
        }
        return query.toString();
    }

    protected void appendList(StringBuilder query, List<?> list, String postfix, String sep) {
        Iterator<?> iterator = list.iterator();
        query.append(postfix);
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (iterator.hasNext()) {
                query.append(next).append(sep);
            } else {
                query.append(next).append(" ");
            }
        }
    }

    private void addForm(String data) {
        if (this.tables == null) {
            this.tables = new ArrayList<>();
            tables.add(data);
        } else {
            tables.add(data);
        }
    }
}
