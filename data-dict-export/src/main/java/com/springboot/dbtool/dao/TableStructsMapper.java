package com.springboot.dbtool.dao;

import com.springboot.dbtool.entity.TableStructs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TableStructsMapper {

    @Select({
            "<script>",
            "SELECT",
            "   TABLE_SCHEMA tableSchema,",
            "   TABLE_NAME tableName,",
            "   COLUMN_NAME columnName,",
            "   COLUMN_COMMENT columnComment,",
            "   COLUMN_TYPE columnType,",
            "   (CASE COLUMN_KEY WHEN '' THEN false ELSE true END ) AS isKey",
            "FROM",
            "   INFORMATION_SCHEMA.COLUMNS",
            "WHERE",
            "   TABLE_SCHEMA = #{schema}",
            "</script>"
    })
    public List<TableStructs> getTableInfo(@Param("schema")String schema);
}
