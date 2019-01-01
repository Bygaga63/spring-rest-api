package home.udemy.rest.io.repository;

import home.udemy.rest.io.entity.AddressEntity;
import home.udemy.rest.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    Iterable<AddressEntity> findAllByUserDetails(UserEntity userEntity);
    AddressEntity findByAddressId(String address);
}
