package net.service;

import net.dao.DaoContact;
import net.dto.ContactDto;
import net.service.converters.ConverterContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final DaoContact daoContact;
    private final ConverterContact converterContact;

    @Autowired
    public ContactService(DaoContact daoContact, ConverterContact converterContact) {
        this.daoContact = daoContact;
        this.converterContact = converterContact;
    }

    public List<ContactDto> getAllContact() {
        return converterContact.convertToContactDto(daoContact.findAll());
    }
}
