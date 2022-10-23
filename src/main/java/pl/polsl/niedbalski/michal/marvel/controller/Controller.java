package pl.polsl.niedbalski.michal.marvel.controller;
import java.util.ArrayList;
import java.util.Scanner;
import pl.polsl.niedbalski.michal.marvel.model.LogicalOperations;
import pl.polsl.niedbalski.michal.marvel.model.Superhero;
import pl.polsl.niedbalski.michal.marvel.view.NumberOutOfBoundsException;
import pl.polsl.niedbalski.michal.marvel.view.UserInterface;

/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
public final class Controller {
    
    public Controller () {};
    public Controller(String[] _params){
        LogicalOperations model = new LogicalOperations();
        ArrayList<Superhero> database = new ArrayList();
        Check(_params);
        model.LoadFile(_params[0],database);
        Options(database);
    }
    public void MainPart(){
        UserInterface Interface = new UserInterface();
       // try {
        //Interface.Options();
       // }
       // catch (NumberOutOfBoundsException e){
       //     e.printStackTrace();
       // }
    }
    /**
     * Gets a string of parameters using command line.
     * If no parameters have been inputted, program asks for input in console.
     * After this process, name of the input file is displayed.
     *
     * @param _params command line parameters
     * @return fileName Name of the .csv file with database.
     */
    public String Check(String[] _params){
        String fileName = null;
        if (_params.length == 0){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the path to the .csv file: ");
        fileName = scanner.next();
        }
        else
            fileName = _params[0];
        return fileName;
    }
    public void Options(ArrayList<Superhero> _database){
        boolean quit = false;
        LogicalOperations logic = new LogicalOperations();
        logic.LoadUniverses(_database);
        UserInterface Interface = new UserInterface();
        while (!quit){
            System.out.println("Please select an option: \n1. View superhero with biggest amount of superpowers.\n2.Display heroes affiliated with chosen universe.\n3.Display the value of Pearson's correlation factor.\n4.Quit\n");
            try{
                switch (ChooseAnOption(4)){
                    case 1 -> {
                        Interface.DisplaySuperhero(logic.FindWithMostSuperpowers(_database));
                    }
                    case 2 -> {
                        Interface.PrintArrayList(logic.getUniverses());
                        ChooseAnOption(logic.getUniverses().size());
                        //Printing heroes' names
                    }
                    case 3 -> {
                        System.out.println("Pearson's correlation factor value between number of types of superhero and number of universes equals: " + logic.CalculatePearsonCorrelation(_database));
                    }
                    case 4 -> {
                        quit = true;
                    }
                    default -> {
                        //throw new NumberOutOfBounds("Invalid option! Please try again.\n");
                    }
                }
            }
            catch (NumberOutOfBoundsException e){
                
            }
        }
    }
    public int ChooseAnOption(int n) throws NumberOutOfBoundsException{
        int choice = 0;
        Scanner myInput = new Scanner(System.in);
        choice = myInput.nextInt();
        //Exception should be somewhere here when (choice < 0 || choice > n)
        return choice;
        
    }


}
