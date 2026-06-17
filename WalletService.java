package com.example.demo.service;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.Wallet;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class WalletService {

    private final UserRepository userRepository;

    public WalletService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE USER:
    public AppUser registerUser(AppUser user) {
        // Step 1. Generate Unique Wallet Address
        // UUID gives us a long random string. We take the first 8 chars.
        String generatedAddress = "0x" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Step 2. Create the Wallet Object
        Wallet newWallet = new Wallet();
        newWallet.setWalletAddress(generatedAddress);
        newWallet.setBalance(BigDecimal.ZERO);

        // Step 3. Link Wallet to User
        user.setWallet(newWallet);


        // Step 4. Save Parent
        return userRepository.save(user);
    }

    // ADD FUNDS:
    @Transactional
    public String addFunds(Long userId, BigDecimal amount) {
        Optional<AppUser> userBox = userRepository.findById(userId);

        AppUser user = null;
        if (userBox.isPresent()) {
            user = userBox.get();
        } else {
            throw new RuntimeException("User not found!");
        }

        Wallet wallet = user.getWallet();
        
        // Update balance
        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);

        return "Success! Added $" + amount + ". New Balance: $" + newBalance;
    }

    // DELETE USER
    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User and Wallet deleted successfully.";
        }
        return "User not found.";
    }

    // GET ALL USERS
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}


