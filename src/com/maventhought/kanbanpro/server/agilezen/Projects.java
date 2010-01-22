package com.maventhought.kanbanpro.server.agilezen;

import javax.xml.bind.annotation.XmlElement;

/**
 * Projects element to map to XML response
 * @author AmirB
 *
 */
public class Projects {

	@XmlElement(name="Items")
	private ProjectItem[] items;

	public ProjectItem []getItems() {
		return items;
	}
}
