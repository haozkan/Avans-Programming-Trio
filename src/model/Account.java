package model;

public class Account {

    private int accountNumber;
    private String accountName;
    private String streetname;
    private int houseNumber;
    private String zipcode;
    private String residence;

    public Account() {

    }

    public Account(int accountNumber, String accountName, String streetname, int houseNumber, String zipcode, String residence) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.streetname = streetname;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.residence = residence;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
