package home.udemy.rest.shared;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Utils {
    public String generatedUserId(){
        return UUID.randomUUID().toString();
    }
}
