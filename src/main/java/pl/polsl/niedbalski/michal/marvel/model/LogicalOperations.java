
package pl.polsl.niedbalski.michal.marvel.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.lang3.ArrayUtils;



/**
 *
 * @author Michał Niedbalski
 * @version 2.0
 */
public class LogicalOperations {

    private Set<String> universes = new HashSet();
    private ArrayList<Superhero> database = new ArrayList();
    private Path filePath;

    /**
     * Method that checks, whether parameters were inputted or not and if not, exception is thrown
     *
     * @param _params Parameters inputted into program
     */
    public String check(String[] _params) throws FileNotFoundException { //TODO: Throw exception to view
        String fileName = null;
        if (_params.length == 0){
           throw new FileNotFoundException("Missing input file");
        }
        else
            fileName = _params[0];
        return fileName;
    }
    /**
     * Method that loads only unique universes into program.
     *
     * @param _database
     */
    public void loadUniverses(ArrayList<Superhero> _database) {//TODO: Check why it isn't working (add + after s)
        for (Superhero checkedSuperhero: _database){
            for (String UniverseInScope: checkedSuperhero.getUniverses() ){
                UniverseInScope.replaceAll("\\s","");
                universes.add(UniverseInScope);
            }
        }
    }
    public Path getFileName(){return filePath;}
    public void setFileName(String passedFilePath){filePath = Paths.get(passedFilePath);}
    public Set<String> getUniverses(){
       return universes;
    }
    public ArrayList<Superhero> getDatabase () { return database; }
    public void setDatabase(ArrayList<Superhero> updatedDatabase) {database = updatedDatabase;}

     /**
     * Method using great features from commons-csv library in order to load data from .csv file into application.
     * In every iteration of for loop a temporary Superhero is created, who will have it's attributes assigned using method csvRecord.get().
     * In specific case where superhero's attribute is an array of elements, the string read from one cell from .csv file is being parsed by method ParseStringIntoArrayList().
     * After every attribute is assigned, superhero is added to the database in program.
      * @throws IOException
      * @throws FileNotFoundException
     */
    public void loadFile() throws FileNotFoundException, IOException{
            ArrayList<Superhero> tempDatabase = getDatabase();
            if (!checkIfFileExists())
                throw new FileNotFoundException("File not found!");
            else {
                Reader reader = Files.newBufferedReader(filePath);
                CSVParser csvParser = new CSVParser(reader,
                        CSVFormat.DEFAULT
                                .withFirstRecordAsHeader()
                                .withIgnoreHeaderCase()
                                .withTrim());
                for (CSVRecord csvRecord : csvParser) {
                    Superhero temp = new Superhero();
                    temp.setId(csvRecord.get("char"));
                    temp.setCharname(csvRecord.get("charname"));
                    temp.setBirthname(csvRecord.get("birthname"));
                    temp.setTypes(parseStringIntoArrayList(csvRecord.get("types")));
                    temp.setUniverses(parseStringIntoArrayList(csvRecord.get("universes")));
                    temp.setBirthplace(csvRecord.get("birthplace"));
                    temp.setSuperpowers(parseStringIntoArrayList(csvRecord.get("superpowers")));
                    temp.setReligions(parseStringIntoArrayList(csvRecord.get("religions")));
                    temp.setGender(csvRecord.get("gender"));
                    temp.setOccupations(parseStringIntoArrayList(csvRecord.get("occupation")));
                    temp.setMemberof(parseStringIntoArrayList(csvRecord.get("memberof")));
                    tempDatabase.add(temp);
                }
                setDatabase((tempDatabase));
                mySort();
            }
    }
    /**
     * Method used to split one string with commas into many strings connected into one ArrayList of strings.
     * 
     * @param parsedString String that has been passed from Loading method in order to be parsed.
     * @return converted ArrayList of Strings that can be assigned as class parameter whenever it is an ArrayList of Strings.
     */
    private ArrayList<String> parseStringIntoArrayList(String parsedString){
       String[] parsed;
       parsed = parsedString.split(",");
       return new ArrayList<>(Arrays.asList(parsed));
    }
     /**
     * 
     * Finding superhero with the biggest amount of superpowers.
     *
     * @return max Superhero with the biggest amount of superpowers.
     */
    public Superhero findWithMostSuperpowers(){
        Superhero max = getDatabase().get(0);
        for (Superhero temp: getDatabase()){
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
     */
     private void mySort(){
        // ArrayList<Superhero> tempDatabase = getDatabase();
         Collections.sort(database); //TODO: Add setter to database
     }
   /*public void mySort(ArrayList<Superhero> superheroes){
       Collections.sort(superheroes);
   }*/
   /**
    * 
    * Creating ArrayList of superheroes that are affiliated with chosen universe by user.
    * 
    * @param chosenUniverse Universe chosen by user.
    * @param superheroes ArrayList of superheroes that stores data in program.
    * @return heroesAffiliated ArrayList of superheroes that are affiliated with the universe chosen by user.
    */
    public ArrayList<Superhero> displayAffiliation(String chosenUniverse, ArrayList<Superhero> superheroes){
        ArrayList<Superhero> heroesAffiliated = new ArrayList();
        for (Superhero checkedSupe: superheroes){
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
    private ArrayList<Double> collectTypesIntoArray(ArrayList<Superhero> _database){
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
    private ArrayList<Double> collectUniversesIntoArray(ArrayList<Superhero> _database){
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
     * @return corr Value of correlation factor.
     */
    public double calculatePearsonCorrelation(){
        ArrayList<Double> typeArray = collectTypesIntoArray(getDatabase());
        ArrayList<Double> universeArray = collectUniversesIntoArray(getDatabase());
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
    /**
     * Returning chosen universe to display superheroes affiliated.
     * @param choice Chosen index of universe
     * @return element chosen universe.
     */
    public String chosenUniverse(int choice){
        int counter = 1;
        for (String element: universes){
            if (counter==choice)
                return element;
        }
        return null;

    }
    /**
     * Creating ArrayList of superheroes that are affiliated with chosen universe by user
     * @param chosenUniverse Name of the chosen universe by user.
     * @_database ArrayList of superheroes that stores data in program.
     */
    public ArrayList<Superhero> findSuperheroesFromUniverse(String chosenUniverse, ArrayList<Superhero> superheroes){
        ArrayList<Superhero> tempArray = new ArrayList();
        for (Superhero supe: superheroes){
            for (String uni: supe.getUniverses()){
                if (uni.equals(chosenUniverse));
                    break;
            }
            tempArray.add(supe);
        }
        return tempArray;    
    }
    public boolean checkIfCorrect(int maxRange, int chosenOption) throws UserInfoException {
        if (chosenOption>maxRange || chosenOption < 1){
            throw new UserInfoException("Your choice is out of bounds!");
        }
        else
            return true;
    }
    public boolean checkIfFileExists(){
        if (Files.isReadable(filePath))
            return true;
        else
            return false;
    }

}
