
package pl.polsl.niedbalski.michal.marvel.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.csv.*;
import org.apache.commons.lang3.ArrayUtils;



/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
public class LogicalOperations {

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
    public void LoadFile(String fileName, ArrayList<Superhero> _database){
        try {
            Reader reader = Files.newBufferedReader(Paths.get("E:/JWIiUM/Marvel Characters.csv"));
            CSVParser csvParser = new CSVParser(reader,
                    CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            for (CSVRecord csvRecord: csvParser){               
                    Superhero temp = new Superhero();
                    temp.setId(csvRecord.get("char"));
                    temp.setCharname(csvRecord.get("charname"));
                    temp.setBirthname(csvRecord.get("birthname"));
                    temp.setTypes(ParseStringIntoArrayList(csvRecord.get("types")));
                    temp.setUniverses(ParseStringIntoArrayList(csvRecord.get("universes")));
                    temp.setBirthplace(csvRecord.get("birthplace"));
                    temp.setSuperpowers(ParseStringIntoArrayList(csvRecord.get("superpowers")));
                    temp.setReligions(ParseStringIntoArrayList(csvRecord.get("religions")));
                    temp.setGender(csvRecord.get("gender"));
                    temp.setOccupations(ParseStringIntoArrayList(csvRecord.get("occupation")));
                    temp.setMemberof(ParseStringIntoArrayList(csvRecord.get("memberof")));
                    _database.add(temp);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found exception!");
        }
        catch (IOException e2){
            System.out.println("IOException!");
        }
        
    }
    public ArrayList<String> ParseStringIntoArrayList(String parsedString){
       String[] parsed;
       parsed = parsedString.split(","); 
       //Long int zmienna = parsed.toLongInt();
       //return zmienna;
       ArrayList <String> converted = new ArrayList<String>(Arrays.asList(parsed));
       return converted;
       
    }
    
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
    /**
     * Method used to count number of superhero's types. 
     * Each index represents one superhero and their amount of types.
     * 
     * @param _database ArrayList of superheroes that stores data in program.
     * @return tempArray ArrayList containing amount of types of each superhero.
     */
    private ArrayList<Double> CollectTypesIntoArray(ArrayList<Superhero> _database){
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe: _database){
            Double d = Double.valueOf(supe.getTypes().size());
            tempArray.add(d);
        }  
        return tempArray;
    }
    /**
     * Method used to count number of universe affiliations of every superhero. 
     * Each index represents one superhero and their amount of affiliations.
     * 
     * @param _database ArrayList of superheroes that stores data in program.
     * @return tempArray ArrayList containing amount of affiliations per every superhero.
     */
    private ArrayList<Double> CollectUniversesIntoArray(ArrayList<Superhero> _database){
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe: _database){
            Double d = Double.valueOf(supe.getUniverses().size());
            tempArray.add(d);
        }  
        return tempArray;
    }
    /**
     * Method to calculate Pearson Correlation factor between number of types and hero's affiliations
     * This method uses a method from 3rd party library - org.apache.commons.math3
     * Firstly, two array lists of type Double are created that will store number of types and number of hero's affiliations
     * Secondly, these array lists are casted into normal arrays of Double type
     * Then they have to be converted from Double wrapper into double primitive type, so therefore they can fit into parameters accepted by function PearsonsCorrelation().
     * 
     * @param _database ArrayList of superheroes that stores data in program.
     * @return corr Value of correlation factor.
     */
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

}
