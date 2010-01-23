package com.maventhought.kanbanpro.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.maventhought.kanbanpro.client.GreetingService;
import com.maventhought.kanbanpro.client.Project;
import com.maventhought.kanbanpro.server.agilezen.AgileZen;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public Iterable<Project> getProjects() throws Exception {
		return new AgileZen().getProjects();
	}
}
