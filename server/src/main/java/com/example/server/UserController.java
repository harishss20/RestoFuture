package com.example.server;

import com.example.server.model.Hotel;
import com.example.server.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    HotelRepository hotelRepository;

    public UserController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> loginUser(@RequestBody Hotel hotel) {
        System.out.println("✅ Hotel data received from frontend: " + hotel);
        return ResponseEntity.ok("Login received");
    }


}
