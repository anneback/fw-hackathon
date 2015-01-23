public class Document{
	private String url;
	private String title;
	Document(String url, String title){
		this.url=url;
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public String getUrl(){
		return url;
	}
	
}
