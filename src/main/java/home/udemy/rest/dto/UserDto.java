package home.udemy.rest.dto;

import java.io.Serializable;

public class UserDto {
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVarificationToken;
    private String emailVarificationStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVarificationToken() {
        return emailVarificationToken;
    }

    public void setEmailVarificationToken(String emailVarificationToken) {
        this.emailVarificationToken = emailVarificationToken;
    }

    public String getEmailVarificationStatus() {
        return emailVarificationStatus;
    }

    public void setEmailVarificationStatus(String emailVarificationStatus) {
        this.emailVarificationStatus = emailVarificationStatus;
    }
}
