
package pl.polsl.model;

import java.util.List;

/**
 *
 * @author Michal Niedbalski
 * @version 1.0
 */
public class Superhero implements Comparable<Superhero> {
        String id;
        String charname;
        String birthname;
        List <String> types;
        List <String> universes;
        String birthplace;
        List <String> superpowers;
        List <String> religions;
        String gender;
        List <String> occupation;
        List <String> memberof;
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
        public void setTypes(List<String> _types){
            this.types= _types;
        }
        public void setUniverses(List<String> _universes){
            this.universes= _universes;
        }
        public void setBirthplace(String _birthplace){
            this.birthplace = _birthplace;
        }
        public void setSuperpowers(List<String> _superpowers){
            this.superpowers= _superpowers;
        }
        public void setReligions(List<String> _religions){
            this.religions= _religions;
        }
        public void setGender(String _gender){
            this.gender = _gender;
        }
        public void setOccupations(List<String> _occupations){
            this.occupation= _occupations;
        }
        public void setMemberof(List<String> _memberof){
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
        public List<String> getTypes(){
            return this.types;
        }
        public List<String> getUniverses(){
            return this.universes;
        }
        public String getBirthplace(){
            return this.birthplace;
        }
        public List<String> getSuperpowers(){
            return this.superpowers;
        }
        public List<String> getReligions(){
            return this.religions;
        }
        public String getGender(){
            return this.gender;
        }
        public List<String> getOccupations(){
            return this.occupation;
        }
        public List<String> getMemberof(){
            return this.memberof;
        }

}
