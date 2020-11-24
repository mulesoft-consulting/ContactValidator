package org.mule.extension.contact.validator.internal.error;

import org.mule.runtime.extension.api.exception.ModuleException;

public class NotNumberError extends ModuleException {


    /**
     * Creates a new instance
     *
     * @param message  the exception description
     */
    public NotNumberError(String message) {
        super(message, ContactValidationErrorType.NOT_NUMMBER );

    }

}
