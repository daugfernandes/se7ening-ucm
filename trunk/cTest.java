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

package s7ucm.test;

public class cTest {
    
    private String s;

    public cTest(){
	setS("");
    }

    public cTest(String p_s){
	setS(p_s);
    }

    public String getS() { return s;}
    public void setS(String p_value) { s=p_value;}
}
