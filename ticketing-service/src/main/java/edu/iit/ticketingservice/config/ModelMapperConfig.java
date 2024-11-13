package edu.iit.ticketingservice.config;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Vendor;
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
        modelMapper.typeMap(TicketEntity.class, TicketResponse.class).addMappings(mapper -> {
            mapper.map(src -> src.getEvent().getEventName(), TicketResponse::setEventName);
            mapper.map(src -> src.getEvent().getEventId(), TicketResponse::setEventId);
            mapper.map(src -> src.getCustomer().getUserId(), TicketResponse::setCustomerId);
            mapper.map(src-> src.getTicketPackage().getPackageId(),TicketResponse::setTicketPackageId);
        });
        return modelMapper;
    }
}
