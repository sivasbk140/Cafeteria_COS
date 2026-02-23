package net.breezeware.user.entity;

public class DeliveryDetail {

    private int id;
    private int userId;
    private String email;
    private String phoneNumber;  // ← was "phone", now matches DB
    private String location;      // ← was "address", now matches DB
    private String createdOn;
    private String updatedOn;

    public DeliveryDetail() {
    }

    public DeliveryDetail(int id, int userId, String email,
                          String phoneNumber, String location,
                          String createdOn, String updatedOn) {
        this.id          = id;
        this.userId      = userId;
        this.email       = email;
        this.phoneNumber = phoneNumber;
        this.location    = location;
        this.createdOn   = createdOn;
        this.updatedOn   = updatedOn;
    }



    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }



    @Override
    public String toString() {
        return "DeliveryDetail{" +
                "id=" + id +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}