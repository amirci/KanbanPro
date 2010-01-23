package com.maventhought.kanbanpro.server.agilezen;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Projects element to map to XML response
 * @author AmirB
 *
 */
@XmlRootElement(name="Projects")
public class Projects {

	@XmlElementWrapper
	@XmlElement(name="Items")
	private Collection<ProjectItem> items;

	public Iterable<ProjectItem> getItems() {
		return items;
	}
}
