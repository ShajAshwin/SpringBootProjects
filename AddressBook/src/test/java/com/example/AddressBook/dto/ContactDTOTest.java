package com.example.AddressBook.dto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ContactDTOTest {

    private  ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private  Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidContactDTO() {
        ContactDTO contactDTO = ContactDTO.build("John", 10L, "john@example.com", "Chennai");

        Set<ConstraintViolation<ContactDTO>> violations = validator.validate(contactDTO);
        for (ConstraintViolation<ContactDTO> violation : violations) {
            System.out.println("Validation error: " + violation.getPropertyPath() + " - " + violation.getMessage());
        }

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidContactDTO() {
        ContactDTO contactDTO = ContactDTO
                .build("",1234567890L,"invalid-email",null);


        assertFalse(validator.validate(contactDTO).isEmpty());
    }
}
