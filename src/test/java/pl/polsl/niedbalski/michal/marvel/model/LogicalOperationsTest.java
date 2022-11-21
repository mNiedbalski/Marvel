package pl.polsl.niedbalski.michal.marvel.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michał Niedbalski
 * @version 1.0
 */
class LogicalOperationsTest { //TODO: Optimize code so no copy + paste is made. (Add general functions.)
    /**
     * Test of findWithMostSuperpowers method, of class LogicalOperations.
     * Checking if superhero returned from method findWithMostSuperpowers() is the same that the one at the top of database (database is sorted by amount of superpowers in descending order)
     */
    @ParameterizedTest
    @ValueSource (ints={0})
    void findWithMostSuperpowers(int indexOfSuperhero) {
        String[] testParams = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"};
        LogicalOperations testLogical = new LogicalOperations();
        try {
            ArrayList<Superhero> testSuperheroes;
            testLogical.prepareDatabase(testParams);
            Superhero withMostSuperpowers = testLogical.findWithMostSuperpowers();
            assertEquals(withMostSuperpowers, testLogical.getDatabase().get(indexOfSuperhero), "Expected superheroes to be equal, but they aren't.");

        }
        catch (IOException ex) {

        }

    }
    /**
     * Test of findWithMostSuperpowers method, of class LogicalOperations.
     * Checking if superhero returned from method findWithMostSuperpowers() is not the same as others from database (database is sorted by amount of superpowers in descending order)
     */
    @ParameterizedTest
    @ValueSource (ints={1,2,3,4,5,6,7,8})
    void findWithMostSuperpowersWrongIndex(int indexOfSuperhero) {
        String[] testParams = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"};
        LogicalOperations testLogical = new LogicalOperations();
        try {
            ArrayList<Superhero> testSuperheroes;
            testLogical.prepareDatabase(testParams);
            Superhero withMostSuperpowers = testLogical.findWithMostSuperpowers();
            assertNotEquals(withMostSuperpowers, testLogical.getDatabase().get(indexOfSuperhero), "Expected superheroes to be equal, but they aren't.");

        }
        catch (IOException ex) {

        }

    }
    /**
     * Test of calculatePearsonCorrelation method, of class LogicalOperations.
     * Value of the function has been calculated using external sources, and in test it is checked, whether it matches with method output or not.
     * Test values in arrays
     * typesArray={0,1,2,3,4}
     * universesArray={4,3,2,1,0}
     * In this case, value of coefficient should equal -1
     */
    @ParameterizedTest
    @ValueSource(ints={5})
    void calculatePearsonCorrelation(int elements) { //TODO: Case with bad data.
        LogicalOperations testLogical = new LogicalOperations();
        ArrayList<Superhero> testSupes = new ArrayList<>();
        ArrayList<ArrayList<String>> collectTestValues = new ArrayList<>();
        for (int i=0;i<elements;i++){
            Superhero tempSupe = new Superhero();
            ArrayList<String> tempContentsToSet = new ArrayList<>();
            for (int j=0;j<i;j++){
                tempContentsToSet.add("testValue");
            }
            tempSupe.setTypes(tempContentsToSet);
            tempSupe.setUniverses(tempContentsToSet);
            testSupes.add(tempSupe);
            collectTestValues.add(tempContentsToSet);
        }
        Collections.reverse(collectTestValues);
        for (int i=0;i<elements;i++){
            testSupes.get(i).setUniverses(collectTestValues.get(i));
        }
        testLogical.setDatabase(testSupes);
        assertEquals(-1,testLogical.calculatePearsonCorrelation(),"Value returned from Pearson Correlation method is not equal to the one calculated externally.");

    }
    /**
     * Test of chosenUniverse method, of class LogicalOperations.
     * Test checks whether user choice matches position of universe in collection.
     * If user has inputted number 1, they chose ZMiTAC universe.
     * Displaying method starts displaying choices starting at number one, henceforth if number 1 is inputted, element on position 0 will be chosen.
     */
    @ParameterizedTest
    @ValueSource(ints = {2})
    void chosenUniverseCorrect(int choice) {
        LogicalOperations testLogical = new LogicalOperations();
        Set<String> tempValues = new HashSet();
        tempValues.add("ZMiTAC");
        tempValues.add("Marvel Universe");
        tempValues.add("Earth-616");
        testLogical.setUniverses(tempValues);
        assertEquals("Marvel Universe",testLogical.chosenUniverse(choice), "Returned universe is not the same as it was expected.");
    }
    /**
     * Test of chosenUniverse method, of class LogicalOperations.
     * Test checks, with obviously wrong data, whether user choice matches position of universe in collection.
     */
    @ParameterizedTest
    @ValueSource(ints = {-3,214,0})
    void chosenUniverseWrongInput(int choice) {
        LogicalOperations testLogical = new LogicalOperations();
        Set<String> tempValues = new HashSet();
        tempValues.add("ZMiTAC");
        tempValues.add("Marvel Universe");
        tempValues.add("Earth-616");
        testLogical.setUniverses(tempValues);
        assertNotEquals("Marvel Universe",testLogical.chosenUniverse(choice), "Returned universe is matching the one in set, which was not planned.");
    }
    /**
     * Test of findSuperheroesFromUniverse method, of class LogicalOperations.
     * The purpose of this test is to check if method finding superheroes affiliated with chosen universe works correctly.
     * If a superhero is found, that doesn't belong to chosen universe, test is failed.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Earth-616","Marvel Universe","Earth-1191"})
    void findSuperheroesFromUniverseCorrect(String testUniverse) {
        String[] testParams = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"};
        LogicalOperations testLogical = new LogicalOperations();
        try {
            ArrayList<Superhero> testSuperheroes;
            testLogical.prepareDatabase(testParams);
            testSuperheroes = testLogical.findSuperheroesFromUniverseUsingStream(testUniverse);
            for (Superhero tested: testSuperheroes){
                String tempUniverse = null;
                for (String universeInScope: tested.getUniverses()){
                    if (testUniverse.equals(universeInScope)){
                        tempUniverse = universeInScope;
                        break;
                    }
                }
                assertEquals(testUniverse,tempUniverse,"A superhero has been found that isn't affiliated with this universe.");
            }

        }
        catch (IOException ex) {

        }

    }
    /**
     * Test of findSuperheroesFromUniverse method, of class LogicalOperations.
     * The purpose of this test is to check if method finding superheroes affiliated with chosen universe works correctly.
     * If a superhero is found, that doesn't belong to chosen universe, test is failed.
     */
    @ParameterizedTest
    @ValueSource(strings = {"null","Earth-2317","Morbin' Time"})
    void findSuperheroesFromUniverseBadData(String testUniverse) {
        String[] testParams = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"};
        LogicalOperations testLogical = new LogicalOperations();
        try {
            ArrayList<Superhero> testSuperheroes = new ArrayList();
            testLogical.prepareDatabase(testParams);
            testSuperheroes = testLogical.findSuperheroesFromUniverseUsingStream(testUniverse);
            for (Superhero tested: testSuperheroes){
                String tempUniverse = null;
                for (String universeInScope: tested.getUniverses()){
                    if (testUniverse.equals(universeInScope)){
                        tempUniverse = universeInScope;
                        break;
                    }
                }
                assertNotEquals(testUniverse,tempUniverse,"A superhero has been found that shouldn't have been found, due to bad inputted test data.");
            }

        }
        catch (IOException ex) {

        }
    }
    /**
     * Test of checkIfCorrect method, of class LogicalOperations.
     * Test checks if correct values will work properly.
     */
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    void checkIfCorrectOutput(int testedValue) {
        try {
            LogicalOperations testLogical = new LogicalOperations();
            assertTrue(testLogical.checkIfCorrect(4, testedValue));
        }
        catch(UserInfoException e){
            //Catch won't be reached anyway.
        }
    }
    /**
     * Test of checkIfCorrect method, of class LogicalOperations.
     * Test checks if incorrect values will throw exception.
     */
    @ParameterizedTest
    @ValueSource(ints = {0,-5,8,-2,5})
    void checkIfCorrectExceptionThrow(int testedValue) {
        LogicalOperations testLogical = new LogicalOperations();
        UserInfoException thrown = Assertions.assertThrows(UserInfoException.class, () -> {
            testLogical.checkIfCorrect(4,testedValue);
        }, "UserInfoException was expected");
    }
    /**
     * Test of checkIfFileExists method, of class LogicalOperations.
     * Testing on file that is readable.
     */
    @ParameterizedTest
    @ValueSource (strings = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"})
    void checkIfFileExists(String filepath) {
        Path testedPath = Paths.get(filepath);
        LogicalOperations testLogical = new LogicalOperations();
        testLogical.setFilePath(testedPath);
        assertTrue(testLogical.checkIfFileExists(),"File can't be read.");
    }
    /**
     * Test of checkIfFileExists method, of class LogicalOperations.
     * Testing on file that doesn't exist.
     */
    @ParameterizedTest
    @ValueSource (strings = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/badfile.csv"})
    void checkIfFileExistsWrongParameter(String filepath) {
        Path testedPath = Paths.get(filepath);
        LogicalOperations testLogical = new LogicalOperations();
        testLogical.setFilePath(testedPath);
        assertFalse(testLogical.checkIfFileExists(),"File can't be read.");
    }
}