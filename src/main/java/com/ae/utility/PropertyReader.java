package com.ae.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyReader {
	private static final Logger LOGGER = LogManager.getLogger(PropertyReader.class);
	
	public static Properties readPropertyFile(String fileName)
	{
		Properties prop = new Properties();
		String filepath = System.getProperty("user.dir")+"/src/main/resources/ConfigFile/"+fileName;
				
		try (FileInputStream fis = new FileInputStream(filepath)) {
		    prop.load(fis);
		} catch (FileNotFoundException ex) {
			LOGGER.info( filepath+" not Found", ex);
		} catch (IOException ex) {
			LOGGER.info("IOException", ex);
		}
		return prop;
	}
	
	public static String readVariable(String fileName,String variableName)
	{
		String value= readPropertyFile(fileName).getProperty(variableName);
		if(value!=null){
			return value;
		}else
		{
			LOGGER.info( variableName+" variable does not present in "+fileName);
			throw new NullPointerException(variableName+" variable does not present in "+fileName);
		}
	}

}
