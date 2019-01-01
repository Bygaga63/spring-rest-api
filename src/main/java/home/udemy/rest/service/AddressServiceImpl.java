package home.udemy.rest.service;

import home.udemy.rest.io.entity.AddressEntity;
import home.udemy.rest.io.entity.UserEntity;
import home.udemy.rest.io.repository.AddressRepository;
import home.udemy.rest.io.repository.UserRepository;
import home.udemy.rest.shared.dto.AddressDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    @Override
    public List<AddressDto> getAddresses(String userId) {
        List<AddressDto> returnValue = new ArrayList<>();

        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) return returnValue;

        ModelMapper modelMapper = new ModelMapper();
        Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
        for (AddressEntity address : addresses) {
            returnValue.add(modelMapper.map(address, AddressDto.class));
        }
        return returnValue;
    }

    @Override
    public AddressDto getAddress(String addressId) {
        AddressDto returnValue = new AddressDto();

        ModelMapper modelMapper = new ModelMapper();
        AddressEntity address = addressRepository.findByAddressId(addressId);

        modelMapper.map(address, returnValue);

        return  returnValue;
    }
}
