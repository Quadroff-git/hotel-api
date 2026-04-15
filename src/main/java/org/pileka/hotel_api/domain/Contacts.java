package org.pileka.hotel_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contacts {

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;
}
