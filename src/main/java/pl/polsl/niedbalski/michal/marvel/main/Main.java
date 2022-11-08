package pl.polsl.niedbalski.michal.marvel.main;

import pl.polsl.niedbalski.michal.marvel.controller.Controller;


/**
 * @author Michał Niedbalski
 * @version 2.0
 */
public class Main {

    /**
     * Main method of the program.
     * The only passed parameter is the path to the .csv file.
     */
    public static void main(String[] args) { //TODO: GENERATE DOCUMENTATION
        Controller myController = new Controller(args);
        myController.prepareProgram(args);
        myController.mainPart();
    }
}
