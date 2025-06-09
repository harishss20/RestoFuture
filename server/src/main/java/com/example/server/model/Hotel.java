package com.example.server.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String HotelName;
    private String ownerName;
    private String email;
    private String oauthProvider;
    private String oauthId;
    private LocalDateTime createdAt= LocalDateTime.now();

    public Hotel() {
    }

    public Hotel(Long id, String hotelName, String ownerName, String email, String oauthProvider, String oauthId, LocalDateTime createdAt) {
        this.id = id;
        HotelName = hotelName;
        this.ownerName = ownerName;
        this.email = email;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOauthProvider() {
        return oauthProvider;
    }

    public void setOauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
