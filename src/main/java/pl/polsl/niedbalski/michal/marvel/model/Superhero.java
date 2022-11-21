package pl.polsl.niedbalski.michal.marvel.model;
import java.util.ArrayList;

/**
 * Class representing superhero in application.
 * @author Michał Niedbalski
 * @version 3.0
 */
public class Superhero implements Comparable<Superhero> {
    /**
     * Class field representing "char" column in database.
     */
    String id;
    /**
     * Class field representing "character name" column in database.
     */
    String charName;
    /**
     * Class field representing "birth name" column in database.
     */
    String birthName;
    /**
     * Class representing "types" column in database.
     */
    ArrayList<String> types;
    /**
     * Class representing "universes" column in database.
     */
    ArrayList<String> universes;
    /**
     * Class field representing "birthplace" column in database.
     */
    String birthplace;
    /**
     * Class field representing "superpowers" in database.
     */
    ArrayList<String> superpowers;
    /**
     * Class field representing "religions" column in database.
     */
    ArrayList<String> religions;
    /**
     * Class field representing "gender" column in database.
     */
    String gender;
    /**
     * Class field representing "occupation" column in database.
     */
    ArrayList<String> occupation;
    /**
     * Class field representing "member of" column in database.
     */
    ArrayList<String> memberof;

    public Superhero() {}


    @Override
    /**
     * Overrided method of comparation.
     * It has been overrided in order to be used in sort function provided by java.util.Collections class.
     * @param s compared superhero
     * @return 0 when amount of superpowers are equal.
     * @return -1 when the other superhero has fewer superpowers.
     * @return 1 when the other superhero has more superpowers.
     */
    public int compareTo(Superhero s) {
        if (this.getSuperpowers().size() == s.getSuperpowers().size())
            return 0;
        else if (this.getSuperpowers().size() > s.getSuperpowers().size())
            return -1;
        else
            return 1;


    }

    //Setters

    public void setId(String passedId) {
        this.id = passedId;
    }

    public void setCharName(String passedCharname) {
        this.charName = passedCharname;
    }

    public void setBirthName(String passedBirthName) {
        this.birthName = passedBirthName;
    }

    public void setTypes(ArrayList<String> passedTypes) {
        this.types = passedTypes;
    }

    public void setUniverses(ArrayList<String> passedUniverses) {
        this.universes = passedUniverses;
    }

    public void setBirthplace(String passedBirthplace) {
        this.birthplace = passedBirthplace;
    }

    public void setSuperpowers(ArrayList<String> passedSuperpowers) {
        this.superpowers = passedSuperpowers;
    }

    public void setReligions(ArrayList<String> passedReligions) {
        this.religions = passedReligions;
    }

    public void setGender(String passedGender) {
        this.gender = passedGender;
    }

    public void setOccupations(ArrayList<String> passedOccupations) {
        this.occupation = passedOccupations;
    }

    public void setMemberof(ArrayList<String> passedMemberOf) {
        this.memberof = passedMemberOf;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getCharName() {
        return charName;
    }

    public String getBirthName() {
        return birthName;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public ArrayList<String> getUniverses() {
        return universes;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public ArrayList<String> getSuperpowers() {
        return superpowers;
    }

    public ArrayList<String> getReligions() {
        return religions;
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<String> getOccupations() {
        return occupation;
    }

    public ArrayList<String> getMemberof() {
        return memberof;
    }

}
