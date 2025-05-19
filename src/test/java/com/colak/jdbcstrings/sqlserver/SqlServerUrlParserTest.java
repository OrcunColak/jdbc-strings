package com.colak.jdbcstrings.sqlserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlServerUrlParserTest {

    @Test
    void test() {
        SqlServerUrlParser sqlServerUrlParser = new SqlServerUrlParser();
        sqlServerUrlParser.parseUrl("jdbc:sqlserver://localhost:1433;databaseName=foo;encrypt=false;"
                                    + "trustServerCertificate=true;currentSchema=dbo");

        assertEquals("localhost", sqlServerUrlParser.getHost());
        assertEquals("1433", sqlServerUrlParser.getPort());
        assertEquals("foo", sqlServerUrlParser.getDatabaseName());
        assertEquals("dbo", sqlServerUrlParser.getSchemaName());
    }

}