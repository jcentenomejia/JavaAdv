package fr.epita.iam.iamcore2.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestLog4J2Usage {
	
	private static final Logger LOGGER = LogManager.getLogger(TestLog4J2Usage.class);
	  
	  @Test
	  public void testLog4J2()
	  {
	    LOGGER.info("this is an info message with parameter : {}", "parameter");
	  }

}
