package com.maventhought.kanbanpro.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
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

	@UiTemplate("DockLayoutUiBinder.ui.xml")
	interface DockLayoutUiBinder extends
	                UiBinder<DockLayoutPanel, Kanbanpro> {
	}

	private static DockLayoutUiBinder uiBinder = GWT.create(DockLayoutUiBinder.class);	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		final Button sendButton = new Button("Get Projects");

		final VerticalPanel dialogVPanel = new VerticalPanel();

		TabLayoutPanel tabs = new TabLayoutPanel(3, Unit.EM);
		tabs.add(new HTML("First Tab Content"), "Features by User");
		tabs.add(new HTML("Second tab content"), "Board");
		tabs.add(new HTML("Third Tab Content"), "Total Time");
		
		HorizontalPanel header = new HorizontalPanel();
		header.add(new Label("Enter API Key"));
		header.add(sendButton);
		
		DockLayoutPanel dockLayout = new DockLayoutPanel(Unit.EM);
		dockLayout.addNorth(header, 5);
		dockLayout.addSouth(new HTML("This is the FOOTER"), 5);
		dockLayout.addEast(dialogVPanel, 10);
		dockLayout.add(tabs);

	    // Make sure we use the whole client area
	    Window.setMargin("0px");
	    
	    // Attach the LayoutPanel to the RootLayoutPanel. The latter will listen for
	    // resize events on the window to ensure that its children are informed of
	    // possible size changes.
	    DockLayoutPanel root = uiBinder.createAndBindUi(this);
	    
	    RootLayoutPanel.get().add(root);
	    
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
