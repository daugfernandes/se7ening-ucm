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

import java.lang.Class;

/**
 *
 * @author David Fernandes
 */
public class cGeneral {

    /**
     *
     */
    public static class cConfig {
       
        /**
         *
         */
        public String server, database, username, password, driver, port, url;

        cConfig(){}

        public @Override String toString()
        {
            return("server:"+this.server+"\nport:"+port+"\ndatabase:"+this.database+"\nuser:"+this.username+"\npassword:"+"********"+"\ndriver:"+driver);
        }
    }

    /**
     * Get config information from a xml file
     * @param configxml Xml file from which to get the config info
     * @return cConfig object from configxml
     */
    public static cConfig getConfig(String configxml)
    {
        cConfig c=new cConfig();

        s7ucm.utilities.cXMLReader xr=new s7ucm.utilities.cXMLReader(configxml);
        org.w3c.dom.Element rootElement = xr.rootElement;
        org.w3c.dom.Node nodeDatabase = xr.getNode(rootElement, "database");
        c.driver=xr.getNode(nodeDatabase, "driver").getTextContent();
        c.server=xr.getNode(nodeDatabase, "server").getTextContent();
        c.database=xr.getNode(nodeDatabase, "name").getTextContent();
        c.username=xr.getNode(nodeDatabase, "username").getTextContent();
        c.password=xr.getNode(nodeDatabase, "password").getTextContent();
        c.port=xr.getNode(nodeDatabase, "port").getTextContent();
        c.url=xr.getNode(nodeDatabase, "url").getTextContent();

        return(c);
    }

    /**
     * Test function for a driver instalation
     * @param conf
     * @return trus if the driver is present; false otherwise
     */
    public static boolean testDriverInstalation(cConfig conf) {
        try
        {
            String className = conf.driver;
            Class driverObject = Class.forName(className);
            return(true);
        }
        catch (Exception e)
        {
            return(false);
        }
    }
}
