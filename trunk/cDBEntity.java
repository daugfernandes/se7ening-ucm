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
 * Base class for database entities. Provides common methods for
 * operations as insert, update, delete, etc.
 */
public class cDBEntity
{
    private int _id;
    private String _name;

    /**
     * flag for changes pending save
     */
    protected boolean _changed;
    /**
     * flag for new data (will insert and not update)
     */
    protected boolean _new;
    /**
     * flag for deleted (can use commit after a delete; should expect a new ID)
     */
    protected boolean _deleted;

    /**
     * Parametersless constructor
     */
    public cDBEntity()
    {
        _id=0;
        _name="";
        _changed=true;
        _new=true;
        _deleted=false;
    }

    /**
     * Complete comstructor
     * @param id
     * @param name
     */
    public cDBEntity(int id, String name)
    {
        _id=id;
        _name=name;
        _changed=true;
        _new=true;
        _deleted=false;
    }

    public String getName() { return(_name); }
    public void setName(String newName)
    {
        if(!_name.equals(newName))
            _changed=true;

        this._name=newName;
    }

    public int getId() { return(_id); }
    protected void setId(int value) {_id = value; }

    public boolean hasChanged() { return(_changed); }
    public boolean isNew() { return(_new); }
    public boolean isDeleted() {  return(_deleted); }

    /** Inserts or Updates 'this' record.
     *    on INSERT returns new ID (autonumber);
     *    on Update returns number os rows affected (see MySQL function for more info)
     * @param conn 
     * @return On derivated classes, returns the ID of a new record when _new or the number of rows affected  (usually 1) if !_new
     * @throws Exception
     */
    protected int Save(java.sql.Connection conn) throws Exception
    {
        _new=false;
        _changed=false;
        return(0);
    }

    /** Deletes 'this' record.
     *      Can be "recovered" by simply using Commit function; you should expect a new ID however.
     *      For that matter test for hasChanghed AND isDeleted.
     * @param conn
     * @return On derivated classes, returns the number os rows deleted (usually 1)
     * @throws Exception
     */
    protected int Delete(java.sql.Connection conn) throws Exception
    {
        _deleted=true;
        _changed=true;
        _new=true;
        return(0);
    }

    /**
     * Compares the content
     * @param other
     * @return
     */
    protected boolean isEqual(cDBEntity other, boolean exceptIdField) {
        boolean ret;

        if(!exceptIdField) {
            ret = this.getId() == other.getId();
            if(!ret) return ret;
        }

        return this.getName().equals(other.getName());    }
}

