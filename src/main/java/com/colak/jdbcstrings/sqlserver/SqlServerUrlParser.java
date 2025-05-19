package com.colak.jdbcstrings.sqlserver;

import java.util.HashMap;
import java.util.Map;

// Parses Sql Server connection parameters into components
public class SqlServerUrlParser {

    private static final String HOST_LITERAL = "host";
    private static final String PORT_LITERAL = "port";
    private static final String DATABASE_NAME_LITERAL = "databaseName";
    private static final String SCHEMA_NAME_LITERAL = "currentSchema";

    private final Map<String, String> parsedValues = new HashMap<>();

    public String getHost() {
        return parsedValues.get(HOST_LITERAL);
    }

    public String getPort() {
        return parsedValues.get(PORT_LITERAL);
    }

    public String getDatabaseName() {
        return parsedValues.get(DATABASE_NAME_LITERAL);
    }

    public String getSchemaName() {
        return parsedValues.get(SCHEMA_NAME_LITERAL);
    }

    public void parseUrl(String url) {
        // Check if the URL starts with the "jdbc:sqlserver://"
        String prefix = "jdbc:sqlserver://";
        if (!url.startsWith(prefix)) {
            throw new IllegalArgumentException(url + " is not a valid SQL Server URL");
        }

        // Remove the prefix
        url = url.substring(prefix.length());

        // Split the URL by semicolons to get the key-value pairs
        String[] parts = url.split(";");
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index];
            if (index == 0) {
                parseHostPort(part);
            } else {
                parseKeyValue(part);
            }
        }
    }

    private void parseHostPort(String part) {
        // Split host:port
        String[] hostPort = part.split(":");
        parsedValues.put(HOST_LITERAL, hostPort[0]);
        // Default to 1433 if no port
        int port = hostPort.length > 1 ? Integer.parseInt(hostPort[1]) : 1433;

        parsedValues.put(PORT_LITERAL, part);
    }

    private void parseKeyValue(String part) {
        String[] keyValue = part.split("=");
        if (keyValue.length == 2) {
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key) {
                case DATABASE_NAME_LITERAL -> parsedValues.put(DATABASE_NAME_LITERAL, value);
                case SCHEMA_NAME_LITERAL -> parsedValues.put(SCHEMA_NAME_LITERAL, value);
            }
        }
    }
}

