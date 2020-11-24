package org.mule.extension.contact.validator.internal.error;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashSet;
import java.util.Set;

import static org.mule.extension.contact.validator.internal.error.ContactValidationErrorType.NOT_NUMMBER;

public class ContactValidatorErrorTypeProvider implements ErrorTypeProvider {

    @Override
    public Set<ErrorTypeDefinition> getErrorTypes() {
        Set<ErrorTypeDefinition> errors = new HashSet<>();
        errors.add(NOT_NUMMBER);
        return errors;
    }
}