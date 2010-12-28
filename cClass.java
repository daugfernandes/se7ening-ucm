/*
 * 
 *     Copyright (C) 2010  David Fernandes
 * 
 *                         Rua da Quinta Amarela, 60
 *                         4475-663 MAIA
 *                         PORTUGAL
 * 
 *                         <daugfernandes@aim.com>
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package s7ucm.base;

/**
 *
 * @author david
 */
public class cClass extends cDBEntity {
    
    private String _code;
    private int _credits;
    private cDepartment _department;
    private cCientificArea _cientificarea;
    private cTeacher _teacher;
    private cTeacher _tutor;
    private String _abstract;
    private String _content;
    private java.util.List<cStudyMaterial> _biblio_req;
    private java.util.List<cStudyMaterial> _biblio_compl;
    private String _teachingmethod;
    private int _hours_work;
    private int _hours_contact;
    private String _grading;

    private enum bibliography_type {
        required, complementary
    }

    cClass() {
        setBibliography(null, bibliography_type.required);
        setBibliography(null, bibliography_type.complementary);
    }

    void setCode(String value) { _code = value; }
    String getCode() { return _code; }

    void setCredits(int value) { _credits = value; }
    int getCredits() { return _credits; }

    void setDepartment(cDepartment value) { _department = value; }
    cDepartment getDepartment() { return _department; }

    void setCientificArea(cCientificArea value) { _cientificarea = value; }
    cCientificArea getCientificArea() { return _cientificarea; }

    void setTeacher(cTeacher value) { _teacher = value; }
    cTeacher getTeacher() { return _teacher; }

    void setTutor(cTeacher value) { _tutor = value; }
    cTeacher getTutor() { return _tutor; }

    void setAbstract(String value) { _abstract = value; }
    String getAbstract() { return _abstract; }

    void setContent(String value) { _content = value; }
    String getContent() { return _content; }

    void setBibliography(java.util.List<cStudyMaterial> value, bibliography_type btype) {
        if(btype==bibliography_type.required)
            _biblio_req = value;
        else
            _biblio_compl = value;
    }
    
    java.util.List<cStudyMaterial> getBibliography(bibliography_type btype) {
        if(btype==bibliography_type.required)
            return _biblio_req;
        else
            return _biblio_compl;
    }

    void setTeachingMethod(String value) { _teachingmethod = value; }
    String getTeachingMethod() { return _teachingmethod; }

    void setGrading(String value) { _grading = value; }
    String getGrading() { return _grading; }

    void setHoursWork(int value) { _hours_work = value; }
    int getHoursWork() { return _hours_work; }

    void setHoursContact(int value) { _hours_contact = value; }
    int getHoursContact() { return _hours_contact; }

    boolean addBibliography(cStudyMaterial sm, bibliography_type btype) {
        try{
            getBibliography(btype).add(sm);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    cStudyMaterial getBilbiographyById(int id, bibliography_type btype){

        java.util.List<cStudyMaterial> temp=getBibliography(btype);

        for(int i=0;i<temp.size();i++)
            if((temp.get(i)).getId()==id) return temp.get(i);

        return null;
    }
}
