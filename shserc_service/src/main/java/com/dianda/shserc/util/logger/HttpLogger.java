package com.dianda.shserc.util.logger;


import org.apache.log4j.LogManager;
import org.slf4j.Logger;

public class HttpLogger implements IServiceLogger {
	
	static Logger logger = ( Logger ) LogManager.getLogger ( LogAspect.class );
	
}
