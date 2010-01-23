package com.maventhought.kanbanpro.server.agilezen;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.maventhought.kanbanpro.client.Project;

public class AgileZen {

	public Iterable<Project> getProjects() throws Exception {

		String urlStr = "http://www.agilezen.com/api/v1/projects/";

		List<Project> projects = new ArrayList<Project>();

		try {
			HttpClient client = new HttpClient();

			HttpMethod method = new GetMethod(urlStr);

			method.setRequestHeader("X-Zen-ApiKey", "1e6a8c37496144f1ba42a7f86b6f693f");

			client.executeMethod(method);

			JAXBContext jc = JAXBContext.newInstance(Projects.class);
			
			Unmarshaller unm = jc.createUnmarshaller();
			
			String response = method.getResponseBodyAsString();
			
			// Skip first byte
			Projects projectList = (Projects) unm.unmarshal(new StringReader(response.substring(1)));
			
			for(Project project : projectList.getItem().getProjects()){
				projects.add(project);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}

		return projects;
	}
}
