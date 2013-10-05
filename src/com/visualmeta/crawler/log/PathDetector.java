package com.visualmeta.crawler.log;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Utility class which detects default path for writing the log file according to OS
 */

public class PathDetector {
  private static String OS = null;
  /**
     * Returns the OS Name
     */ 
  public static String getOsName()
  {
    if(OS == null) { OS = System.getProperty("os.name"); }
    return OS;
  }
  /**
     * Determins if the os is windows
     */ 
  public static boolean isWindows()
  {
    return getOsName().startsWith("Windows");
  }
  /**
     * Generates the default path to save the result log file
     */ 
  public static String getDefaultPath(){
    if(isWindows()){
      return "c:\\";
    }
    else{//Assuming it's unix based
      return "/opt/";
    }
  }
}