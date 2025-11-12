package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path)
    {
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            return sheet.getLastRowNum();  // returns last row index (0-based)
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            return row.getLastCellNum();  // returns total number of cells in that row
        }
    }

    
    
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        String data = "";
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("❌ Sheet not found: " + sheetName);
                return "";
            }

            XSSFRow row = sheet.getRow(rownum);
            if (row == null) {
                // The requested row doesn’t exist
                return "";
            }

            XSSFCell cell = row.getCell(colnum);
            if (cell == null) {
                // The requested cell doesn’t exist
                return "";
            }

            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);  // safely read any cell type
        } catch (Exception e) {
            System.out.println("Error reading Excel cell: " + e.getMessage());
        }
        return data;
    }

    
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
    {
    	File xlfile=new File(path);
    	if(!xlfile.exists()) //if file note exists then create new file
    	{
    		workbook=new XSSFWorkbook();
    		fo=new FileOutputStream(path);
    		workbook.write(fo);	
    	}
    	
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	
    	if(workbook.getSheetIndex(sheetName)==-1) //If sheet not exists then create new Sheet
    		workbook.createSheet(sheetName);
    	sheet=workbook.getSheet(sheetName);
    	
    	if(sheet.getRow(rownum)==null) //If row not exists then create new Row
    		sheet.createRow(rownum);
    	row=sheet.getRow(rownum);
    	
    	cell=row.createCell(colnum);
    	cell.setCellValue(data);
    	fo=new FileOutputStream(path);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();	
    }
    
    public void fillGreenColour(String sheetName, int rownum, int colnum) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(sheetName);
    	
    	row=sheet.getRow(rownum);
    	cell=row.getCell(colnum);
    	
    	style=workbook.createCellStyle();
    	
    	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();
    }
    
    public void fillRedColour(String sheetName, int rownum, int colnum) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(sheetName);
    	
    	row=sheet.getRow(rownum);
    	cell=row.getCell(colnum);
    	
    	style=workbook.createCellStyle();
    	
    	style.setFillForegroundColor(IndexedColors.RED.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();
    }
    
  
}
