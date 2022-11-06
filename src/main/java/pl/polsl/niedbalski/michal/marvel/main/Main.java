package pl.polsl.niedbalski.michal.marvel.main;
import pl.polsl.niedbalski.michal.marvel.controller.Controller;



/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
public class Main {
    //"E:\JWIiUM\Marvel Characters.csv"
    /**
     * Main method of the program.
     * The only passed parameter is the path to the .csv file.
     */
    public static void main(String[] args) {
        Controller myController = new Controller(args); //TODO: Pass fileName as parameter so the model loads the file
        myController.prepareProgram(args);
        myController.mainPart();
    }
}
