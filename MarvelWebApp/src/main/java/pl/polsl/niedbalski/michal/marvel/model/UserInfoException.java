package pl.polsl.niedbalski.michal.marvel.model;

/**
 * Class representing a possible exception when user is able to input a custom data into program.
 * @author Michał Niedbalski
 * @version 2.0
 */
public class UserInfoException extends Exception {

    public UserInfoException(String errorMessage) {
        super(errorMessage);
    }

}

