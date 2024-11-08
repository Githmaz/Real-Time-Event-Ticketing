package edu.iit.ticketingservice.config;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.dto.Vendor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Configure ModelMapper to skip the password field if needed
        modelMapper.typeMap(CustomerEntity.class, Customer.class).addMappings(mapper -> mapper.skip(Customer::setPassword));
        modelMapper.typeMap(VendorEntity.class, Vendor.class).addMappings(mapper -> mapper.skip(Vendor::setPassword));
//        modelMapper.typeMap(AdminEntity.class, Admin.class).addMappings(mapper -> mapper.skip(Admin::setPassword));
        return modelMapper;
    }
}
