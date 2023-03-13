package pl.polsl.niedbalski.michal.marvel.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michał Niedbalski
 * @version 2.0
 */
class LogicalOperationsTest {
    /**
     * Test of findWithMostSuperpowers method, of class LogicalOperations.
     * Checking if superhero returned from method findWithMostSuperpowers() is the same that the one at the top of database (database is sorted by amount of superpowers in descending order)
     */
    @ParameterizedTest
    @ValueSource (ints={0})
    void findWithMostSuperpowers(int indexOfSuperhero) {
        //String[] testParams = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"};
        LogicalOperations testLogical = LogicalOperations.getInstance();
        try {
            testLogical.prepareDatabase();
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
        //String[] testParams = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"};
        LogicalOperations testLogical = LogicalOperations.getInstance();
        try {
            testLogical.prepareDatabase();
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
        LogicalOperations testLogical = LogicalOperations.getInstance();
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
     * Test of findSuperheroesFromUniverse method, of class LogicalOperations.
     * The purpose of this test is to check if method finding superheroes affiliated with chosen universe works correctly.
     * If a superhero is found, that doesn't belong to chosen universe, test is failed.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Earth-616","Marvel Universe","Earth-1191"})
    void findSuperheroesFromUniverseCorrect(String testUniverse) {
        String[] testParams = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"};
        LogicalOperations testLogical = LogicalOperations.getInstance();
        try {
            ArrayList<Superhero> testSuperheroes;
            testLogical.prepareDatabase();
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
        LogicalOperations testLogical = LogicalOperations.getInstance();
        try {
            ArrayList<Superhero> testSuperheroes = new ArrayList();
            testLogical.prepareDatabase();
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
     * Test of checkIfFileExists method, of class LogicalOperations.
     * Testing on file that is readable.
     */
    @ParameterizedTest
    @ValueSource (strings = {"E:/JWIiUM/Niedbalski.Michal.cw2.prototyp/Niedbalski.Michal.cw1.final/src/main/resources/Marvel Characters.csv"})
    void checkIfFileExists(String filepath) {
        Path testedPath = Paths.get(filepath);
        LogicalOperations testLogical = LogicalOperations.getInstance();
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
        LogicalOperations testLogical = LogicalOperations.getInstance();
        testLogical.setFilePath(testedPath);
        assertFalse(testLogical.checkIfFileExists(),"File can't be read.");
    }
}