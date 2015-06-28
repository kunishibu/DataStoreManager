package jp.co.dk.datastoremanager;

aspect MethodTrace {
	
	protected jp.co.dk.logger.Logger logger = jp.co.dk.logger.LoggerFactory.getLogger(this.getClass());
	
	pointcut methodTrace(): execution(* *.*(..));
	
	before(): methodTrace() {
	    logger.debug("entering:" + thisJoinPoint);
	}
	after(): methodTrace() {
	    logger.debug("exiting:" + thisJoinPoint);
	}
}