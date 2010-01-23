package com.maventhought.kanbanpro.server.agilezen;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="items")
public class ProjectItem {

	
	@XmlElement(name="project")
	private Collection<ProjectImpl> projects;

	public Iterable<ProjectImpl> getProjects() {
		return projects;
	}
}
