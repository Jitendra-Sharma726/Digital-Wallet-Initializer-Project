package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String walletAddress;

    // Using BigDecimal is best practice for money
    private BigDecimal balance;

    // CONSTRUCTORS:
    public Wallet() {}

    public Wallet(String walletAddress, BigDecimal balance) {
        this.walletAddress = walletAddress;
        this.balance = balance;
    }

    // GETTERS AND SETTERS:
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getWalletAddress() { 
        return walletAddress; 
    }
    public void setWalletAddress(String walletAddress) { 
        this.walletAddress = walletAddress; 
    }
    
    public BigDecimal getBalance() { 
        return balance; 
    }
    public void setBalance(BigDecimal balance) { 
        this.balance = balance; 
    }
}


