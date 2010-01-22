package com.maventhought.kanbanpro.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getProjects(AsyncCallback<Iterable<Project>> callback);
}
