package home.udemy.rest.io.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "users")
@Data
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(columnDefinition = "boolean default false")
    private Boolean emailVerificationStatus = false;
}
