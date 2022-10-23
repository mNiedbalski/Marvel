
package pl.polsl.kontroler;
import java.util.Scanner;
import pl.polsl.model.*;

/**
 *
 * @author Michal Niedbalski
 * @version 1.0
 */
public class kontroler {
    
    /**
     * Gets a string of parameters using command line.
     * If no parameters have been inputted, program asks for input in console.
     * After this process, name of the input file is displayed (for the sake of exercise)
     *
     * @param args command line parameters
     */
    public void Check(String[] _params){
        if (_params.length == 0){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = scanner.next();
        System.out.println("Welcome " + name + "!");
        }
        else
        System.out.println("Welcome " + _params[0]);
    }
    public void LoadData(){
        
    }


}
