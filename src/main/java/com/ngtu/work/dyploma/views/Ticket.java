package com.ngtu.work.dyploma.views;

import java.util.LinkedList;
import java.util.List;

public class Ticket {
    public String name,description,id, uid;
    public int status;
    public List<String> imageUrls;
    public String address;
    public double latitude,longitude;

    public Ticket(String name, String description, String id,  String address, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = 0;
        this.imageUrls = new LinkedList<>();
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Ticket(String name, String description, String id, int status, List<String> imageUrls) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
        this.imageUrls = imageUrls;
    }

    public Ticket(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status=0;
        imageUrls=new LinkedList<>();
    }

    public Ticket(String name, String description, String id, int status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
        imageUrls=new LinkedList<>();
    }

    public Ticket() {
        status=0;
        imageUrls=new LinkedList<>();
        uid="";
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addUrl(String url){
        this.imageUrls.add(url);
    }
    public void clearUrls(){
        this.imageUrls.clear();
    }
}
