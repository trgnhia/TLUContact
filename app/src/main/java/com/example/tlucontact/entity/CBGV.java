package com.example.tlucontact.entity;

// Lớp giảng viên với các thuộc tính : Mã , Tên , Chức vụ , Số điện thoại , Email , Tên đơn vị , Hình ảnh.
public class CBGV {
    private String Id, Name , Position , PhoneNumber,Email , WordUnit,avatar;
    private int AvatarImage;
    public CBGV() {
    }

    public CBGV(String avatar, String email, String id, String name, String phoneNumber, String position, String wordUnit) {
        avatar = avatar;
        Email = email;
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        Position = position;
        WordUnit = wordUnit;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAvatarImage() {
        return AvatarImage;
    }

    public void setAvatarImage(int avatarImage) {
        AvatarImage = avatarImage;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getWordUnit() {
        return WordUnit;
    }

    public void setWordUnit(String wordUnit) {
        WordUnit = wordUnit;
    }
}
