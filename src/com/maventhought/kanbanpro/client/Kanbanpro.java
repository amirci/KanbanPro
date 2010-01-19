package com.maventhought.kanbanpro.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kanbanpro implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Get Projects");
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		final VerticalPanel dialogVPanel = new VerticalPanel();

		dialogVPanel.addStyleName("projectsPanel");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("sendButtonContainer").add(sendButton);

		RootPanel.get("projectsContainer").add(dialogVPanel);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {

				String url = "http://www.agilezen.com/api/v1/projects";
				
				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

				builder.setHeader("X-Zen-ApiKey", "1e6a8c37496144f1ba42a7f86b6f693f");

				dialogVPanel.clear();

				try {
					Request request = builder.sendRequest(null,
							new RequestCallback() {
								public void onError(Request request,
										Throwable exception) {
									// Couldn't connect to server (could be
									// timeout, SOP violation, etc.)
									displayError("Could not connect");
								}

								public void onResponseReceived(Request request,
										Response response) {
									if (200 == response.getStatusCode()) {
										dialogVPanel.add(new Label(response
												.getText()));
									} else {
										// Handle the error. Can get the status
										// text from response.getStatusText()
										displayError("Status code obtained " + response.getStatusCode());
										displayError("Error with the status code ##->" + response.getStatusText() + "<-##");
									}
								}

							});
				} catch (Exception e) {
					// Couldn't connect to server
					displayError(e.getMessage());

				}
			}

			private void displayError(String error) {
				dialogVPanel.add(new Label(error));

			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
	}
}
