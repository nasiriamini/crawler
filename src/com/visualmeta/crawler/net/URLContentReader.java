package com.visualmeta.crawler.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Utility class which fetches the content of an arbitrary input URL parameter
 */

public class URLContentReader {
  private static Pattern htmlContentPattern = Pattern.compile("[tT][eE][xX][tT]/[hH][tT][mM][lL]");
  
  /**
   * utility method to fetch content of an arbitrary url
   * @param url: The url whose content should be fetched
   */
  public static String fetchURLContent(String url) throws URLContentReaderException
  {
    String content="";
    URL urlObj=null;
    try{
      urlObj = new URL(url);
    }
    catch(MalformedURLException e){
      throw new URLContentReaderException(URLContentReaderException.MALFORMED_URL,e);
    }
    if(urlObj!=null){
      URLConnection con=null;
      try{
        con=urlObj.openConnection();
      }
      catch(IOException e){
        throw new URLContentReaderException(URLContentReaderException.NETWORK_FAILUR,e);
      }
      if(con!=null){
        String contentType=con.getContentType();
        if(contentType!=null){
          Matcher matcher=htmlContentPattern.matcher(contentType);
          if(!matcher.find()){
            throw new URLContentReaderException(URLContentReaderException.INAPPROPRIATE_CONTENT_TYPE);
          }
        }
        else{
          throw new URLContentReaderException(URLContentReaderException.UNKNOWN_CONTENT_TYPE);
        }
      }
      BufferedReader in=null;
      try{
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }
      catch(IOException e){
        throw new URLContentReaderException(URLContentReaderException.NETWORK_FAILUR,e);
      }
      if(in!=null){
        try{
          String inputLine;
          while ((inputLine = in.readLine()) != null)
            content+=inputLine;
        }
        catch(IOException e){
          throw new URLContentReaderException(URLContentReaderException.STREAM_READ_FAILUR,e);
        }
        finally{
          if(in!=null){
            try{
              in.close();
            }
            catch(IOException e){}
          }
        }
      }
    }
    return content;
  }
}
