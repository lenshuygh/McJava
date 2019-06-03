package be.mcjava.dao;

import be.mcjava.model.PreMadeOrderMenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreMadeOrderMenuDao {
    private static final String url = "jdbc:mysql://192.168.99.100/test_db";
    private static final String user = "root";
    private static final String password = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public List<PreMadeOrderMenu> populatePreMadeOrderMenu() throws SQLException {
        String sql = "select * from premade_menu";
        List<PreMadeOrderMenu> preMadeOrderMenuList = new ArrayList<>();

        try (
                PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                PreMadeOrderMenu preMadeOrderMenu = new PreMadeOrderMenu();
                preMadeOrderMenu.setName(resultSet.getString("name"));
                preMadeOrderMenu.setPrice(resultSet.getBigDecimal("price"));
                preMadeOrderMenu.setpictureName(resultSet.getString("graphic_name"));
                preMadeOrderMenuList.add(preMadeOrderMenu);
            }
            return preMadeOrderMenuList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}