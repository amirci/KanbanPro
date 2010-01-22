package com.maventhought.kanbanpro.server.agilezen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Item")
public class ProjectItem {

	@XmlElement(name="Projects")
	private ProjectImpl[] projects;

	public ProjectImpl []getProjects() {
		return projects;
	}
}
