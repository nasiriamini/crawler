CRAWLER Version 1.0


Description

The Crawler is capable of performing a crawling procedure starting with a seed URL and gathers up a list of urls found on the way. At the end, when reached the intended number of URLs, it will stop crawling and writes the result list into a log file.
The Crawler can follow to strategies for crawling which are BFS (Breadth-First) and DFS  (Depth-first) which is assignable upon execution.

Note: Crawler only crawles through http and https protocoles and searchs Urls mentioned in link (<a>) and frame (frames or iframes) tags.

HOW TO RUN:

You can simply run the jar file to get the instruction on how to and in what order to send the input arguments:

java -jar crawler.jar

You shall assign the only mandatory argument which is the seed url to start the crawling with as shown below:

java -jar crawler.jar http://www.visual-meta.com

You shall assign the number of URLs you wish the crawler find as the second argument (The default value is 1000):

java -jar crawler.jar http://www.visual-meta.com 1500

Crawling strategy can also be set as the third argument (The default value is BFS):

java -jar crawler.jar http://www.visual-meta.com 1500 DFS

For the fourth argument you can also assign the path in which the result log is written (c:\ for windows based platforms and /opt for Unix based platforms as the dafault of this argument).

java -jar crawler.jar http://www.visual-meta.com 1500 DFS d:\

Note: Your user must have writing privilege to the defined path. Windows users are highly recommended to run 'cmd' as administrator.
