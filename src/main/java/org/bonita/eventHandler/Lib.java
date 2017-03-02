package org.bonita.eventHandler;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Fabrice.R on 21/02/2015.
 */
public class Lib {

    private static final Logger logger = Logger.getLogger("org.bonita.eventHandler");

    /**
     *
     * @param message A message
     */
    static void message(String message){
        try {
            logger.info(message);
            System.out.println(message);
        }catch (Exception ex) {
            logger.severe("message - Error : " + ex);
        }
    }

    /**
     *
     * @param aThrowable An exception
     */
    public static void traceExeption(Throwable aThrowable){
        String methodeName = Thread.currentThread().getStackTrace()[2].getMethodName();
        message("Error ("+methodeName+") : "+ getStackTrace(aThrowable));
    }

    /**
     *
     * @param aThrowable An exception
     * @return A displayable trace
     */
    public static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    /**
     *
     * @return
     */
    public static Properties loadProperties() {
        try {
            //-Dproperties.path=C:\DATAS\myProperties.properties
            //content file exemple :
            //debug=true
            String properiesPath = System.getProperty("properties.path");
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream(properiesPath);

            try{
                properties.load(input);
                return properties;
            } finally{
                input.close();
            }
        } catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public static String getProperty(String name){
        try {
            Properties myProperties = loadProperties();

            String prop = myProperties.getProperty(name);

            return prop;
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }

    /**
     *
     * @return A message
     */
    public String sayHelloMessage(){
        try {
            return "HelloWorld";
        }catch (Exception e) {
            traceExeption(e);
            return null;
        }
    }
}