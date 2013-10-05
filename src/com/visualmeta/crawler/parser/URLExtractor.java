package com.visualmeta.crawler.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Utility class which goes through the content of the page and extracts urls
 */

public class URLExtractor {
  //Anchors <a href="x"> or <a href='x'> or <a href=x >
  private static Pattern urlPattern_01 = Pattern.compile("<[aA][^>]*\\s[hH][rR][eE][fF]=\"([^#\"][^\"]*)\"[^>]*>");
  private static Pattern urlPattern_02 = Pattern.compile("<[aA][^>]*\\s[hH][rR][eE][fF]='([^'#][^']*)'[^>]*>");
  private static Pattern urlPattern_03 = Pattern.compile("<[aA][^>]*\\s[hH][rR][eE][fF]=([^\"'\\s#>][^\"'\\s>]*)(\\s[^>]*)?>");
  //iframe <iframe src="x"> or <iframe src='x'> or <iframe src=x >
  private static Pattern urlPattern_04 = Pattern.compile("<[iI]?[fF][rR][aA][mM][eE][^>]*\\s[sS][rR][cC]=\"([^\"#][^\"]*)\"[^>]*>");
  private static Pattern urlPattern_05 = Pattern.compile("<[iI]?[fF][rR][aA][mM][eE][^>]*\\s[sS][rR][cC]='([^'#][^']*)'[^>]*>");
  private static Pattern urlPattern_06 = Pattern.compile("<[iI]?[fF][rR][aA][mM][eE][^>]*\\s[sS][rR][cC]=([^\"'\\s#>][^\"'\\s>]*)(\\s[^>]*)?>");
  
  private static Pattern[] patterns={urlPattern_01,urlPattern_02,urlPattern_03,urlPattern_04,urlPattern_05,urlPattern_06};
  
  public URLExtractor() {
    super();
  }
  /**
   * Extracts url patterns from page content and returns a list of these urls
   * @param hyperTextContent: HTML content of the page feched
   */
  public Set<String> extractURLs(String hyperTextContent){
    Set<String> result=new HashSet<String>();
    if(hyperTextContent!=null && hyperTextContent.length()!=0){
      for(Pattern p:patterns){
        Matcher matcher=p.matcher(hyperTextContent);
        while(matcher.find()){
          String group=matcher.group(1);
          if(group!=null && group.length()!=0){
            result.add(group);
          }
        }
      }
    }
    return result;
  }
}
