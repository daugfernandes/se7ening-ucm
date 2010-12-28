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

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The main class of the application.
 */
public class db {

    static Connection connection;

    public static void main(String[] args) throws Exception {

	System.out.println(initDatabase());

    }

    private static boolean initDatabase() throws Exception {

        cGeneral.cConfig conf=cGeneral.getConfig("config.xml");
        boolean DriverOk=cGeneral.testDriverInstalation(conf);

        if(DriverOk)
        {
            String driverName = conf.driver;
            Class.forName(driverName);

            String serverName = conf.server;
            String portNumber= conf.port;
            String mydatabase = conf.database;
            String username = conf.username;
            String password = conf.password;

            String url = conf.url;
            url = url.replace("#SN#", serverName);
            url = url.replace("#PN#", portNumber);
            url = url.replace("#DB#", mydatabase);
            url = url.replace("#UN#", username);
            url = url.replace("#PWD#", password);

            connection = DriverManager.getConnection(url, username, password);

            return(!connection.isClosed());

        }
        else
            return(false);
    }
}
