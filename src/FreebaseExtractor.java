import java.io.IOException;
import java.net.URLEncoder;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.*;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class FreebaseExtractor{
	
	public static void tryOne() throws JsonSyntaxException, ParseException, IOException {
		String url = "https://www.googleapis.com/freebase/v1/mqlread"    + "?query=" + URLEncoder.encode("[{\"/common/topic/description\":[{\"value\":null, \"lang\":\"/lang/en\"}],\"id\":null,\"name\":null,\"type\":\"/media_common/adaptation\"}]", "UTF-8")
	                                    + "&key=" + "AIzaSyD4H7khKUc8xhtYSmDinlDp8IC3UJjX48I";     
	       
	       
	    	 
	       HttpClient httpclient = new DefaultHttpClient();   
	       HttpResponse response = httpclient.execute(new HttpGet(url));  

	       JsonParser parser = new JsonParser();
	       JsonObject json_data = (JsonObject)parser.parse(EntityUtils.toString(response.getEntity()));
	       JsonArray results = (JsonArray)json_data.get("result");            
	       JsonArray cursor = (JsonArray)json_data.get("cursor");
	       
	       if(cursor != null)
	       {
	           for (Object r : cursor) 
	           {
	              System.out.println(((JsonObject)r).toString());//get("name"));
	           }
	       } 
	       
	       if(results != null)
	       {
	           for (Object r : results) 
	           {
	              System.out.println(((JsonObject)r).toString());//get("name"));
	           }
	       }        
	     
	  }
	
	public static void main(String[] args) throws JsonSyntaxException, ParseException, IOException  {
		tryOne();
		// TODO Auto-generated method stub
    	/*HttpTransport httpTransport = new NetHttpTransport();
    	com.google.api.client.http.HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
    	
    	 GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/mqlread");
    	 url.put("query", "[{\"id\":null,\"name\":null,\"type\":\"/media_common/adaptation\"}]");
    	//url.put("indent", "true");
    	// url.put("prop", "/media_common/adapted_work/name: Enemy Mine");
    	 url.put("cursor", "");
    	 url.put("key", "AIzaSyD4H7khKUc8xhtYSmDinlDp8IC3UJjX48I");
    	 System.out.println(url.toString());
         com.google.api.client.http.HttpRequest request = (com.google.api.client.http.HttpRequest) requestFactory.buildGetRequest(url);
         com.google.api.client.http.HttpResponse httpResponse = request.execute();
         System.out.println(httpResponse.parseAsString());
         
    	/*String query = "[{\"limit\": 100,\"name\":null,\"adaptations\":\"Enemy Mine\",\"type\":\"/media_common/adapted_work\"}]";
    	String query = "[{\"limit\": 100,\"type\":\"/media_common/adapted_work\"}]";
    	GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/mqlread");
    	url.put("key", "AIzaSyD4H7khKUc8xhtYSmDinlDp8IC3UJjX48I");
    	url.put("query", query);
    	com.google.api.client.http.HttpRequest request = requestFactory.buildGetRequest(url);
    	//com.google.api.client.http.HttpRequest request = requestFactory.buildGetRequest(
    		//	new GenericUrl("https://www.googleapis.com/freebase/v1/search?query=&indent=true&filter=(type:/media_common/adapted_work)&key=AIzaSyD4H7khKUc8xhtYSmDinlDp8IC3UJjX48I"));


    	com.google.api.client.http.HttpResponse httpResponse = request.execute();
    	System.out.println(httpResponse.parseAsString());
    	
    	//JSONObject r = (JSONObject);
    
    	/*JSONObject response = (JSONObject)parser.parse(httpResponse);
    	JSONArray results = (JSONArray)response.get("result");
    	for (Object result : results) {
    	  System.out.println(result.get("name").toString());
    	}*/
	}
    
    

}
