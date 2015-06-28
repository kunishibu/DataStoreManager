package jp.co.dk.aspect;

public aspect MethodTrace {
	
	protected jp.co.dk.logger.Logger logger = jp.co.dk.logger.LoggerFactory.getLogger(this.getClass());
	
	pointcut methodTrace(): execution(* *.*(..));
	
	before(): methodTrace() {
	    logger.trace("entering:" + thisJoinPoint);
	}
	after(): methodTrace() {
	    logger.trace("exiting:" + thisJoinPoint);
	}
}