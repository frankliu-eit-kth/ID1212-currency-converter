
package app.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts the bank application.
 * @author Liming Liu
 * @debug This class must be in the parent package of other classes, or the Spring framework will not find the conponents
 */
@SpringBootApplication
public class Main {
    /**
     * Starts the bank application.
     *
     * @param args There are no command line parameters.
     */
    public static void main(String[] args) {
    	//load beans
    	//start everything
    	//creates beans
        SpringApplication.run(Main.class);
    }
}
