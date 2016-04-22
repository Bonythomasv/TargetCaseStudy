package com.tgt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tgt.app.TargetCaseStudyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TargetCaseStudyApplication.class)
@WebAppConfiguration
public class TargetCaseStudyApplicationTests {

	@Test
	public void contextLoads() {
	}

}
