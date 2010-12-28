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

// TODO: Qualified names for java.sql.*

/**
 *
 * @author David Fernandes
 */
public class cProject extends cDBEntity {

    private java.util.List<cCientificArea> _cientificareas;
    private java.util.List<cTeacher> _teachers;

    /**
     * Parameterless constructor
     */
    public cProject(){
        super();
        _cientificareas= new java.util.ArrayList();
        _teachers = new java.util.ArrayList();
    }

    /**
     * Complete constructor
     * @param id
     * @param nome
     */
    public cProject(int id, String nome){
        super(id,nome);
    }

    /**
     * "FromDB" constructor
     * @param id
     * @param conn Database already open connection
     * @throws Exception
     */
    public cProject(int id, java.sql.Connection conn) throws Exception
    {
        super(id,"");

        String simpleProc = "SELECT NAME FROM PROJECT WHERE ID=?";
        java.sql.PreparedStatement ps = conn.prepareStatement(simpleProc);

        ps.setInt(1, id);
        java.sql.ResultSet res=ps.executeQuery( );

        if(res.next())
            super.setName(res.getString(1));

    }

    @Override protected int Save(java.sql.Connection conn) throws Exception
    {

        try {
            // TODO: não é nada disto
            for(int i=0;i<_cientificareas.size();i++)
                ((cCientificArea)_cientificareas.get(i)).Save(conn);

            for(int i=0;i<_teachers.size();i++)
                ((cTeacher)_teachers.get(i)).Save(conn);

            // and .....................

	    super.Save(conn);

            return(getId());

        }
        catch(Exception e)
        {
            return(0);
        }

    }

    /**
     * Add a cientifica area do the project
     * @param ac
     * @return true on success, false otherwise
     */
    public boolean addAreaCientifica(cCientificArea ac) {

         try{
            _cientificareas.add(ac);
            return true;
         } catch(Exception e){
            return false;
         }
     }

    /**
     * Search for a cientific area base on its unique identifier
     * @param id
     * @return a project's cientificArea with the specified id
     */
    public cCientificArea getCientificAreaById(int id){
        for(int i=0;i<_cientificareas.size();i++)
            if(((cCientificArea)_cientificareas.get(i)).getId()==id)
                return (cCientificArea)_cientificareas.get(i);

        return null;
    }

    /**
     * Getter for Cientificareas List
     * @return
     */
    public java.util.List<cCientificArea> getCientificAreas() {
        return _cientificareas;
    }

    /**
     * Add a teacher to the project
     * @param ac
     * @return true onj success, false otherwise
     */
    public boolean addTeacher(cTeacher ac) {

         try{
            _teachers.add(ac);
            return true;
         } catch(Exception e){
            return false;
         }
    }

     /**
      * Search for a teacher based on its unique identifier
      * @param id
      * @return a project's teacher with the specified id
      */
     public cTeacher getTeacherById(int id){
         for(int i=0;i<_teachers.size();i++)
             if(((cTeacher)_teachers.get(i)).getId()==id)
                 return (cTeacher)_teachers.get(i);

         return null;
     }

     /**
      * Getter for Teachers List
      * @return
      */
     public java.util.List<cTeacher> getTeachers() {
        return _teachers;
    }

     /**
      * Compares the content (ID included)
      * @param other cProject
      * @param exceptIdField Flag for not consider the ID as an Equal condition.
      * @return
      */
     public boolean isEqual(cProject other, boolean exceptIdField) {

        boolean ret = super.isEqual(other, exceptIdField);
        ret = ret && this.getCientificAreas().size()==other.getCientificAreas().size();
        ret = ret && this.getTeachers().size()==other.getTeachers().size();

        if(ret) {

            for(int i=0;i<this.getCientificAreas().size();i++) {
                if(_cientificareas.get(i).isEqual(other.getCientificAreas().get(i), exceptIdField)) {
                    return false;
                }
            }

            for(int i=0;i<this.getTeachers().size();i++) {
                if(_teachers.get(i).isEqual(other.getTeachers().get(i), exceptIdField)) {
                    return false;
                }
            }
        }

        return ret;

     }

}
