package com.matt;

import static org.junit.Assert.fail;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.matt.filters.XSSRequestWrapper;

public class TestXSSRequestWrapper extends SampleBaseTestCase {
	@Mock HttpServletRequest request = null;
	XSSRequestWrapper xssWrapper = null;
	
	@Before
	public void init(){
		xssWrapper = new XSSRequestWrapper(request);
	}
	
	   
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
