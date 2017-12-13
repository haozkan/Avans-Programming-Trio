package model;

public class Account {
    private int abboNumber;
    private String name;
    private String streetName;
    private int houseNumber;
    private String postcode;
    private String city;

    public Account(){

    }

    public int getAbboNumber(){
        return this.abboNumber;
    }

    public void setAbboNumber(int number){
        this.abboNumber = number;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getStreetName(){
        return this.streetName;
    }

    public void setStreetName(String name){
        this.streetName = name;
    }

    public int getHouseNumber(){
        return this.houseNumber;
    }

    public void setHouseNumber(int number){
        this.houseNumber = number;
    }

    public String getPostcode(){
        return this.postcode;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
    }

    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city=city;
    }
}
