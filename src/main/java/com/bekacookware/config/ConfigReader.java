package com.bekacookware.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigReader {

        public static String propValueFromConfigFile(String propName) {
            Properties prop = new Properties();
            String propValue = "";
            try (FileInputStream input = new FileInputStream(new File("configuration\\config.property"))) {
                prop.load(input);
                propValue = prop.getProperty(propName);

            } catch (IOException ex) {
                System.out.println("File is missing");
                ex.getStackTrace();
            }
            return propValue;
        }

}





