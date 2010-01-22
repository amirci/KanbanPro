package com.maventhought.kanbanpro.server.agilezen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.maventhought.kanbanpro.client.Project;

public class AgileZen {

	public Iterable<Project> getProjects() {

		String urlStr = "http://www.agilezen.com/api/v1/projects/";

		List<Project> projects = new ArrayList<Project>();

		try {
			HttpClient client = new HttpClient();

			HttpMethod method = new GetMethod(urlStr);

			method.setRequestHeader("X-Zen-ApiKey",
					"1e6a8c37496144f1ba42a7f86b6f693f");

			client.executeMethod(method);

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		return projects;
	}
}
