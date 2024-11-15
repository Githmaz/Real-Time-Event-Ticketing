package edu.iit.ticketingservice.config;

import edu.iit.ticketingservice.dao.*;
import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.dto.ticketPackage.TicketPackage;
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

        // Map CustomerEntity to Customer DTO (skip password)
        modelMapper.typeMap(CustomerEntity.class, Customer.class).addMappings(mapper -> mapper.skip(Customer::setPassword));

        // Map VendorEntity to Vendor DTO (skip password)
        modelMapper.typeMap(VendorEntity.class, Vendor.class).addMappings(mapper -> mapper.skip(Vendor::setPassword));

        modelMapper.typeMap(TicketEntity.class, TicketResponse.class).addMappings(mapper -> {
                    mapper.map(src -> src.getEvent().getEventName(), TicketResponse::setEventName);
                    mapper.map(src -> src.getEvent().getEventId(), TicketResponse::setEventId);
                    mapper.map(src -> src.getCustomer().getUserId(), TicketResponse::setCustomerId);
                    mapper.map(src -> src.getTicketPackage().getPackageId(), TicketResponse::setTicketPackageId);
        });

        // Map VendorEntity to Vendor DTO (skip password)
        modelMapper.typeMap(EventEntity.class, Event.class).addMappings(mapper -> mapper.skip(Event::setTicketPackages));


        return modelMapper;
    }
}
