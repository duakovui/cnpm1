/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author hoan
 */
public class CSVCModel {
    private String loai;
    private int soLuong;
    private String trangThai;
    
    public String getLoai(){
        return loai;
    }
    
    public void setLoai(String Loai){
        this.loai = Loai;
    }
    
    public int getSoLuong(){
        return soLuong;
    }
    
    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }
    
    public String getTrangThai(){
        return trangThai;
    }
    
    public void setTrangThai(String trangThai){
        this.trangThai = trangThai;
    }
}
