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
 * @author david
 */
public class cDepartment extends cDBEntity
{
    /**
     * Parameterless constructor
     */
    public cDepartment()
    {
        super();
    }

    /**
     * Complete constructor
     * @param id
     * @param name
     */
    public cDepartment(int id, String name)
    {
        super(id,name);
    }

    /**
     * "FromDB" constructor
     * @param id
     * @param conn
     * @throws Exception
     */
    public cDepartment(int id, java.sql.Connection conn) throws Exception
    {
        super(id,"");

        String simpleProc = "SELECT NAME FROM DEPARTMENT WHERE ID=?";
        java.sql.PreparedStatement ps = conn.prepareStatement(simpleProc);

        ps.setInt(1, id);
        java.sql.ResultSet res=ps.executeQuery( );

        if(res.next())
            super.setName(res.getString(1));

    }

    @Override protected int Save(java.sql.Connection conn) throws Exception
    {
        // there is no point commiting a not changed object
        if(hasChanged())
        {
            String simpleProc;
            java.sql.CallableStatement cs;

            if(isNew()) // insert
                simpleProc = "{ ? = call DEPARTMENT_ADD(?) }";
            else // update
                simpleProc = "{ ? = call DEPARTMENT_UPD(?,?) }";

            cs = conn.prepareCall(simpleProc);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setString(2, getName());
            if(!isNew()) cs.setInt(3, getId());
            cs.execute();

            super.Save(conn);

            return(cs.getInt(1));
        }

        return(0);

    }

    /* Deletes 'this' record.
     *      Can be "recovered" by simply using Commit function; you should expect a new ID however.
     *      For that matter test for hasChanghed AND isDeleted.
     */
    @Override public int Delete(java.sql.Connection conn) throws Exception
    {
        String simpleProc = "{ ? = call DEPARTMENT_DEL(?) }";
        java.sql.CallableStatement cs = conn.prepareCall(simpleProc);
        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setInt(2, getId());
        cs.execute();

        super.Delete(conn);

        return(cs.getInt(1));
    }

    /**
     * Compares the content (ID included)
     * @param other
     * @return
     */
    public boolean isEqual(cDepartment other, boolean exceptIdField) {
       return super.isEqual(other, exceptIdField);
    }

}

