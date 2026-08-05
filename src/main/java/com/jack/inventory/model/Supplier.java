package com.jack.inventory.model;

public class Supplier {

    private int id;
    private String companyName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;

    public Supplier(int id, String companyName, String contactPerson,
                    String phone, String email, String address) {

        this.id = id;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Supplier(String companyName, String contactPerson,
                    String phone, String email, String address) {

        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}