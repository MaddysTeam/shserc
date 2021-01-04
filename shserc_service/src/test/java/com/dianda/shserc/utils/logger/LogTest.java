package com.dianda.shserc.utils.logger;

import com.dianda.shserc.ServiceApplication;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ActiveProfiles("local")
@SpringBootTest(classes = ServiceApplication.class)
@RunWith( SpringRunner.class )
public class LogTest{

}
