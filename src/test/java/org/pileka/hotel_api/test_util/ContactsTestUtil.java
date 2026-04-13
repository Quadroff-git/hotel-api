package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.Contacts;
import org.pileka.hotel_api.dto.ContactsDTO;

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
}
