package home.udemy.rest.ui.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDetailsRequestModel implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
