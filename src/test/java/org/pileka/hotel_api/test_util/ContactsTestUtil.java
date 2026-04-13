package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.Contacts;
import org.pileka.hotel_api.dto.ContactsDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@UtilityClass
public class ContactsTestUtil {

    public static Contacts getContacts() {
        return Contacts.builder()
                .phone("+375 17 309-80-00")
                .email("doubletreeminsk.info@hilton.com")
                .build();
    }

    public ContactsDTO getContactsDTO() {
        return ContactsDTO.builder()
                .phone("+375 17 309-80-00")
                .email("doubletreeminsk.info@hilton.com")
                .build();
    }

    public void assertDtoEqualsModel(ContactsDTO dto, Contacts model) {
        if (dto == null && model == null) return;
        assertNotNull(dto, "ContactsDTO should not be null");
        assertNotNull(model, "Contacts model should not be null");
        assertEquals(dto.getPhone(), model.getPhone());
        assertEquals(dto.getEmail(), model.getEmail());
    }

    public void assertModelEqualsDto(Contacts model, ContactsDTO dto) {
        if (model == null && dto == null) return;
        assertNotNull(model, "Contacts model should not be null");
        assertNotNull(dto, "ContactsDTO should not be null");
        assertEquals(model.getPhone(), dto.getPhone());
        assertEquals(model.getEmail(), dto.getEmail());
    }
}
