package pl.polsl.niedbalski.michal.marvel.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

import org.apache.commons.lang3.ObjectUtils;
import pl.polsl.niedbalski.michal.marvel.model.LogicalOperations;
import pl.polsl.niedbalski.michal.marvel.model.UserInfoException;
import pl.polsl.niedbalski.michal.marvel.view.UserInterface;

/**
 * @author Michał Niedbalski
 * @version 2.0
 */
public final class Controller {
    private LogicalOperations logicalOperations;
    private UserInterface userInterface;

    /**
     * Constructor of controller. The purpouse of it is to initiate two private class fields.
     *
     * @param _params
     */
    public Controller(String[] _params) {
        logicalOperations = new LogicalOperations();
        userInterface = new UserInterface();
    }

    public LogicalOperations getLogicalOperations() {
        return this.logicalOperations;
    }

    /**
     * Method that prepares program in order to be ready to use.
     * In this method database is loaded from the file and also unique universes are imported into set structure.
     *
     * @param passedParams parameters passed to program (filePath)
     */
    public void prepareProgram(String[] passedParams) {
        boolean foundFile = false;
        while (!foundFile) {
            try {
                logicalOperations.prepareDatabase(passedParams);
                foundFile = true;
            } catch (FileNotFoundException e) {
                while (!foundFile) {
                    try {
                        userInterface.setFilename();
                        logicalOperations.checkIfFileExists();
                        foundFile = true;
                    } catch (NullPointerException exception) { //TODO: Do something with this blank space

                    }
                }
            } catch (IOException e) { //TODO: Do something with this blank space

            }
        }
    }

    /**
     * Main part of the application.
     * In this part user is shown options to
     * 1. Display superhero with most superpowers.
     * 2. Display heroes affiliated with chosen universe.
     * 3. Calculate Pearson's correlation factor value between number of types of superhero and number of universes.
     * 4. Quit
     */
    public void mainPart() {
        final int AMOUNT_OF_OPTIONS = 4;
        boolean quit = false;
        int choice = 0;
        while (!quit) {
            userInterface.displayMainOptions();
            try {
                choice = userInterface.chooseAnOption(AMOUNT_OF_OPTIONS);
                switch (choice) {
                    case 1 -> {
                        userInterface.displaySuperhero(logicalOperations.findWithMostSuperpowers());
                    }
                    case 2 -> {
                        userInterface.printSet(logicalOperations.getUniverses());
                        userInterface.choosingUniverseText();
                        userInterface.displaySuperheroes(logicalOperations.findSuperheroesFromUniverse(logicalOperations.chosenUniverse(userInterface.chooseAnOption(logicalOperations.getUniverses().size()))));
                    }
                    case 3 -> {
                        userInterface.displayPearsonCorrelation(logicalOperations.calculatePearsonCorrelation());
                    }
                    case 4 -> {
                        quit = true;
                    }
                    default -> {
                        logicalOperations.checkIfCorrect(AMOUNT_OF_OPTIONS, choice);
                    }
                }
            } catch (UserInfoException e) {
                userInterface.pausingApp();
            } catch (InputMismatchException e2) {
                userInterface.pausingApp();
            }
        }
    }
}
