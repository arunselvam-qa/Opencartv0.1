package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="LoginData")
	public Object[][] getData() throws IOException
	{
		 String path = ".\\testData\\OpenCart_LoginDDT.xlsx";
		 ExcelUtility exU = new ExcelUtility(path);
		 int rowcount = exU.getRowCount("Sheet1");
		 int columncount = exU.getCellCount("Sheet1",1);
		 
		 String loginData[][] = new String[rowcount][columncount];
		 
		 for(int i = 1; i<=rowcount; i++)
		 {
			 for(int j = 0; j<columncount; j++)
			 {
				 loginData[i-1][j] = exU.getCellData("Sheet1", i, j);
			 }
		 }
		 return loginData;
	}

}
