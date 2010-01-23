package com.maventhought.kanbanpro.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kanbanpro implements EntryPoint {
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

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

				dialogVPanel.clear();

				greetingService.getProjects(
						new AsyncCallback<Iterable<Project>>() {

							@Override
							public void onFailure(Throwable caught) {
								displayError(caught.getMessage());
							}

							@Override
							public void onSuccess(Iterable<Project> projects) {
								
								for(Project project: projects){
									dialogVPanel.add(new Label(project.getName()));
								}
								
							}
						});
				
				
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
