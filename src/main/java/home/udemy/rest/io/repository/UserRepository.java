package home.udemy.rest.io.repository;

import home.udemy.rest.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String id);
}
