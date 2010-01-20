package com.maventhought.kanbanpro.server;

import com.maventhought.kanbanpro.client.GreetingService;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		
		String url = "http://www.agilezen.com/api/v1/projects/";
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

		builder.setHeader("X-Zen-ApiKey", "1e6a8c37496144f1ba42a7f86b6f693f");

		String result1 = "empty";

		try {
			
			RequestCallback callback = new RequestCallback() {
				
				public String Result;
				
				public void onError(Request request, Throwable exception) {
					// Couldn't connect to server (could be timeout, SOP violation, etc.)
					Result = "Could not connect";
				}

				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {
						Result = response.getText();
					} else {
						// Handle the error. Can get the status
						// text from response.getStatusText()
						Result = "Status code obtained " + response.getStatusCode();
						Result += "Error with the status code ##->" + response.getStatusText() + "<-##";
					}
				}
				
				public String toString() {
					return this.Result;
				}
			};
			
			Request request = builder.sendRequest(null,callback);
			
			result1 = callback.toString();
		} catch (Exception e) {
			// Couldn't connect to server
			result1 = e.getMessage();

		}
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent
				+ "<br><br>"+ result1;
	}
}
