package com.hedeen.john.app01.dao.util;

import com.hedeen.john.app01.dao.DBConnection;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DBHelper {

    public static void initDB() {
        try {
            Statement st = DBConnection.getConnection().createStatement();
            ResourceBundle appRB = ResourceBundle.getBundle("application");
            Scanner sc = new Scanner(Paths.get(appRB.getString("app.initdb")));
            StringBuilder sb = new StringBuilder();

            while(true) {
                if(!sc.hasNextLine()) {
                    return;
                }
                String line = sc.nextLine();
                if(line.trim().endsWith(";")) {
                    line = line.trim();
                    line = line.substring(0, line.length() - 1);
                    sb.append(line);
                    st.execute(sb.toString());
                    sb = new StringBuilder();
                } else {
                    sb.append(line).append(" ");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void showResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for(int i = 1; i <= columnCount; i++) {
            if(i > 1) {
                System.out.print(", ");
            }
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while(rs.next()) {
            for(int i = 1; i <= columnCount; i++) {
                if(i > 1) {
                    System.out.print(", ");
                }
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
    }
}
