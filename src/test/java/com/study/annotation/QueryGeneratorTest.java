package com.study.annotation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryGeneratorTest {

    @Test
    public void testGetAll() {
        QueryGenerator queryGenerator = new QueryGenerator();
        String expectedSql = "SELECT person_id, person_name, salary FROM Persons;";

        String actualSql = queryGenerator.getAll(Person.class);

        assertEquals(expectedSql, actualSql);
    }
}