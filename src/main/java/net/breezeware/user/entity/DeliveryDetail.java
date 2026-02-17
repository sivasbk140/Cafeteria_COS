package net.breezeware.user.entity;

public class DeliveryDetail {

    private int id;
    private int userId;
    private String email;
    private String address;
    private String phone;
    private String createdOn;
    private String updatedOn;

    public DeliveryDetail() {
    }

    public DeliveryDetail(int id, int userId, String address,
                          String phone,
                          String createdOn, String updatedOn) {
        this.id        = id;
        this.userId    = userId;
        this.address   = address;
        this.phone     = phone;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    // ─── Getters ─────────────────────────────────────────────────

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    // ─── Setters ─────────────────────────────────────────────────

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
// ─── toString ────────────────────────────────────────────────

    @Override
    public String toString() {
        return "DeliveryDetail{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }


}
