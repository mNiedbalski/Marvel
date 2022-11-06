package pl.polsl.niedbalski.michal.marvel.view;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import pl.polsl.niedbalski.michal.marvel.model.Superhero;

/**
 *
 * Class representing user interface in program.
 * @author @author Michał Niedbalski
 * @version 2.0
 */
public class UserInterface {
    public UserInterface() {};
    /**
     * Method displaying the name of input file.
     * @param input name of the input file.
     */

    public void display(String input){
         System.out.println("Full path of the input file:" + input);
    }
    /**
     * Method displaying arraylist of superheroes
     * @param superheroes
     */
    public void displaySuperheroes(ArrayList<Superhero> superheroes){
        for (Superhero supe: superheroes){
            displaySuperhero(supe);
            System.out.println("\n");
        }
    }
    /**
     * Method displaying superhero with most superpowers
     * @param hero Displayed superhero
     */
    public void displaySuperhero(Superhero hero){
        System.out.println("char: " + hero.getId() + "\n");
        System.out.println("charname: " + hero.getCharname() + "\n");
        System.out.println("birthname: " + hero.getBirthname() + "\n");
        System.out.println("types: ");
        printArrayList(hero.getTypes());
        System.out.println("universes: ");
        printArrayList(hero.getUniverses());
        System.out.println("birthplace: " + hero.getBirthplace() + "\n");
        System.out.println("superpowers: ");
        printArrayList(hero.getSuperpowers());
        System.out.println("religions: ");
        printArrayList(hero.getReligions());
        System.out.println("gender: " + hero.getGender() + "\n");
        System.out.println("occupation: ");
        printArrayList(hero.getOccupations());
        System.out.println("memberof: ");
        printArrayList(hero.getMemberof());
        
    }

    /**
     * Method that prints inputted ArrayList
     * @param array
     */
    public void printArrayList(ArrayList<String> array){
        if (!array.isEmpty()){
        for (String element: array)
            if (!element.isBlank()){
                System.out.println(element + ", ");
            }      
        }
    }

    /**
     * Method that prints inputted set
     * @param set
     */
    public void printSet(Set<String> set){
        int counter = 1;
        for (String element: set){
            System.out.println(counter + ". " + element);
            counter++;
        }
    }
    /**
     * If no parameters have been inputted, program asks for input in console.
     * After this process, name of the input file is displayed.
     *
     * @return fileName Name of the .csv file with database.
     */
    public String setFilename() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the path to the .csv file (with quotation mark at beginning and end): ");
        String fileName = scanner.next();
        return fileName;
    }

    /**
     * Method that displays options available to user and proceeds further depending on given options.
     */
    public void displayMainOptions(){
        System.out.println("Please select an option: \n1. View superhero with biggest amount of superpowers.\n2.Display heroes affiliated with chosen universe.\n3.Display the value of Pearson's correlation factor.\n4.Quit\n");
    }

    /**
     *
     * @param n Index of chosen option
     * @return
     */
    public int chooseAnOption(int n) throws InputMismatchException {
        Scanner myInput = new Scanner(System.in);
        int choice = myInput.nextInt();
        return choice;
    }
    public void pausingApp() {
        System.out.println("Some inputted data is wrong... \nPress Any Key To Continue The Application...");
        new Scanner(System.in).nextLine();
    }
    public void choosingUniverseText(){
        System.out.println("Please input the number of chosen universe and accept with ENTER: ");
    }
    public void displayPearsonCorrelation(double pearsonCorrelationFactor){
        System.out.println("Pearson's correlation factor value between number of types of superhero and number of universes equals: " + pearsonCorrelationFactor);
    }
}
