package com.antrun.logic;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

/**
 * Created by srattanakana on 5/29/2016 AD.
 */
public class PreromonesExporter {

    final String path;
    final HSSFWorkbook workbook;
    final HSSFSheet sheet;
    int rowIndex = 0;

    public PreromonesExporter(String path) throws Exception{
        this.path = path;
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet();
    }

    public void printLint(){
        rowIndex += 1;
        sheet.createRow(rowIndex);
    }

    public void printString(String str){
        rowIndex += 1;
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue(str);
    }

    public void printArray(double[][] data){
        for(int i = 0 ; i < data.length ; i++){
            rowIndex += 1;
            Row row = sheet.createRow(rowIndex);
            for(int j = 0 ; j < data[i].length ; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(data[i][j]);
            }
        }

    }

    public void dowrite() throws Exception{
        FileOutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
    }
}
