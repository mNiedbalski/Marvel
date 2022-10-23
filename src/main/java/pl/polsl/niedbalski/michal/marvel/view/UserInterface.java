package pl.polsl.niedbalski.michal.marvel.view;
import java.util.ArrayList;
import pl.polsl.niedbalski.michal.marvel.controller.Controller;
import pl.polsl.niedbalski.michal.marvel.model.Superhero;

/**
 *
 * @author @author Michał Niedbalski
 * @version 1.0
 */
public class UserInterface {
    /**
     * Method displaying the name of input file.
     * @param input name of the input file.
     */
    public void Display(String input){
         System.out.println("Name of the input file:" + input);
    }
    /**
     * Method displaying superhero with most superpowers
     * @param hero Displayed superhero
     */
    public void DisplaySuperhero(Superhero hero){
        System.out.println("char: " + hero.getId() + "\n");
        System.out.println("charname: " + hero.getCharname() + "\n");
        System.out.println("birthname: " + hero.getBirthname() + "\n");
        System.out.println("types: ");
        PrintArrayList(hero.getTypes());
        System.out.println("universes: ");
        PrintArrayList(hero.getUniverses());
        System.out.println("birthplace: " + hero.getBirthplace() + "\n");
        System.out.println("superpowers: ");
        PrintArrayList(hero.getSuperpowers());
        System.out.println("religions: ");
        PrintArrayList(hero.getReligions());
        System.out.println("gender: " + hero.getGender() + "\n");
        System.out.println("occupation: ");
        PrintArrayList(hero.getOccupations());
        System.out.println("memberof: ");
        PrintArrayList(hero.getMemberof());
        
    }
    public void PrintArrayList(ArrayList<String> array){
        if (!array.isEmpty()){
        for (String element: array)
            if (!element.isBlank()){
                System.out.println(element + ", ");
            }      
        }
    }
}
