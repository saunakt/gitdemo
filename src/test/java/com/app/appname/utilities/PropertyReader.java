
package com.app.appname.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

public class PropertyReader {
	Properties properties = new Properties();
	InputStream inputStream = null;
	
	static Logger logger = Logger.getLogger(PropertyReader.class);
	
	/**
	**********************************************************************
	* PropertyReader (Default Constructor) Calls the loadProperties method
	***********************************************************************
	*/
	public PropertyReader() {
		loadProperties();
	}

	/**
	**********************************************************************
	* PropertyReader (Overload Constructor) Calls the loadProperties method
	* @param propetiesFilePath This is path of properties file in String format
	***********************************************************************
	*/
	public PropertyReader(String propetiesFilePath) {
		loadProperties(propetiesFilePath);
	}
	
	/**
	**********************************************************************
	Initialize and load the config.properties file
	***********************************************************************
	*/
	private void loadProperties() {
		try {
			inputStream = new FileInputStream("./src/test/resources/ConfigFiles/config.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("[PropertyReader : loadProperties] Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/**
	**********************************************************************
	* Initialize and load the config.properties file
	* @param propetiesFilePath This is path of properties file in String format
	***********************************************************************
	*/
	private void loadProperties(String propetiesFilePath) {
		try {
			inputStream = new FileInputStream(propetiesFilePath);
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("[PropertyReader : loadProperties] Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/**
	**************************************************************************************
	* Return value for the key mentioned in config.properties file
	* @param key This is name of Key in String format.
	* @return KeyValue	This is Key value is String format.
	***************************************************************************************
	*/
	public String readProperty(String key) {
		return properties.getProperty(key);
	}
}
