package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    // ONE-TO-ONE MAPPING
    // CascadeType.ALL: If we delete the User, the Wallet is destroyed too.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    // CONSTRUCTORS:
    public AppUser() {}

    public AppUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // GETTERS AND SETTERS:
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }
    
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public Wallet getWallet() { 
        return wallet; 
    }
    public void setWallet(Wallet wallet) { 
        this.wallet = wallet; 
    }
}


