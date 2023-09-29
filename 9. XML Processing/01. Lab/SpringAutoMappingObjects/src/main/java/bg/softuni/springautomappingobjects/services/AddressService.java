package bg.softuni.springautomappingobjects.services;

import bg.softuni.springautomappingobjects.entities.Address;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.AddressDTO;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.CreateAddressDTO;

public interface AddressService {

    AddressDTO create(CreateAddressDTO data);

}
