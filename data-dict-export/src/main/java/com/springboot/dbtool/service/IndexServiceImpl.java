package com.springboot.dbtool.service;

import com.springboot.dbtool.annotation.DataSourceSwitch;
import com.springboot.dbtool.dao.TableStructsMapper;
import com.springboot.dbtool.entity.TableStructs;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service("indexService")
public class IndexServiceImpl implements IIndexService{
    @Autowired
    TableStructsMapper tableStructsMapper;

    @DataSourceSwitch
    @Override
    public void exportTableInfo(String dataSourceKey, String schema, HttpServletResponse response) throws Exception{
        List<TableStructs> tableInfos = tableStructsMapper.getTableInfo(schema);
        XWPFDocument document = new XWPFDocument();
        createDocument(tableInfos,document);
        OutputStream out = response.getOutputStream();
        document.write(out);
        document.close();
        out.close();
    }

    public void appendTableToDocument(XWPFDocument document,List<TableStructs> tableStructs){
        document.createParagraph().createRun().setText("表名：" + tableStructs.get(0).getTableName());
        XWPFTable table = document.createTable();
        //表格为20分之1磅为单位
        table.setWidthType(TableWidthType.DXA);
        table.setWidth(9978);
        //表格居中
        table.setTableAlignment(TableRowAlign.CENTER);
        XWPFTableRow headerRow = table.getRow(0);
        setCellSimpleText(headerRow.getCell(0),"列名");
        setCellSimpleText(headerRow.addNewTableCell(),"字段类型");
        setCellSimpleText(headerRow.addNewTableCell(),"注释");
        setCellSimpleText(headerRow.addNewTableCell(),"主键");
        for (int i = 0; i < tableStructs.size(); i++) {
            TableStructs info = tableStructs.get(i);
            XWPFTableRow row = table.createRow();
            setCellSimpleText(row.getCell(0),info.getColumnName());
            setCellSimpleText(row.getCell(1),info.getColumnType());
            setCellSimpleText(row.getCell(2),info.getColumnComment());
            setCellSimpleText(row.getCell(3),info.getIsKey() ? "是" : "否");
        }
        document.createParagraph().createRun().addBreak();
    }

    public void createDocument(List<TableStructs> tableStructs,XWPFDocument document){
        Map<String,List<TableStructs>> map = tableStructs.stream().collect(Collectors.groupingBy(TableStructs::getTableSchema));
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String schema = it.next();
            document.createParagraph().createRun().setText("数据库名：" + schema);
            List<TableStructs> tables = map.get(schema);
            Map<String,List<TableStructs>> tableMap = tables.stream().collect(Collectors.groupingBy(TableStructs::getTableName));
            Iterator<String> tableIt = tableMap.keySet().iterator();
            while (tableIt.hasNext()){
                String tableName = tableIt.next();
                appendTableToDocument(document,tableMap.get(tableName));
            }
        }
    }

    public void setCellSimpleText(XWPFTableCell cell,String text){
        //单元格宽度按百分比
        cell.setWidthType(TableWidthType.PCT);
        cell.setWidth("25%");
        //垂直居中
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        XWPFParagraph paragraph = cell.addParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
    }
}
