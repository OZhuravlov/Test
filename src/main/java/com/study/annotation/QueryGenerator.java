package com.study.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.StringJoiner;

// "SELECT person_id, person_name, salary FROM Persons;";
public class QueryGenerator {

    public String getAll(Class clazz) {

        String tableName = getTableName(clazz);
        // person_id, person_name, salary
        String columns = getColumns(clazz);
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        stringBuilder.append(columns);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    public String insert(Object value) {

        Class clazz = value.getClass();
        String tableName = getTableName(clazz);
        String columns = getColumns(clazz);
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(tableName);
        stringBuilder.append(" (");
        stringBuilder.append(columns);
        stringBuilder.append(" ) VALUES (");

        return stringBuilder.toString();
    }

    public String getById(Class clazz, Object id) {
        StringBuilder stringBuilder = new StringBuilder(getAll(clazz));
        stringBuilder.append(getWhereById(clazz, id));

        return stringBuilder.toString();
    }

    public String delete(Class clazz, Object id) {

        String tableName = getTableName(clazz);
        StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(getWhereById(clazz, id));

        return stringBuilder.toString();
    }

    private String getTableName(Class clazz) {
        Annotation classAnnotation = clazz.getAnnotation(Table.class);
        Table tableAnnotation = (Table) classAnnotation;

        if (tableAnnotation == null) {
            throw new IllegalArgumentException("@Table is not present");
        }
        String tableName = tableAnnotation.name().isEmpty() ?
                clazz.getName() : tableAnnotation.name();
        return tableName;
    }

    private String getColumns(Class clazz) {
        StringJoiner columns = new StringJoiner(", ");
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ?
                        field.getName() : columnAnnotation.name();
                columns.add(columnName);
            }
        }
        return columns.toString();
    }

    private String getIdColumn(Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            Id idAnnotation = field.getAnnotation(Id.class);
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null && idAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ?
                        field.getName() : columnAnnotation.name();
                return columnName;
            }
        }
        return null;
    }

    private String getWhereById(Class clazz, Object id){
        StringBuilder stringBuilder = new StringBuilder(" WHERE ");
        stringBuilder.append(getIdColumn(clazz));
        stringBuilder.append(" = ");
        stringBuilder.append(id);

        return stringBuilder.toString();
    }
}