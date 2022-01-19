package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import com.github.nicedfx.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void dbTest() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (resultSet.next()) {
                groups.add(new GroupData().withId(resultSet.getInt("group_id"))
                        .withName(resultSet.getNString("group_name"))
                        .withHeader(resultSet.getString("group_header"))
                        .withFooter(resultSet.getNString("group_footer")));
            }

            System.out.println(groups);

            resultSet.close();
            st.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
