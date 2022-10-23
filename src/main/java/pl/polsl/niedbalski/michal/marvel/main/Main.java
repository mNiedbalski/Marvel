package pl.polsl.niedbalski.michal.marvel.main;
import pl.polsl.niedbalski.michal.marvel.controller.Controller;
import pl.polsl.niedbalski.michal.marvel.view.UserInterface;


/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
public class Main {
    
        public static void main(String[] args) {
            Controller myController = new Controller(args);
            UserInterface Interface = new UserInterface();
       // LogicalOperations model = new LogicalOperations();
       // ArrayList<Superhero> database = new ArrayList();
       /// model.LoadFile(args[0],database);
       // System.out.print(model.FindWithMostSuperpowers(database).getBirthname() + "\n");
       // model.MySort(database);
        //model.LoadUniverses(database);
       // ArrayList<Superhero> affiliated = model.DisplayAffiliation("marvel", database);
       // model.CalculatePearsonCorrelation(database);
        
       // Controller myKontroler = new Controller();
      //  myKontroler.Check(args);
      //  myKontroler.LoadData();
    }

}
