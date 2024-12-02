package io.github.mung.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import io.github.mung.utils.LanguageUtils;
import io.github.mung.utils.LogUtils;


public class PropertiesHelpers {

    private static Properties properties;
    private static String linkFile;
    private static FileInputStream inputFile;
    private static FileOutputStream outFile;

    private static String propFilesPathDefault = "src/test/resources/config/config.properties";

    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();

        files.add("src/test/resources/config/config.properties");
        files.add("src/test/resources/config/data.properties");

        try {
            properties = new Properties();

            for(String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelpers.getCurrentDir() + f;
                inputFile = new FileInputStream(linkFile);
                tempProp.load(inputFile);
                properties.putAll(tempProp);
            }

            inputFile.close();
            LogUtils.info("Loaded all properties files.");
            return properties;
        } catch (IOException ex) {
            LogUtils.info("Warning!!, Can not load all files.");
            return new Properties();
        }
    }


    public static Properties getProperties() {
        return  properties;
    }

    public static void setFile(String relPropertiesFilePath) {
        properties = new Properties();
        try {
            linkFile = SystemHelpers.getCurrentDir() + propFilesPathDefault;
            inputFile = new FileInputStream(linkFile);
            properties.load(inputFile);
            inputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDefaultFile() {
        properties = new Properties();
        try {
            linkFile = SystemHelpers.getCurrentDir() + propFilesPathDefault;
            inputFile = new FileInputStream(linkFile);
            properties.load(inputFile);
            inputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        String keyValue = null;
        try {
            if (inputFile == null && properties == null) {
                properties = new Properties();
                linkFile = SystemHelpers.getCurrentDir() + propFilesPathDefault;
                inputFile = new FileInputStream(linkFile);
                properties.load(inputFile);
                inputFile.close();
            }
            // Get value from file
            keyValue = properties.getProperty(key);
            return LanguageUtils.convertCharset_ISO_8859_1_To_UTF8(keyValue);
            //return keyValue;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return keyValue;
        }
    }

    public static void setValue(String key, String keyValue) {
        try {
            if (inputFile == null) {
                properties = new Properties();
                inputFile = new FileInputStream(SystemHelpers.getCurrentDir() + propFilesPathDefault);
                properties.load(inputFile);
                inputFile.close();
                outFile = new FileOutputStream(SystemHelpers.getCurrentDir() + propFilesPathDefault);
            }
            //Write to the same Prop file as the extracted file
            outFile = new FileOutputStream(linkFile);
            System.out.println(linkFile);
            properties.setProperty(key, keyValue);
            properties.store(outFile, "Set value to properties file success.");
            outFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
