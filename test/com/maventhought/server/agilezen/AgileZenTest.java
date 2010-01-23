package com.maventhought.server.agilezen;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.maventhought.kanbanpro.client.Project;
import com.maventhought.kanbanpro.server.agilezen.AgileZen;

public class AgileZenTest {

	private AgileZen sut;

	@Before
	public void Setup() {
		this.sut = new AgileZen();
	}
	
	@Test
	public void testGetProjects() throws Exception {

		Iterable<Project> projects = this.sut.getProjects();
		
		Iterator<Project> it = projects.iterator();
		
		assertTrue(it.hasNext());		
	}

}
