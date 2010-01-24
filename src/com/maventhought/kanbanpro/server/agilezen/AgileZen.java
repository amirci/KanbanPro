package com.maventhought.kanbanpro.server.agilezen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.maventhought.kanbanpro.client.Project;
import com.maventhought.kanbanpro.client.Project2;

public class AgileZen {

	public Iterable<Project> getProjects() {

		String urlStr = "http://www.agilezen.com/api/v1/projects";

		List<Project> projects = new ArrayList<Project>();

		try {
            URL url = new URL(urlStr);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestProperty("X-Zen-ApiKey", "1e6a8c37496144f1ba42a7f86b6f693f");
            
            connection.connect();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            String response = "";
            
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
	                  
			JAXBContext jc = JAXBContext.newInstance(Projects.class);
			
			Unmarshaller unm = jc.createUnmarshaller();
			
			response = response.substring(3);
			
			// Skip first 3 bytes
			Projects projectList = (Projects) unm.unmarshal(new StringReader(response));
			
			for(Project project : projectList.getItem().getProjects()){
				projects.add(new Project2(project.getName()));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			projects.add(new ProjectImpl("Exception project " + e.getMessage()));
		}

		return projects;
	}
}
