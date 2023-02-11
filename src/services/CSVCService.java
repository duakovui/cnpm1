/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.CSVCModel;

/**
 *
 * @author hoan
 */
public class CSVCService {
    public List<CSVCModel> getListCSVC(){
        List<CSVCModel> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getMysqlConnection1();
            String query = "SELECT * FROM CSVC";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                CSVCModel csvc = new CSVCModel();
                csvc.setLoai(rs.getString("loai"));
                csvc.setSoLuong(rs.getInt("soLuong"));
                csvc.setLoai(rs.getString("trangThai"));
                list.add(csvc);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public List<CSVCModel> search(String keyword){
        List<CSVCModel> list = new ArrayList<>();
        if (keyword.trim().isEmpty()) {
            return this.getListCSVC();
        }
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM CSVC WHERE loai LIKE '%"
                            + keyword
                            + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                CSVCModel csvc = new CSVCModel();
                csvc.setLoai(rs.getString("loai"));
                csvc.setSoLuong(rs.getInt("soLuong"));
                csvc.setLoai(rs.getString("trangThai"));
                list.add(csvc);
            }
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
        return list;
    }
    
    private void exceptionHandle(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
    }
}
