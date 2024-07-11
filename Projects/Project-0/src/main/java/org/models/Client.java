package org.models;

public class Client {

    private int client_id;
    private String company_name;

    private String point_of_contact;

    private String contact_email;

    private String contact_number;

    private String city;

    private  String address;

    public Client(int client_id, String company_name, String point_of_contact, String contact_email, String contact_number, String city, String address) {
        this.client_id = client_id;
        this.company_name = company_name;
        this.point_of_contact = point_of_contact;
        this.contact_email = contact_email;
        this.contact_number = contact_number;
        this.city = city;
        this.address = address;
    }
    public Client(){}

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPoint_of_contact() {
        return point_of_contact;
    }

    public void setPoint_of_contact(String point_of_contact) {
        this.point_of_contact = point_of_contact;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
