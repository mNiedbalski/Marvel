
package pl.polsl.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;


/**
 *
 * @author Michal Niedbalski
 * @version 1.0
 */
public class model {

    private ArrayList<String> universes = new ArrayList();
        
    public void model () {
       // this.universes = new ArrayList();
    }
    public void LoadUniverses (ArrayList<Superhero> _database) {
        String tempUniverse = _database.get(0).getUniverses().get(0);
        this.universes.add(tempUniverse);
        for (Superhero checkedSuperhero: _database){
            for (String checkedUniverse: checkedSuperhero.getUniverses() ){
                tempUniverse = checkedUniverse;
                boolean found = false;
                     for (String unique: universes){
                        if (tempUniverse.equals(unique))
                            found = true;
            }
            if (!found)
                universes.add(tempUniverse);
            }

        }
    }

    public ArrayList<String> getUniverses(){
       return universes;
    }

     /**
     * Loads CSV file into array of lines.
     * Read whole line into String line
     * Separate using String[] TempString = line.split(",");
     * 
     * @return string of lines from csv file
     * @param input Name of the input file.
     */
    /*public ArrayList<ArrayList<String>> LoadFromFile(){
        String filename = "E:/JWIiUM/Marvel Characters.csv";

        Superhero tempHero = new Superhero();
        
        return _fileData;
        
    }*/
    
    /**
     * 
     * Processes data therefore creating database of superheroes in program.
     * Algorithm:
     * If an item has " at the beginning, add those items to templist of attributes and set bool found to true
     * If an item has " at the end, finish adding items to temp list of attributes and set bool to false
     * Add superhero to database in program
     * Clear temp elements and in another iteration start from beginning 
     * 
     * @param fileData 
     * @return ArrayList of superheroes
     */

     /**
     * 
     * Finding superhero with the biggest amount of superpowers.
     * 
     * @param _database ArrayList of superheroes that stores data in program.
     * @return max Superhero with the biggest amount of superpowers.
     */
    public Superhero FindWithMostSuperpowers(ArrayList<Superhero> _database){
        Superhero max = _database.get(0);
        for (Superhero temp: _database){
            if (temp.getSuperpowers().size() > max.getSuperpowers().size())
                max = temp;
        }
        return max;
    }
     /**
     * 
     * Sorting superheroes in descending order depending on highest amount of superpowers using overrided method compareTo
     * and Comparable interface created in Superhero class.
     * 
     * @param _database ArrayList of superheroes that stores data in program.
     */
   public void MySort(ArrayList<Superhero> _database){
       Collections.sort(_database);
           
   }
   /**
    * 
    * Creating ArrayList of superheroes that are affiliated with chosen universe by user.
    * 
    * @param chosenUniverse Universe chosen by user.
    * @param _database ArrayList of superheroes that stores data in program.
    * @return heroesAffiliated ArrayList of superheroes that are affiliated with the universe chosen by user.
    */
    public ArrayList<Superhero> DisplayAffiliation(String chosenUniverse, ArrayList<Superhero> _database){
        ArrayList<Superhero> heroesAffiliated = new ArrayList();
        for (Superhero checkedSupe: _database){
            boolean found = false;
            for (String checkedUniverse: checkedSupe.getUniverses()){
                if (checkedUniverse.equals(chosenUniverse))
                    found = true;
                if (found){
                    heroesAffiliated.add(checkedSupe);
                    break;
                }
            }
        }
        return heroesAffiliated;
    }
    private ArrayList<Double> CollectTypesIntoArray(ArrayList<Superhero> _database){
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe: _database){
            Double d = Double.valueOf(supe.getTypes().size());
            tempArray.add(d);
        }  
        return tempArray;
    }
    private ArrayList<Double> CollectUniversesIntoArray(ArrayList<Superhero> _database){
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe: _database){
            Double d = Double.valueOf(supe.getUniverses().size());
            tempArray.add(d);
        }  
        return tempArray;
    }
    public double CalculatePearsonCorrelation(ArrayList<Superhero> _database){
        ArrayList<Double> typeArray = CollectTypesIntoArray(_database);
        ArrayList<Double> universeArray = CollectUniversesIntoArray(_database);
        Double[] arr = new Double[typeArray.size()];
        arr = typeArray.toArray(arr);
        double[] tArray= ArrayUtils.toPrimitive(arr);
        Double[] arr2 = new Double[universeArray.size()];
        arr2 = typeArray.toArray(arr2);
        double[] uArray= ArrayUtils.toPrimitive(arr2);
        double corr = new PearsonsCorrelation().correlation(tArray,uArray);
        System.out.println(corr);
        return corr;
    }
    public ArrayList<Superhero> Test (){
        ArrayList<Superhero> tempDatabase = new ArrayList();
        
        Superhero first = new Superhero();
        first.setBirthname("Michal");
        first.setCharname("Programista");
        first.setId("www.zmitac.db.pl");
        ArrayList<String> typy = new ArrayList();
        typy.add("wind");
        typy.add("electric");
        typy.add("fire");
        first.setTypes(typy);
        ArrayList<String> univ = new ArrayList();
        univ.add("poland");
        univ.add("marvel");
        first.setUniverses(univ);
        first.setBirthplace("Bytom");
        ArrayList<String> supe = new ArrayList();
        supe.add("wstrzymywanie oddechu na 20 sekund");
        supe.add("Depresja");
        first.setSuperpowers(supe);
        ArrayList<String> rel = new ArrayList();
        rel.add("Kerfusionizm");
        first.setReligions(rel);
        first.setGender("Male");
        ArrayList<String> occ = new ArrayList();
        occ.add("Programista");
        occ.add("StudentD");
        first.setOccupations(occ);
        ArrayList<String> memof = new ArrayList();
        memof.add("Politechnika");
        first.setMemberof(memof);
        
        Superhero second = new Superhero();
        second.setBirthname("Bartosz");
        second.setCharname("Technik");
        second.setId("www.polsl.pl");
        ArrayList<String> typy2 = new ArrayList();
        typy2.add("stone");
        typy2.add("fire");
        second.setTypes(typy2);
        ArrayList<String> univ2 = new ArrayList();
        univ2.add("marvel");
        univ2.add("poland");
        univ2.add("anotherUniv");
        second.setUniverses(univ2);
        second.setBirthplace("Bytom");
        ArrayList<String> supe2 = new ArrayList();
        supe2.add("Spanie przez 8 godzin");
        supe2.add("Schizofrenia");
        supe2.add("Plywanie pod woda");
        second.setSuperpowers(supe2);
        ArrayList<String> rel2 = new ArrayList();
        rel2.add("Islam");
        second.setReligions(rel2);
        second.setGender("Male");
        ArrayList<String> occ2 = new ArrayList();
        occ2.add("Programista");
        occ2.add("Gracz");
        second.setOccupations(occ2);
        ArrayList<String> memof2 = new ArrayList();
        memof2.add("Elektronik");
        second.setMemberof(memof2);
        
        tempDatabase.add(first);
        tempDatabase.add(second);
        return tempDatabase;
    }
}
