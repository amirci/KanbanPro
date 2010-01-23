package com.maventhought.kanbanpro.server.agilezen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.maventhought.kanbanpro.client.Project;

@XmlType(name="project")
public class ProjectImpl implements Project {

	@XmlElement
	private String name;

	@Override
	public String getName() {
		return name;
	}

}
