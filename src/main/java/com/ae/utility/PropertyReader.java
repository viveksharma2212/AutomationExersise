package com.ae.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.*;

public class PropertyReader {
	private static final Logger LOGGER = Logger.getLogger(PropertyReader.class.getName());
	
	public static Properties readPropertyFile(String fileName)
	{
		Properties prop = new Properties();
		String filepath = System.getProperty("user.dir")+"/src/main/resources/ConfigFile/"+fileName;
				
		try (FileInputStream fis = new FileInputStream(filepath)) {
		    prop.load(fis);
		} catch (FileNotFoundException ex) {
			LOGGER.log(Level.SEVERE, filepath+" not Found", ex);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE,"IOException", ex);
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
			LOGGER.log(Level.SEVERE, variableName+" variable does not present in "+fileName);
			throw new NullPointerException(variableName+" variable does not present in "+fileName);
		}
	}

}
