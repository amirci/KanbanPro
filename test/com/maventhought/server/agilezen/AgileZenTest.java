package com.maventhought.server.agilezen;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.maventhought.kanbanpro.client.Project;
import com.maventhought.kanbanpro.server.agilezen.AgileZen;

public class AgileZenTest {

	private Iterable<Project> sut;

	@Before
	public void when_getting_the_projects() throws Exception {
		AgileZen az = new AgileZen();
		this.sut = az.getProjects();
	}
	
	/**
	 * Checks is not empty
	 */
	@Test
	public void should_not_be_empty() {
		Iterator<Project> it = this.sut.iterator();
		assertTrue(it.hasNext());		
	}
	
	/**
	 * Checks the expected projects
	 */
	@Test
	public void should_contain_projects() {
		Iterator<Project> iterator = this.sut.iterator();
		boolean found = false;
		while(iterator.hasNext() && !found) {
			Project project = iterator.next();
			found = project.getName().equals("Rails Media Library");
		}
		assertTrue("Does not contain rails project", found);
	}

}
