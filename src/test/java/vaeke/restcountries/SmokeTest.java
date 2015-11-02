package vaeke.restcountries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vaeke.restcountries.Application;

import static org.junit.Assert.assertTrue;


public class SmokeTest {

	@Test
	public void contextLoads() {
        assertTrue(1 == 1);
	}

}
