package org.mule.extension.contact.validator.internal.error;

import static java.util.Optional.of;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;
import org.mule.runtime.extension.api.error.MuleErrors;

import java.util.Optional;


public enum ContactValidationErrorType implements ErrorTypeDefinition<ContactValidationErrorType> {

    /**
     * Indicates that a validation failure occurred
     */
    VALIDATION(MuleErrors.VALIDATION),

    NOT_NUMMBER(VALIDATION);

    private ErrorTypeDefinition<?> parentErrorType;

    ContactValidationErrorType(ErrorTypeDefinition<?> parentErrorType) {
        this.parentErrorType = parentErrorType;
    }

    @Override
    public Optional<ErrorTypeDefinition<? extends Enum<?>>> getParent() {
        return of(parentErrorType);
    }

}
