package com.visualmeta.crawler.net;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Utility class whose main functionality is to normalize fetched urls which includes:
 *  - Convert relative urls to absolute ones
 *  - Skip Urls begining which #,mailto:,javascript: and etc.
 *  - Skip duplicate urls
 */
public class URLUtility {
  private static Pattern ignorableUrlPatterns = Pattern.compile("(^javascript:)|(^mailto:)");
  
  /**
   * Utility method which generates absolute URL from a relative one according to a base URL
   * @param pageURL: Base url
   * @parat relativeURL: The relative URL
   */
  public static String getAbsoluteOfRelativeURL(String pageURL,String relativeURL){
    URL baseURL=null;
    try{
      baseURL=new URL(pageURL);
    }
    catch(MalformedURLException e){
      //normally no exception is thrown since this content of this url is already fetched and the url is a valid one
      e.printStackTrace();
    }
    URL newURL=null;
    try{
      newURL=new URL(baseURL,relativeURL);
    }
    catch(MalformedURLException e){
      e.printStackTrace();
    }
    if(newURL!=null){
      return newURL.toString();
    }
    else{
      return null;
    }
  }
  
  /**
   * Utility method which normalize all input urls according to their baseURL
   * @param pageURL: Base url
   * @parat urls: fetched urls
   */
  public static Set<String> normalizeURLs(String pageURL,Set<String> urls){
    Set<String> normalizedURLs=new HashSet<String>();
    if(urls!=null && !urls.isEmpty()){
      for(String url:urls){
        if(url!=null && url.length()!=0){
          Matcher matcher=ignorableUrlPatterns.matcher(url);
          if(matcher.find()){
            continue;
          }
          //Make relative urls absolute
          if(url.length()<8 || (!"http://".equalsIgnoreCase(url.substring(0, 7)) && !"https://".equalsIgnoreCase(url.substring(0, 8)))){
            url=getAbsoluteOfRelativeURL(pageURL,url);
          }
          //Removing local anchors from urls(including # sing and the tail)
          if(url!=null){
            int sharpIndex=url.indexOf("#");
            if(sharpIndex!=-1){
              url=url.substring(0,sharpIndex);
            }
          }
          if(url!=null && !normalizedURLs.contains(url)){
            normalizedURLs.add(url);
          }
        }
      }
    }
    return normalizedURLs;
  }
}
