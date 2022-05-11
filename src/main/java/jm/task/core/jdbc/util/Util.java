package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.ArrayList;

public class Util {

    public ArrayList<String> getMySqlOptions() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("root");
        result.add("Sesh2001");
        result.add("jdbc:mysql://localhost:3306/kata");
        return result;
    }
}
