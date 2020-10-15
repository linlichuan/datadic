package com.llc.springcloud.apiservice.service.impl;

import com.llc.springcloud.apiservice.annotation.DatabaseParam;
import com.llc.springcloud.apiservice.dto.TableConnectMsgDto;
import com.llc.springcloud.apiservice.enums.DatabaseParamEnum;
import com.llc.springcloud.apiservice.entity.TableStructs;
import com.llc.springcloud.apiservice.annotation.DataSourceSwitch;
import com.llc.springcloud.apiservice.dao.TableStructsMapper;
import com.llc.springcloud.apiservice.service.IIndexService;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service("indexService")
public class IIndexServiceImpl implements IIndexService {

    static Logger log = LoggerFactory.getLogger(IIndexServiceImpl.class);
    
    final static String INFORMATION_SCHEMA = "information_schema";

    @Autowired
    TableStructsMapper tableStructsMapper;

    @DataSourceSwitch
    @Override
    public void exportTableInfo(@DatabaseParam(type = DatabaseParamEnum.DATA_SOURCE) String dataSourceKey,
                                String schema, HttpServletResponse response) throws Exception {
        List<TableStructs> tableInfos = tableStructsMapper.getTableInfo(schema, null);
        XWPFDocument document = new XWPFDocument();
        createDocument(tableInfos,document);
        OutputStream out = response.getOutputStream();
        document.write(out);
        document.close();
        out.close();
    }

    public void appendTableToDocument(XWPFDocument document, List<TableStructs> tableStructs){
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
            setCellSimpleText(row.getCell(3),info.getKey() ? "是" : "否");
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
    
    @Override
    public List<TableStructs> getTableInformation(TableConnectMsgDto dto) {
        StringBuilder url = new StringBuilder(dto.getUrl());
        int slashIdx = url.lastIndexOf("/");
        if (slashIdx == url.length() - 1) {
            url.append(INFORMATION_SCHEMA);
        } else {
            url.append("/").append(INFORMATION_SCHEMA);
        }
        
        return doGetTableInformation(url.toString(), dto.getUserName(), dto.getPassword(), dto.getDatabase(), dto.getTableName());
    }
    
    @DataSourceSwitch
    public List<TableStructs> doGetTableInformation(@DatabaseParam(type = DatabaseParamEnum.URL) String url,
                                                    @DatabaseParam(type = DatabaseParamEnum.USER_NAME) String username,
                                                    @DatabaseParam(type = DatabaseParamEnum.PASSWORD) String password,
                                                    String database,
                                                    String tableName) {
        return tableStructsMapper.getTableInfo(database, tableName);
    }
}
