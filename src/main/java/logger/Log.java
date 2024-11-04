package logger;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;


public class Log {

    /** The log. */
    static Logger LOG = LoggerFactory.getLogger("BookstoreLog");

    /**
     * Info.
     * Takes a message string as input and logs it
     * Generates a step in the Allure report for the logged information by using the @Step annotation with the {0} parameter.
     * This will display the message in the Allure report as a step with the name of the step being the value of the message parameter.
     * Synchronized - only one thread can access it at a time to ensure thread safety.
     * Logs the message to the TestNG report using the Reporter.log() method which can be used to include
     * information in TestNG reports for easier debugging and troubleshooting.
     * @param message the message
     */
    @Step("{0}")
    public static synchronized void info(String message) {
        LOG.info(message);
        Reporter.log(message);
    }

    /**
     * Error.
     * Takes a message string as input and logs it using a logger instance created earlier (in the static block) with the logging level set to ERROR.
     * Generates a formatted HTML output to be included in the TestNG report for easier debugging and troubleshooting.
     * The Reporter.log() method is used to log the HTML output to the TestNG report.
     * @param message the message
     */
    public static void error(String message) {
        LOG.error(message);
        Reporter.log("<p class=\"testOutput\" style=\"color:red font-size:1em\">"
                + message + "</p>");
    }

}