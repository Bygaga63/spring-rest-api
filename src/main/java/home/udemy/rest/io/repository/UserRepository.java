package home.udemy.rest.io.repository;

import home.udemy.rest.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String id);
}
