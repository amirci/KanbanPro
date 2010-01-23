package com.maventhought.kanbanpro.server.agilezen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.maventhought.kanbanpro.client.Project;

@XmlRootElement(name="Project")
public class ProjectImpl implements Project {

	@XmlElement
	private String name;

	@Override
	public String getName() {
		return name;
	}

}
