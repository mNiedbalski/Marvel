
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
 * @author Michał Niedbalski
 * @version 2.0
 */
public class LogicalOperations {

    private Set<String> universes = new HashSet<>();
    private ArrayList<Superhero> database = new ArrayList<>();
    private Path filePath;

    /**
     * Method that checks, whether parameters were inputted or not and if not, exception is thrown
     *
     * @param _params Parameters inputted into program
     */
    private String check(String[] _params) throws FileNotFoundException {
        String fileName = null;
        if (_params.length == 0) {
            throw new FileNotFoundException("Missing input file");
        } else
            fileName = _params[0];
        return fileName;
    }

    /**
     * Method that loads only unique universes into program.
     */
    public void loadUniverses() {
        for (Superhero checkedSuperhero : database) {
            for (String UniverseInScope : checkedSuperhero.getUniverses()) {
                universes.add(UniverseInScope.strip());
            }
        }
    }
    public void setFilePath(Path passedFilePath) {filePath = passedFilePath;}

    public Path getFileName() {
        return filePath;
    }

    private void setFileName(String passedFilePath) {
        filePath = Paths.get(passedFilePath);
    }

    public Set<String> getUniverses() {
        return universes;
    }

    public void setUniverses(Set<String> universes) {
        this.universes = universes;
    }

    public ArrayList<Superhero> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<Superhero> updatedDatabase) {
        database = updatedDatabase;
    }

    public void prepareDatabase(String[] passedParamsToModel) throws IOException, FileNotFoundException {
        setFileName(check(passedParamsToModel));
        loadFile();
    }
    /**
     * Method using great features from commons-csv library in order to load data from .csv file into application.
     * In every iteration of for loop a temporary Superhero is created, who will have it's attributes assigned using method csvRecord.get().
     * In specific case where superhero's attribute is an array of elements, the string read from one cell from .csv file is being parsed by method ParseStringIntoArrayList().
     * After every attribute is assigned, superhero is added to the database in program.
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    private void loadFile() throws FileNotFoundException, IOException {
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
                temp.setCharName(csvRecord.get("charname"));
                temp.setBirthName(csvRecord.get("birthname"));
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
            setDatabase(tempDatabase);
            mySort();
            loadUniverses();
        }
    }

    /**
     * Method used to split one string with commas into many strings connected into one ArrayList of strings.
     * This method also removes all unnecessary white space from before phrase, and after.
     *
     * @param parsedString String that has been passed from Loading method in order to be parsed.
     * @return parsedAndStripped ArrayList of Strings that can be assigned as class parameter whenever it is an ArrayList of Strings.
     */
    private ArrayList<String> parseStringIntoArrayList(String parsedString) {
        String[] parsed;
        parsed = parsedString.split(",");
        ArrayList<String> parsedAndStripped = new ArrayList();
        for (String stringInScope : parsed)
            parsedAndStripped.add(stringInScope.strip());
        return parsedAndStripped;
    }

    /**
     * Finding superhero with the biggest amount of superpowers.
     *
     * @return max Superhero with the biggest amount of superpowers.
     */
    public Superhero findWithMostSuperpowers() {
        Superhero max = getDatabase().get(0);
        for (Superhero temp : getDatabase()) {
            if (temp.getSuperpowers().size() > max.getSuperpowers().size())
                max = temp;
        }
        return max;
    }

    /**
     * Sorting superheroes in descending order depending on highest amount of superpowers using overrided method compareTo
     * and Comparable interface created in Superhero class.
     */
    private void mySort() {
        Collections.sort(database);
    }
    /**
     * Method used to count number of superhero's types.
     * Each index represents one superhero and their amount of types.
     *
     * @return tempArray ArrayList containing amount of types of each superhero.
     */
    private ArrayList<Double> collectTypesIntoArray() {
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe : database) {
            Double d = Double.valueOf(supe.getTypes().size());
            tempArray.add(d);
        }
        return tempArray;
    }

    /**
     * Method used to count number of universe affiliations of every superhero.
     * Each index represents one superhero and their amount of affiliations.
     *
     * @return tempArray ArrayList containing amount of affiliations per every superhero.
     */
    private ArrayList<Double> collectUniversesIntoArray() {
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe : database) {
            Double d = Double.valueOf(supe.getUniverses().size());
            tempArray.add(d);
        }
        return tempArray;
    }

    /**
     * Method to calculate Pearson Correlation factor between number of types and hero's affiliations
     * This method uses a method from 3rd party library - org.apache.commons.math3
     * Firstly, two array lists of type Double are created that will store number of types and number of hero's affiliations
     * Secondly, these array lists are cast into normal arrays of Double type
     * Then they have to be converted from Double wrapper into double primitive type, therefore they can fit into parameters accepted by function PearsonsCorrelation().
     *
     * @return corr Value of correlation factor.
     */
    public double calculatePearsonCorrelation() {
        ArrayList<Double> typeArray = collectTypesIntoArray();
        ArrayList<Double> universeArray = collectUniversesIntoArray();
        Double[] arr = new Double[typeArray.size()];
        arr = typeArray.toArray(arr);
        double[] tArray = ArrayUtils.toPrimitive(arr);
        Double[] arr2 = new Double[universeArray.size()];
        arr2 = typeArray.toArray(arr2);
        double[] uArray = ArrayUtils.toPrimitive(arr2);
        double corr = new PearsonsCorrelation().correlation(tArray, uArray);
        System.out.println(corr);
        return corr;
    }

    /**
     * Returning chosen universe to display superheroes affiliated.
     *
     * @param choice Chosen index of universe
     * @return element chosen universe.
     */
    public String chosenUniverse(int choice) {
        int counter = 1;
        for (String element : universes) {
            if (counter == choice)
                return element;
            counter++;
        }
        return null;

    }

    /**
     * Creating ArrayList of superheroes that are affiliated with chosen universe by user
     *
     * @param chosenUniverse Name of the chosen universe by user.
     * @return tempArray Array of superheroes from chosen universe
     */
    public ArrayList<Superhero> findSuperheroesFromUniverse(String chosenUniverse) {
        ArrayList<Superhero> tempArray = new ArrayList();
        for (Superhero supe : database) {
            boolean belongs = false;
            for (String uni : supe.getUniverses()) {
                if (uni.equals(chosenUniverse)) {
                    belongs = true;
                    break;
                }
            }
            if (belongs)
                tempArray.add(supe);
        }
        return tempArray;
    }
    /**
     * Checks if option chosen by user is in range given by application - if not, a custom exception is thrown to be handled in controller.
     * Return false is not needed, because exception will be thrown instead of it.
     *
     * @param maxRange     Highest possible option available in application.
     * @param chosenOption A number representing user's choice
     * @return true if choice is correct
     * @throws UserInfoException if choice was incorrect
     */
    public boolean checkIfCorrect(int maxRange, int chosenOption) throws UserInfoException {
        if (chosenOption > maxRange || chosenOption < 1) {
            throw new UserInfoException("Your choice is out of bounds!");
        } else
            return true;
    }

    /**
     * Method that checks if file from given path is readable
     *
     * @return true if file is readable
     * @return false if file isn't readable
     */
    public boolean checkIfFileExists() {
        if (Files.isReadable(filePath))
            return true;
        else
            return false;
    }

}
