# 📚 BookInventoryManage - Google Login & Security Flow

This document explains how **Google Login** is handled and how **JWT-based security** is enforced across the BookInventoryManage application.

---

## 🔐 1. Google Login Flow

### ➤ Step-by-step:

1. **Client sends Google login request:**
   - Makes a `POST` request to `/auth/google` with the Google ID Token in the body (`credential`).

2. **Controller receives the request:**
   - `AuthController` handles the endpoint `/auth/google`.
   - Calls `authService.googleLogin(dto)` to authenticate the Google token.

3. **AuthService processes the token:**
   - Initializes `GoogleIdTokenVerifier` using `googleClientId` from `BIConfiguration`.
   - Verifies the Google ID Token:
     ```java
     verifier.verify(credential.getCredential());
     ```
   - If the token is valid:
     - Extracts email from token payload.
     - Checks if the email exists in the system using:
       ```java
       accountRepository.findByEmail(email)
       ```
     - If found:
       - Creates an internal JWT access token:
         ```java
         signAccessToken(account)
         ```
       - Returns the access token to the client.
   - If invalid or user not found:
     - Returns an authentication error.

---

## 🛡️ 2. Authentication & Authority

- `Authentication` Verifys the identity of the user.
- `Authority` Verifys the user's permissions.

### ➤ Step-by-step:

1. **Client sends request with JWT:**
   - Sends a request with the JWT in the `Authorization` header.

2. **Server receives the request:**
   - Filters the request using `BIAuthentication`.

3. **BIAuthentication processes the request:**
   - Extracts JWT from the request.
   - Decodes the JWT using `JwtService`.
   - Checks if the token is valid and not expired.
   - If valid:
     - Extracts user information from the token.
     - Sets the user in the `SecurityContextHolder`.
     - Allows the request to proceed.
   - If invalid:  
     - Returns an authentication error.

---      

## 📦 3. Project Structure
```
   | src
   | enums
   | modules
      | account
         | DTO
         | accountController
         | accountService
      | auth
      | author
   | security
      |BIAuthentication
      |...
   | utils
      | configs
         | ...
   | BookInventoryManageApplication
   ```



