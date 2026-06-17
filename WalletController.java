package com.example.demo.controller;

import com.example.demo.entity.AppUser;
import com.example.demo.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // POST: Register User
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        try {
            return ResponseEntity.ok(walletService.registerUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // PUT: Add Money
    @PutMapping("/add-money/{userId}")
    public ResponseEntity<String> addMoney(@PathVariable Long userId, @RequestParam BigDecimal amount) {
        try {
            return ResponseEntity.ok(walletService.addFunds(userId, amount));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE: Delete Account
    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        return walletService.deleteUser(id);
    }

    // GET: View All Users
    @GetMapping("/users")
    public List<AppUser> getAll() {
        return walletService.getAllUsers();
    }
}


