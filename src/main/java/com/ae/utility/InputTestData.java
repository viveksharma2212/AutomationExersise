package com.ae.utility;


import org.testng.annotations.DataProvider;

import com.ae.base.TestBase;

public class InputTestData extends TestBase{
	
	@DataProvider
	public Object[][] Signup()  {
		Object[][] data;
			data = readFromExcel(workingDir + "/src/main/resources/TestData/TestData.xlsx", "01SignUpData");
			return data;
	}
	

}
