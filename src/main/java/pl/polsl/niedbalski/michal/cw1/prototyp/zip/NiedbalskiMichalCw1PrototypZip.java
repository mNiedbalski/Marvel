package pl.polsl.niedbalski.michal.cw1.prototyp.zip;
import java.util.ArrayList;
import pl.polsl.kontroler.kontroler;
import pl.polsl.model.*;


/**
 *
 * @author Michal Niedbalski
 * @version 1.0
 */
public class NiedbalskiMichalCw1PrototypZip {
    
        public static void main(String[] args) {
        model model = new model();
        ArrayList<Superhero> database = new ArrayList();
        
        database = model.Test();
        System.out.print(model.FindWithMostSuperpowers(database).getBirthname() + "\n");
        model.MySort(database);
        model.LoadUniverses(database);
        ArrayList<Superhero> affiliated = model.DisplayAffiliation("marvel", database);

        kontroler myKontroler = new kontroler();
        myKontroler.Check(args);
        myKontroler.LoadData();
    }

}
