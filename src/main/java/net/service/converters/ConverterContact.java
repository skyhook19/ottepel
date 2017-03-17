package net.service.converters;

import net.domain.contacts.Contact;
import net.dto.ContactDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterContact {


    public List<ContactDto> convertToContactDto(List<Contact> contacts) {
        return contacts.stream().map(this::convertToContactDto).collect(Collectors.toList());
    }

    public ContactDto convertToContactDto(Contact contact) {
        return ContactDto.builder()
                .age(contact.getAge())
                .comment(contact.getComment())
                .dob(contact.getDob())
                .gender(contact.getGender())
                .interests(contact.getInterests())
                .lastName(contact.getLastName())
                .login(contact.getLogin())
                .name(contact.getName())
                .parents(contact.getParents())
                .phoneNumber(contact.getPhoneNumber())
                .sourceOfCapital(contact.getSourceOfCapital())
                .build();
    }
}
