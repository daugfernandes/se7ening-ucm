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
 * Teacher class used as both 'teacher' and as 'tutor'
 * @author david
 */
public class cTeacher extends cDBEntity
{

    private String          _email;
    private String          _phone;
    private cCientificArea  _cientificArea;
    private String          _schedule;

    /**
     * Parameterless constructor
     */
    public cTeacher()
    {
        super();
    }

    /**
     * Super-like constructor
     * @param id
     * @param name
     */
    public cTeacher(int id, String name)
    {
        super(id,name);
        _email="";
        _phone="";
        _cientificArea=null;
        _schedule="";
    }

    /**
     * Complete constructor
     * @param id Teacher's unique identifier; autonumber on insert
     * @param name
     * @param email
     * @param phone
     * @param cientificArea
     * @param schedule
     */
    public cTeacher(int id, String name, String email, String phone,
                    cCientificArea cientificArea, String schedule)
    {
        super(id,name);
        
        _email=email;
        _phone=phone;
        _cientificArea=cientificArea;
        _schedule=schedule;

    }

    /**
     * "From DB" constructor
     * @param id Unique identifier
     * @param conn Database open connection
     * @throws Exception
     */
    public cTeacher(int id, java.sql.Connection conn) throws Exception
    {
        super(id,"");

        // TODO: try
        String simpleProc = "SELECT NAME, EMAIL, PHONE, ID_CIENTIFICAREA, SCHEDULE FROM TEACHER WHERE ID=?";
        java.sql.PreparedStatement ps = conn.prepareStatement(simpleProc);

        ps.setInt(1, id);
        java.sql.ResultSet res=ps.executeQuery( );

        if(res.next())
        {
            setName(res.getString(1));
            setEmail(res.getString(2));
            setPhone(res.getString(3));
            setCientificArea(new cCientificArea(res.getInt(4),conn));
            setSchedule(res.getString(5));
        }

    }

    public void setEmail(String value) {
        _changed=true;
        _email=value;
    }

    public String getEmail() { return _email; }

    public void setPhone(String value) {
        _changed=true;
        _phone=value;
    }

    public String getPhone() { return _phone; }

    public void setCientificArea(cCientificArea value) {
        _changed=true;
        _cientificArea=value;
    }

    public cCientificArea getCientificArea() { return _cientificArea; }

    public void setSchedule(String value) {
        _changed=true;
        _schedule=value;
    }

    public String getSchedule() { return _schedule; }

    @Override public int Save(java.sql.Connection conn) throws Exception
    {
        // there is no point in commiting a not changed object
        if(hasChanged())
        {
            String simpleProc;
            java.sql.CallableStatement cs;

            // TODO: try
            if(isNew()) // insert
                simpleProc = "{ ? = call TEACHER_ADD(?,?,?,?,?) }";
            else // update
                simpleProc = "{ ? = call TEACHER_UPD(?,?,?,?,?,?) }";

            cs = conn.prepareCall(simpleProc);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);

            cs.setString(2, getName());
            cs.setString(3, _email);
            cs.setString(4, _phone);

            if(_cientificArea==null)
                cs.setInt(5, 1);
            else
                cs.setInt(5, _cientificArea.getId());

            cs.setString(6, _schedule);

            // last parameter of simpleProc, used only if updating
            // when INSERTING Id gets as auto-number
            if(!isNew()) cs.setInt(7, getId());
            cs.execute();

            // inserting => set de property ID with the new ID just created
            if(isNew()) setId(cs.getInt(1));

            // doesn't do anything DB related, in fact
            // just updates some flags
            super.Save(conn);

            // returns de Id of the new created record
            return(cs.getInt(1));
        }

        return(0);

    }

    /* Deletes 'this' record.
     *      Can be "recovered" by simply using Commit function; you should expect a new ID however.
     *      For that matter test for hasChanged AND isDeleted.
     */
    @Override public int Delete(java.sql.Connection conn) throws Exception
    {
        String simpleProc;
        java.sql.CallableStatement cs;

        // TODO: try
        simpleProc = "{ ? = call TEACHER_DEL(?) }";
        cs = conn.prepareCall(simpleProc);
        cs.registerOutParameter(1, java.sql.Types.INTEGER);

        cs.setInt(2, getId());
        cs.execute();

        super.Delete(conn);

        return(cs.getInt(1));
    }

    /**
     * Compares content
     * @param other
     * @return
     */
    public boolean isEqual(cTeacher other, boolean exceptId) {

        boolean ret;

        if(!exceptId) {
            ret = this.getId() == other.getId();
            if(!ret) return false;
        }

        ret = this.getEmail().equals(other.getEmail());
        ret = ret && this.getName().equals(other.getName());
        ret = ret && this.getPhone().equals(other.getPhone());
        ret = ret && this.getSchedule().equals(other.getSchedule());
        if(!ret) return false;
        
        return this.getCientificArea().isEqual(other.getCientificArea(), true);
    }
}

