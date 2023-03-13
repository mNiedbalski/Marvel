package pl.polsl.niedbalski.michal.marvel.view;

import java.util.ArrayList;
import java.util.Set;

import pl.polsl.niedbalski.michal.marvel.model.Superhero;



/**
 * Class handling all user input and output.
 * This class is partly representing view in MVC pattern.
 *
 * @author Michał Niedbalski
 * @version 3.0
 */
public class UserInterface {
    public UserInterface() {}
    /**
     * Method displaying arraylist of superheroes
     *
     * @param superheroes ArrayList of superheroes to be displayed.
     */

    public void displaySuperheroes(ArrayList<Superhero> superheroes) {
        for (Superhero supe : superheroes) {
            displaySuperhero(supe);
            System.out.println("\n");
        }
    }

    /**
     * Method displaying superhero with most superpowers.
     *
     * @param hero Displayed superhero.
     * return parsed parameters into string in html style.
     */
    public String displaySuperhero(Superhero hero) {
        String allParamsOfSuperhero = "";
        allParamsOfSuperhero+="<b>char:</b> " + hero.getId() + "<br>";
        allParamsOfSuperhero+="<b>charname:</b> " + hero.getCharName() + "<br>";
        allParamsOfSuperhero+="<b>birthname:</b> " + hero.getBirthName() + "<br>";
        allParamsOfSuperhero+="<b>types:</b> <br>" + printArrayList(hero.getTypes());
        allParamsOfSuperhero+="<br><b>universes:</b> <br>" + printArrayList(hero.getUniverses());
        allParamsOfSuperhero+="<b>birthplace:</b> " + hero.getBirthplace() + "<br>";
        allParamsOfSuperhero+="<b>superpowers:</b> <br>" + printArrayList(hero.getSuperpowers());
        allParamsOfSuperhero+="<br><b>religions:</b> <br>" + printArrayList(hero.getReligions());
        allParamsOfSuperhero+="<br><b>gender:</b> " + hero.getGender() + "<br>";
        allParamsOfSuperhero+="<b>occupations:</b>" + printArrayList(hero.getOccupations());
        allParamsOfSuperhero+="<br><b>member of: </b><br>" + printArrayList(hero.getMemberof());
        
        return allParamsOfSuperhero;
    }

    /**
     * Method that prints inputted ArrayList.
     *
     * @param array method printing array.
     */
    public String printArrayList(ArrayList<String> array) {
        String temp = "";
        if (!array.isEmpty()) {
            for (String element : array)
                if (!element.isBlank()) {
                    temp += element + ", <br>";
                }
        }
        return temp;
    }

    /**
     * Method that prints inputted set.
     *
     * @param set printed set.
     * @return universes parsed into string.
     */
    public String printSet(Set<String> set) {
        int counter = 1;
        String universes="";
        for (String element : set) {
            universes+=counter + ". " + element + "<br>";
            counter++;
        }
        return universes;
    }

    /**
     * Method printing out pearSonCorrelationFactor calculated beforehand in model and passed using controller.
     * @param pearsonCorrelationFactor Value of Pearson's Correlation coefficient.
     */
    public String displayPearsonCorrelation(double pearsonCorrelationFactor) {
        return "" + pearsonCorrelationFactor;
    }
}
