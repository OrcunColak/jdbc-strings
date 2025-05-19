package com.colak.jdbcstrings.sqlserver;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlServerUrlBuilderTest {

    @Test
    void build() {
        String jdbcUrl = new SqlServerUrlBuilder("localhost", "mydb").buildUrl();
        String expected = "jdbc:sqlserver://localhost;databaseName=mydb;encrypt=false;trustServerCertificate=true;";
        assertEquals(expected, jdbcUrl);
    }

    @Test
    void buildAdditionalParams() {
        String jdbcUrl = new SqlServerUrlBuilder("localhost", "mydb")
                .setEncrypt(true)
                .setTrustServerCertificate(false)
                .addParam("applicationName", "MyApp")
                .addParam("loginTimeout", 10)
                .buildUrl();
        String expected
                = "jdbc:sqlserver://localhost;databaseName=mydb;encrypt=true;trustServerCertificate=false;applicationName=MyApp;loginTimeout=10;";
        assertEquals(expected, jdbcUrl);
    }
}