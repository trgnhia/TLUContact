package com.example.tlucontact.entity;

//Lớp đơn vị với các thuộc tính : Mã , Tên , Số điện thoại , Địa chỉ , Hình ảnh.
public class DBDV {
    private String Id, Name , PhoneNumber , Address;
    private String SymbolImage;

    public DBDV() {
    }

    public DBDV(String id, String name, String address, String phoneNumber, String symbolImage) {
        Address = address;
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        SymbolImage = symbolImage;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getSymbolImage() {
        return SymbolImage;
    }

    public void setSymbolImage(String symbolImage) {
        SymbolImage = symbolImage;
    }
}
