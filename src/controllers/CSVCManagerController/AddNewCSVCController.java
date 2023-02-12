/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.CSVCManagerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import services.MysqlConnection;
import models.CSVCModel;

/**
 *
 * @author hoan
 */
public class AddNewCSVCController {
    public boolean addNewCSVC(CSVCModel csvc) throws SQLException, ClassNotFoundException{
        Connection connection = MysqlConnection.getMysqlConnection1();
        String query = "INSERT INTO csvc (loai, soLuong, trangThai)"
                        + "values(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, csvc.getLoai());
        preparedStatement.setInt(2, csvc.getSoLuong());
        preparedStatement.setString(3, csvc.getTrangThai());
        
        preparedStatement.executeUpdate();
        connection.close();
        return true;
    }
    
    public boolean updateCSVC(CSVCModel csvc) throws SQLException, ClassNotFoundException{
        Connection connection = MysqlConnection.getMysqlConnection1();
        String query = "UPDATE csvc "
                        +"SET soLuong = " + Integer.toString(csvc.getSoLuong()) + ", "
                        +"trangThai = '" + csvc.getTrangThai() + "' "
                        +"WHERE loai = '" + csvc.getLoai() + "'";
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        connection.close();
        return true;
    }
}
