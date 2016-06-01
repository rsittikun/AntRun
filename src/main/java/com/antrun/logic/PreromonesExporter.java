package com.antrun.logic;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by srattanakana on 5/29/2016 AD.
 */
public class PreromonesExporter {

    final static String SHEET_PATH = "Path";
    final static String SHEET_WIJ = "Wij";
    final static String SHEET_PHERO = "Pheromonse";


    final String path;
    final HSSFWorkbook workbook;
    //final HSSFSheet sheet;
    Map<String,Integer> rowIndexMap = new HashMap<String, Integer>();

    public PreromonesExporter(String path) throws Exception{
        this.path = path;
        workbook = new HSSFWorkbook();
        workbook.createSheet(SHEET_PATH);
        workbook.createSheet(SHEET_WIJ);
        workbook.createSheet(SHEET_PHERO);
        rowIndexMap.put(SHEET_PATH, -1);
        rowIndexMap.put(SHEET_WIJ, -1);
        rowIndexMap.put(SHEET_PHERO, -1);

        //sheet = workbook.createSheet();
    }

    public void printLint(String sheetName){
        int rowIndex = rowIndexMap.get(sheetName) + 1;
        rowIndexMap.put(sheetName, rowIndex);
        HSSFSheet currSheeet = workbook.getSheet(sheetName);
        currSheeet.createRow(rowIndex);
    }

    public void printString(String sheetName,String str){
        int rowIndex = rowIndexMap.get(sheetName) + 1;
        rowIndexMap.put(sheetName, rowIndex);
        HSSFSheet currSheeet = workbook.getSheet(sheetName);
        Row row = currSheeet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue(str);
    }

    public void printStrings(String sheetName,String... strs){
        int rowIndex = rowIndexMap.get(sheetName) + 1;
        rowIndexMap.put(sheetName, rowIndex);
        HSSFSheet currSheeet = workbook.getSheet(sheetName);
        Row row = currSheeet.createRow(rowIndex);

        for(int j = 0 ; j < strs.length ; j++){
            Cell cell = row.createCell(j);
            cell.setCellValue(strs[j]);
        }
    }

    public void printArray(String sheetName,double[][] data){
        for(int i = 0 ; i < data.length ; i++){
            int rowIndex = rowIndexMap.get(sheetName) + 1;
            rowIndexMap.put(sheetName, rowIndex);
            HSSFSheet currSheeet = workbook.getSheet(sheetName);
            Row row = currSheeet.createRow(rowIndex);
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
