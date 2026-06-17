# Digital-Wallet-Initializer-Project

# Digital Wallet Initializer Project

## Project Description

### Overview

The Digital Wallet Initializer is a Spring Boot-based backend application designed for a FinTech platform where every newly registered user automatically receives a unique digital wallet. The project demonstrates how to implement **One-to-One entity relationships**, **automatic resource creation**, **data integrity constraints**, and **transaction management** using Spring Boot, Spring Data JPA, and H2 Database.

In financial systems, consistency and ownership are critical. A wallet cannot exist without an owner, and every user must have exactly one wallet. This project enforces these rules through JPA mappings, cascading operations, and database constraints.

---

## Business Problem

When a user signs up for a digital payment platform, the system should automatically:

1. Create the user account.
2. Generate a unique wallet address.
3. Create a wallet with an initial balance of zero.
4. Link the wallet to the user.
5. Save both entities together.

Similarly, when a user account is deleted, the associated wallet should also be removed automatically to prevent orphaned financial records.

---

## Core Features

### User Registration

* Create new user accounts.
* Automatically generate a wallet for every user.
* Assign a unique wallet address.
* Initialize wallet balance to zero.

### Wallet Management

* Maintain a one-to-one relationship between users and wallets.
* Store wallet balances.
* Support adding funds to existing wallets.

### Account Lifecycle Management

* Automatically delete wallets when users are removed.
* Prevent orphan wallet records.

### Data Integrity

* Enforce unique wallet addresses.
* Prevent null wallet addresses.
* Ensure every wallet belongs to exactly one user.

---

## Architecture

The project follows a standard layered architecture:

### Entity Layer

#### AppUser

Represents the account holder.

Attributes:

* ID
* Username
* Wallet

#### Wallet

Represents financial information.

Attributes:

* ID
* Wallet Address
* Balance

---

### Repository Layer

#### UserRepository

Provides database operations such as:

* Save users
* Retrieve users
* Delete users
* Find user by username

---

### Service Layer

#### WalletService

Contains business logic for:

* User registration
* Wallet generation
* Balance updates
* Account deletion
* Transaction handling

---

### Controller Layer

#### WalletController

Exposes REST APIs for:

* Registering users
* Adding money
* Viewing users
* Deleting accounts

---

## Database Design

### One-to-One Relationship

```text
AppUser
   |
   | One-To-One
   |
Wallet
```

Each user owns exactly one wallet.

Each wallet belongs to exactly one user.

---

## Relationship Configuration

### AppUser → Wallet

```java
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(
    name = "wallet_id",
    referencedColumnName = "id"
)
private Wallet wallet;
```

### Benefits

* Automatic wallet persistence.
* Automatic wallet deletion.
* Simplified lifecycle management.

---

## Wallet Address Generation

During registration, the application automatically generates wallet addresses.

Example:

```text
0xA1B2C3D4
0x8F91ABCD
0x1234EF56
```

Generation strategy:

```java
"0x" +
UUID.randomUUID()
    .toString()
    .substring(0,8)
    .toUpperCase();
```

---

## Database Constraints

### Unique Address

```java
@Column(unique = true, nullable = false)
private String walletAddress;
```

Ensures:

* No duplicate addresses.
* Address is always present.

---

## Transaction Management

Adding money updates financial data.

To ensure consistency:

```java
@Transactional
public void addFunds(Long userId,
                     Double amount)
```

Benefits:

* Atomic balance updates.
* Rollback on failures.
* Consistent wallet state.

---

## REST API Endpoints

### Register User

**POST**

```http
/api/register
```

Request:

```json
{
  "username": "john"
}
```

Response:

```json
{
  "id": 1,
  "username": "john",
  "wallet": {
    "id": 1,
    "walletAddress": "0xAB12CD34",
    "balance": 0.0
  }
}
```

---

### Add Money

**PUT**

```http
/api/add-money/{userId}?amount=500
```

Example:

```http
/api/add-money/1?amount=500
```

Response:

```text
Funds Added Successfully
```

---

### View Users

**GET**

```http
/api/users
```

Returns all registered users with wallet information.

---

### Delete User

**DELETE**

```http
/api/delete/{id}
```

Example:

```http
/ api/delete/1
```

Effect:

```text
User Deleted
Wallet Deleted Automatically
```

---

## Example Workflow

### Registration

```text
New User
    |
    v
Generate Wallet Address
    |
    v
Create Wallet
    |
    v
Balance = 0
    |
    v
Link Wallet to User
    |
    v
Save User
```

---

### Add Funds

```text
Find User
    |
    v
Get Wallet
    |
    v
Current Balance
    |
    v
Add Amount
    |
    v
Save Updated Balance
```

---

### Delete Account

```text
Delete User
    |
    v
Cascade Triggered
    |
    v
Delete Wallet
```

---

## Technologies Used

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Lombok
* Maven

---

## Learning Objectives

By completing this project, developers will learn:

* JPA One-to-One Relationships
* Cascade Operations
* Foreign Key Mapping
* Database Constraints
* UUID-Based Identifier Generation
* Transaction Management
* Constructor-Based Dependency Injection
* Spring Data JPA Repositories
* REST API Development
* Layered Architecture Design

---

## Expected Outcome

Upon completion, the Digital Wallet Initializer will function as a fully operational FinTech backend capable of automatically creating wallets for new users, maintaining strict ownership relationships, enforcing unique wallet addresses, supporting balance updates, and ensuring proper cleanup of financial records through cascading deletions.

---

It is a practical project for learning how entity lifecycle management works in real financial applications where data consistency and ownership are critical.
