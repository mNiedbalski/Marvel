package pl.polsl.niedbalski.michal.marvel.view;

import pl.polsl.niedbalski.michal.marvel.controller.Controller;
import pl.polsl.niedbalski.michal.marvel.controller.DisplayingAffiliation;
import pl.polsl.niedbalski.michal.marvel.model.Superhero;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class handling all user input and output.
 * This class is representing view in MVC pattern.
 *
 * @author Michał Niedbalski
 * @version 3.0
 */
public class UserInterface {
    public UserInterface() {}

    /**
     * Method returning string containing all data about superhero with most superpowers.
     *
     * @param hero Hero whose parameters are parsed into string.
     */
    public String displaySuperhero(Superhero hero) {
        String allParamsOfSuperhero = "";
        allParamsOfSuperhero+="char: " + hero.getId() + "\n";
        allParamsOfSuperhero+="\ncharname: " + hero.getCharName() + "\n";
        allParamsOfSuperhero+="\nbirthname: " + hero.getBirthName() + "\n";
        allParamsOfSuperhero+="\ntypes:\n" + printArrayList(hero.getTypes());
        allParamsOfSuperhero+="\nuniverses:\n" + printArrayList(hero.getUniverses());
        allParamsOfSuperhero+="\nbirthplace: " + hero.getBirthplace() + "\n";
        allParamsOfSuperhero+="\nsuperpowers:\n" + printArrayList(hero.getSuperpowers());
        allParamsOfSuperhero+="\nreligions:\n" + printArrayList(hero.getReligions());
        allParamsOfSuperhero+="\ngender: " + hero.getGender() + "\n";
        allParamsOfSuperhero+="\noccupations:\n" + printArrayList(hero.getOccupations());
        allParamsOfSuperhero+="\nmember of:\n" + printArrayList(hero.getMemberof());
        return allParamsOfSuperhero;
    }
    /**
     * Method that parses ArrayList into String.
     *
     * @param array array to be parsed.
     */
    public String printArrayList(ArrayList<String> array) {
        String temp = "";
        if (!array.isEmpty()) {
            for (String element : array)
                if (!element.isBlank()) {
                    temp += element + ", \n";
                }
        }
        else{
            temp = "none";
        }
        return temp;
    }
    /**
     * Method returning pearSonCorrelationFactor calculated beforehand in model.
     * @param pearsonCorrelationFactor Value of Pearson's Correlation coefficient.
     */
    public String displayPearsonCorrelation(double pearsonCorrelationFactor) {
        return "" + pearsonCorrelationFactor;
    }

    /**
     * Method initializing and showing GUI to user.
     * Every user's input is handled by GUI.
     * @param passedParams Passed parameters to the program.
     */
    public void createAndShowGUI(String[] passedParams) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Marvel program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new Controller(passedParams);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method initializing tree that is used to show superheroes affiliated with specific universes.
     * @param superheroes Database of superheroes.
     * @param universes Set of unique universes.
     */
    public void createAndShowTree(ArrayList<Superhero> superheroes, Set<String> universes) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Browse universes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DisplayingAffiliation newContentPane = new DisplayingAffiliation(superheroes,universes);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }
}
