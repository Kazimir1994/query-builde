package com.kazzimir.bortnik.todes.taskone.repository.querybuilder;

import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.utils.PredicateBasic;
import com.kazzimir.bortnik.todes.taskone.repository.querybuilder.utils.PredicateBasicJoin;

public interface QueryBuilder {
    QueryBuilder select();

    QueryBuilder select(String... strings);

    QueryBuilder from(Class aClass);

    QueryBuilder from(Class aClass, String prefix);

    QueryBuilder from(String aClass, String prefix);

    QueryBuilder join(PredicateBasicJoin predicateJoin);

    QueryBuilder where(PredicateBasic predicateBasic);

    Query build();
}
