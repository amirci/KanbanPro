package com.maventhought.kanbanpro.server.agilezen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.maventhought.kanbanpro.client.Project;

@XmlType(name="project")
public class ProjectImpl implements Project, IsSerializable {

	@XmlElement
	private String name;

	public ProjectImpl() {}
	
	public ProjectImpl(String newName) {
		this.name = newName;
	}

	@Override
	public String getName() {
		return name;
	}

}
