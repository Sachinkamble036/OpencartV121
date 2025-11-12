package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {

//DataProvider 1

	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException {
		String path=".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testData
	    ExcelUtility xl = new ExcelUtility(path);
	    int totalRows = xl.getRowCount("Sheet1");
	    int totalCols = xl.getCellCount("Sheet1", 1);

	    List<Object[]> dataList = new ArrayList<>();

	    for (int i = 1; i <= totalRows; i++) {
	        String firstCell = xl.getCellData("Sheet1", i, 0);
	        if (firstCell == null || firstCell.trim().isEmpty()) {
	            continue; // skip blank rows
	        }

	        Object[] rowData = new Object[totalCols];
	        for (int j = 0; j < totalCols; j++) {
	            rowData[j] = xl.getCellData("Sheet1", i, j);
	        }
	        dataList.add(rowData);
	    }

	    return dataList.toArray(new Object[0][]);
	}

    
    //DataProvider 2
    
    //DataProvider 3
    
    //DataProvider 4

}
