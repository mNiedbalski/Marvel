package pl.polsl.niedbalski.michal.marvel.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class handling all data processing and giving outputs. This class is representing model in MVC pattern.
 * @author Michał Niedbalski
 * @version 3.0
 */
public final class LogicalOperations {

    /**
     * Set containing unique universes.
     */
    private Set<String> universes = new HashSet<>();
    /**
     * Class field representing database of the program.
     */
    private ArrayList<Superhero> database = new ArrayList<>();
    /**
     * Class field storing information about file path.
     */
    private Path filePath;
    /**
     * Class field representing instance of model (Singleton).
     */
    private static LogicalOperations instance;
    
    private LogicalOperations(){
        try{
            prepareDatabase();
        }
        catch (IOException e){
            
        }
    }
    public static LogicalOperations getInstance(){
        if (instance == null)
            instance = new LogicalOperations();
        return instance;
    }
    /**
     * Method that checks, whether parameters were inputted or not and if not, exception is thrown
     *
     * @param passedParams Parameters inputted into program
     * @return fileName
     */
    private String check(String[] passedParams) throws FileNotFoundException {
        String fileName = null;
        if (passedParams.length == 0) {
            throw new FileNotFoundException("Missing input file");
        } else
            fileName = passedParams[0];
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

    /**
     * File is also located in Marvel/src/main/resources/Marvel Characters.csv but somehow I have problems with accessing it.
     */
    private void setFileName() {
    String passedFilePath="E:/JWIiUM/NetBeans projekty sprawdzanie/MarvelWebApp/src/main/resources/Marvel Characters.csv";
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

    /**
     * Method used to get together two private methods in one spot. It is used also in tests so code looks cleaner.
     * @param passedParamsToModel List of passed params to model.
     * @throws IOException When I/O exception happens.
     * @throws FileNotFoundException When file hasn't been found.
     */
    public void prepareDatabase() throws IOException, FileNotFoundException {
        setFileName();
        loadFile();
        
    }
    /**
     * Method using great features from commons-csv library in order to load data from .csv file into application.
     * In every iteration of for loop a temporary Superhero is created, who will have its attributes assigned using method csvRecord.get().
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
    @MainFunctionalityMethods
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
    @MainFunctionalityMethods
    private void mySort() {
        Collections.sort(database);
    }
    /**
     * Method used to count amount of superhero's types.
     * Each index represents one superhero and their amount of types.
     *
     * @return tempArray ArrayList containing amount of types of each superhero.
     */
    private ArrayList<Double> collectTypesIntoArray() {
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe : database) {
            Double d = (double) supe.getTypes().size();
            tempArray.add(d);
        }
        return tempArray;
    }

    /**
     * Method used to count amount of universe affiliations of every superhero.
     * Each index represents one superhero and their amount of affiliations.
     *
     * @return tempArray ArrayList containing amount of affiliations per every superhero.
     */
    private ArrayList<Double> collectUniversesIntoArray() {
        ArrayList<Double> tempArray = new ArrayList();
        for (Superhero supe : database) {
            Double d = (double) supe.getUniverses().size();
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
    @MainFunctionalityMethods
    public double calculatePearsonCorrelation() {
        ArrayList<Double> typeArray = collectTypesIntoArray();
        ArrayList<Double> universeArray = collectUniversesIntoArray();
        Double[] arr = new Double[typeArray.size()];
        arr = typeArray.toArray(arr);
        double[] tArray = ArrayUtils.toPrimitive(arr);
        Double[] arr2 = new Double[universeArray.size()];
        arr2 = universeArray.toArray(arr2);
        double[] uArray = ArrayUtils.toPrimitive(arr2);
        double corr = new PearsonsCorrelation().correlation(tArray, uArray);
        System.out.println(corr);
        return corr;
    }

    /**
     * Creating ArrayList of superheroes that are affiliated with chosen universe by user.
     * This method uses Stream to process data.
     *
     * @param chosenUniverse Name of the chosen universe by user.
     * @return foundSuperheroes Array of superheroes from chosen universe
     */
    @MainFunctionalityMethods
    public ArrayList<Superhero> findSuperheroesFromUniverseUsingStream(String chosenUniverse){
        List<Superhero> foundSuperheroes;
        foundSuperheroes = database.stream()
                .filter(superhero -> superhero.universes.contains(chosenUniverse))
                .collect(Collectors.toList());
        Map<String,Superhero> processedSuperheroes = new HashMap();
        for (Superhero temp: foundSuperheroes)
            processedSuperheroes.put(temp.getCharName(),temp);
        foundSuperheroes.clear();
        for (Map.Entry<String,Superhero> entry : processedSuperheroes.entrySet())
            foundSuperheroes.add(entry.getValue());
        Collections.sort(foundSuperheroes);
        return (ArrayList<Superhero>) foundSuperheroes;
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
