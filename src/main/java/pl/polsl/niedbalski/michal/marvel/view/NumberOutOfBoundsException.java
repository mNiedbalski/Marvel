
package pl.polsl.niedbalski.michal.marvel.view;

/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */

/**
 * Class representing a possible exception when user is able to input a custom number in console.
 */
public class NumberOutOfBoundsException extends Exception {
    
    public NumberOutOfBoundsException(String errorMessage){
        super(errorMessage);
    }
    
}
