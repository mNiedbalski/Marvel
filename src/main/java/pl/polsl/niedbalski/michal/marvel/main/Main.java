package pl.polsl.niedbalski.michal.marvel.main;
import pl.polsl.niedbalski.michal.marvel.controller.Controller;


/**
 * Main class of the program
 * @author Michał Niedbalski
 * @version 3.0
 */
public class Main {

    /**
     * Main method of the program.
     * The only passed parameter is the path to the .csv file.
     * File is located in:
     * "\Niedbalski.Michal.cw2.final\src\main\resources\Marvel Characters.csv"
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Controller myGui = new Controller(args);
                myGui.getUserInterface().createAndShowGUI(args);
            }
        });
    }
}
