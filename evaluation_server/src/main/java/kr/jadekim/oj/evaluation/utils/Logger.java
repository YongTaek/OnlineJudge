package kr.jadekim.oj.evaluation.utils;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
public class Logger {

    public static final String LOGGER_NAME = "kr.jadekim.oj.evaluation";

    public static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LOGGER_NAME);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void error(Throwable error) {
        error(error.getMessage());
    }

    public static void error(String message) {
        logger.severe(message);
    }
}
