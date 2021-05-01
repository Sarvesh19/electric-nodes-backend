package com.electricnodes.dto;

/* Copyright 2021 freecodeformat.com */

/* Time: 2021-04-21 10:51:16 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
public class Address {

    private String city;
    private String zip;
    private String street;
    private String number;
    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setZip(String zip) {
         this.zip = zip;
     }
     public String getZip() {
         return zip;
     }

    public void setStreet(String street) {
         this.street = street;
     }
     public String getStreet() {
         return street;
     }

    public void setNumber(String number) {
         this.number = number;
     }
     public String getNumber() {
         return number;
     }

}