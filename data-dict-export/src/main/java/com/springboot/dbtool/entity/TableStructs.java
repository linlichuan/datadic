package com.springboot.dbtool.entity;

import lombok.Data;

@Data
public class TableStructs {
    String tableSchema;
    String tableName;
    String columnName;
    String columnComment;
    String columnType;
    Boolean isKey;
}
