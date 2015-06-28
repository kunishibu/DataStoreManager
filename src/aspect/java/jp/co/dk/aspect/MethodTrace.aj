package jp.co.dk.aspect;

public aspect MethodTrace {
	
	protected jp.co.dk.logger.Logger logger = jp.co.dk.logger.LoggerFactory.getLogger(this.getClass());
	
	pointcut methodTrace(): execution(* *.*(..));
	
	before(): methodTrace() {
	    logger.trace("METHOD[START]:" + thisJoinPoint);
	}
	after(): methodTrace() {
	    logger.trace("METHOD[ FIN ]:" + thisJoinPoint);
	}
}