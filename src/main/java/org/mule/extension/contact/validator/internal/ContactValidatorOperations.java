package org.mule.extension.contact.validator.internal;

import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;

import org.mule.extension.contact.validator.internal.error.ContactValidatorErrorTypeProvider;
import org.mule.extension.contact.validator.internal.error.NotNumberError;
import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.AnyType;
import org.mule.metadata.api.model.MetadataFormat;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.stereotype.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class ContactValidatorOperations {


  private static final Logger LOGGER = LoggerFactory.getLogger(ContactValidatorOperations.class);

  public static final String STRING_IS_NOT_A_NUMBER = "The supplied string is not a number";
  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
   * @return
   */
  @Alias("is-string-a-number")
  @Validator
  @Throws(ContactValidatorErrorTypeProvider.class)
  public void isStringNumber( @Expression(SUPPORTED) String expression,
                              @Optional String message ) {
    if ( ! onlyDigits(expression)) {
      throw new NotNumberError(message);
    }
  }

    /**
   *  Check if character is digit from 0-9 then return true else false
   */
  private boolean onlyDigits(String str) {
    if (str == null) return false;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) < '0'
              || str.charAt(i) > '9') {
        return false;
      }
    }
    return true;
  }
}