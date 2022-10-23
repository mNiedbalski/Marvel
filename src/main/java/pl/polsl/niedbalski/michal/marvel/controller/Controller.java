package pl.polsl.niedbalski.michal.marvel.controller;
import java.util.ArrayList;
import java.util.Scanner;
import pl.polsl.niedbalski.michal.marvel.model.LogicalOperations;
import pl.polsl.niedbalski.michal.marvel.model.Superhero;

/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
public final class Controller {
    
    public Controller(String[] _params){
        LogicalOperations model = new LogicalOperations();
        ArrayList<Superhero> database = new ArrayList();
        Check(_params);
        model.LoadFile(_params[0],database);
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
    public void LoadData(){
        
    }


}
