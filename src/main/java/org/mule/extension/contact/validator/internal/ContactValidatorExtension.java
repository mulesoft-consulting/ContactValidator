package org.mule.extension.contact.validator.internal;

import org.mule.extension.contact.validator.internal.error.ContactValidationErrorType;
import org.mule.extension.contact.validator.internal.error.NotNumberError;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "contact-validator")
@Extension(name = "Contact Validator")
@Configurations(ContactValidatorConfiguration.class)
@ErrorTypes(ContactValidationErrorType.class)
public class ContactValidatorExtension {

}
