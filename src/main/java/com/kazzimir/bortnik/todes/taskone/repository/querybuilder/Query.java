package com.kazzimir.bortnik.todes.taskone.repository.querybuilder;

import java.util.Map;

public interface Query {
    String getQuery();

    Map<String, Object> getQueryParameters();
}
