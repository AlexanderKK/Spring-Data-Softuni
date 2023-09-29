package bg.softuni.springautomappingobjects.services;

import bg.softuni.springautomappingobjects.entities.Address;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.springautomappingobjects.repositories.AddressRepository;
import org.modelmapper.ModelMapper;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDTO create(CreateAddressDTO data) {
        ModelMapper mapper = new ModelMapper();

        Address address = mapper.map(data, Address.class);

        Address savedAddress = this.addressRepository.save(address);

        return this.modelMapper.map(savedAddress, AddressDTO.class);
    }

}
