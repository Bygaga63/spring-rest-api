package home.udemy.rest.ui.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDetailsRequestModel implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressRequestModel> addresses;
}
