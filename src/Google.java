import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Google {
	//test of how to use method
	public static void main(String[] args) throws Exception {
		ArrayList<Document> searchhits=getGoogleDocuments("cat");
		for(Document hit :searchhits){
			System.out.println(hit.getTitle());
			System.out.println(hit.getUrl());
		}
	}
	
	public static ArrayList<Document> getGoogleDocuments(String search) throws UnsupportedEncodingException, IOException{
		String google = "http://www.google.com/search?q=";
		String charset = "UTF-8";
		String userAgent = "Yahoo returns 2.0"; // Change this to your company's name and bot homepage!
		ArrayList<Document> searchhits= new ArrayList<Document>();
		Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select("li.g>h3>a");
		for (Element link : links) {
		    String title = link.text();
		    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
		    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

		    if (!url.startsWith("http")) {
		        continue; // Ads/news/etc.
		    }
		    searchhits.add(new Document(url,title));
		}
		
		return searchhits;
	}


}


