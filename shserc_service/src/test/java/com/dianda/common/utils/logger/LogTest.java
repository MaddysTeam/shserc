package com.dianda.common.utils.logger;

import com.dianda.ServiceApplication;
import com.dianda.common.util.logger.system.SystemLog;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

//@ActiveProfiles("local")
@SpringBootTest(classes = ServiceApplication.class)
@RunWith( SpringRunner.class )
@EnableAspectJAutoProxy()
public class LogTest{


	@Test
	@SystemLog()
	public void Log(){
		Assert.isTrue(false);
	}

}
