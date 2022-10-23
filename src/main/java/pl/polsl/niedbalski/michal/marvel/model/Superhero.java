
package pl.polsl.niedbalski.michal.marvel.model;

import java.util.ArrayList;

/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
public class Superhero implements Comparable<Superhero> {
        String id;
        String charname;
        String birthname;
        ArrayList <String> types;
        ArrayList <String> universes;
        String birthplace;
        ArrayList <String> superpowers;
        ArrayList <String> religions;
        String gender;
        ArrayList <String> occupation;
        ArrayList <String> memberof;
        public Superhero(){}
        
        public int compare(Superhero s1, Superhero s2){
            return s1.superpowers.size() - s2.superpowers.size();
        }
        @Override
         public int compareTo(Superhero s) {
             if (this.getSuperpowers().size()==s.getSuperpowers().size())
                 return 0;
             else if (this.getSuperpowers().size()>s.getSuperpowers().size())
                 return -1;
             else
                 return 1;
                 
            
         }
        
        
        //Setters
        
        public void setId(String _id){
            this.id = _id;
        }
        public void setCharname(String _charname){
            this.charname= _charname;
        }
        public void setBirthname(String _birthname){
            this.birthname = _birthname;
        }
        public void setTypes(ArrayList<String> _types){
            this.types= _types;
        }
        public void setUniverses(ArrayList<String> _universes){
            this.universes= _universes;
        }
        public void setBirthplace(String _birthplace){
            this.birthplace = _birthplace;
        }
        public void setSuperpowers(ArrayList<String> _superpowers){
            this.superpowers= _superpowers;
        }
        public void setReligions(ArrayList<String> _religions){
            this.religions= _religions;
        }
        public void setGender(String _gender){
            this.gender = _gender;
        }
        public void setOccupations(ArrayList<String> _occupations){
            this.occupation= _occupations;
        }
        public void setMemberof(ArrayList<String> _memberof){
            this.memberof= _memberof;
        }
        
        //Getters
        public String getId(){
            return this.id;
        }
        public String getCharname(){
            return this.charname;
        }
        public String getBirthname(){
            return this.birthname;
        }
        public ArrayList<String> getTypes(){
            return this.types;
        }
        public ArrayList<String> getUniverses(){
            return this.universes;
        }
        public String getBirthplace(){
            return this.birthplace;
        }
        public ArrayList<String> getSuperpowers(){
            return this.superpowers;
        }
        public ArrayList<String> getReligions(){
            return this.religions;
        }
        public String getGender(){
            return this.gender;
        }
        public ArrayList<String> getOccupations(){
            return this.occupation;
        }
        public ArrayList<String> getMemberof(){
            return this.memberof;
        }

}
