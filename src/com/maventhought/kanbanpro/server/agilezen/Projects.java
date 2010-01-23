package com.maventhought.kanbanpro.server.agilezen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Projects element to map to XML response
 * @author AmirB
 *
 */
@XmlRootElement(name="projects")
public class Projects {

	@XmlElement(name="items")
	private ProjectItem item;

	public ProjectItem getItem() {
		return item;
	}
}
