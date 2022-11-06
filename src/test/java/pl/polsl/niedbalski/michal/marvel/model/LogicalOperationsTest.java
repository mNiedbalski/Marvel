/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.niedbalski.michal.marvel.model;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.*;

/**
 *
 * @author Student ETO-B 10
 */
public class LogicalOperationsTest {
    
    public LogicalOperationsTest() {
    }

    /**
     * Test of LogicalOperations method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    public void testLogicalOperations() {
    }

    /**
     * Test of LoadUniverses method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test

    @ParameterizedTest //Testing whether those 3 strings are equal or not (they shouldnt)
    @ValueSource(strings = {"Marvel Universe", " Marvel Universe", "Marvel Universe "})
    public void testLoadUniverses() {
    }

    /**
     * Test of getUniverses method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    public void testGetUniverses() {
    }

    /**
     * Test of LoadFile method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    @ValueSource(strings = {"bad/file/path"})
    public void testLoadFile() {
    }

    /**
     * Test of ParseStringIntoArrayList method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    public void testParseStringIntoArrayList() {
    }

    /**
     * Test of FindWithMostSuperpowers method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    public void testFindWithMostSuperpowers() {
    }

    /**
     * Test of MySort method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    public void testMySort() {
    }

    /**
     * Test of DisplayAffiliation method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    public void testDisplayAffiliation() {
    }

    /**
     * Test of CalculatePearsonCorrelation method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    @CsvSource({"1,1","1,1","1,1"})
    public void testCalculatePearsonCorrelation() {
    }

    /**
     * Test of ChosenUniverse method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    public void testChosenUniverse() {
    }

    /**
     * Test of FindSuperheroesFromUniverse method, of class LogicalOperations.
     */
    @org.junit.jupiter.api.Test
    @ParameterizedTest
    public void testFindSuperheroesFromUniverse() {
    }
    
}
