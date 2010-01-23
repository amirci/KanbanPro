package com.maventhought.kanbanpro.server.agilezen;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Item")
public class ProjectItem {

	@XmlElementWrapper
	@XmlElement(name="Projects")
	private Collection<ProjectImpl> projects;

	public Iterable<ProjectImpl> getProjects() {
		return projects;
	}
}
