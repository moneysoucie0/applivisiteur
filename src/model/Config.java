package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Config {
	Properties config;

	public Config() throws FileNotFoundException, IOException {
		config = new Properties();

		try {
			config.load(new FileInputStream(new File("config.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getProp(String prop) {
		return config.getProperty(prop).toString();
	}
}