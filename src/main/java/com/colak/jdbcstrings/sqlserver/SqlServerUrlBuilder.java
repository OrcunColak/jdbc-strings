package com.colak.jdbcstrings.sqlserver;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class SqlServerUrlBuilder {

    private final String server;
    private final String database;

    private boolean encrypt = false;
    private boolean trustServerCertificate = true;

    private final Map<String, Object> additionalParams = new LinkedHashMap<>();

    public SqlServerUrlBuilder addParam(String key, Object value) {
        this.additionalParams.put(key, value);
        return this;
    }

    public String buildUrl() {
        StringBuilder url = new StringBuilder("jdbc:sqlserver://")
                .append(server).append(";")
                .append("databaseName=").append(database).append(";")
                .append("encrypt=").append(encrypt).append(";")
                .append("trustServerCertificate=").append(trustServerCertificate).append(";");

        StringJoiner joiner = new StringJoiner(";");
        additionalParams.forEach((key, value) -> joiner.add(key + "=" + value));

        if (!additionalParams.isEmpty()) {
            url.append(joiner).append(";");
        }

        return url.toString();
    }
}
