package org.mule.extension.contact.validator.internal;

import static org.mule.runtime.api.meta.ExpressionSupport.REQUIRED;
import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;

import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.AnyType;
import org.mule.metadata.api.model.MetadataFormat;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class ContactValidatorOperations {

  public static final String STRING_IS_NOT_A_NUMBER = "The supplied string is not a number";
  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
   * @return
   */
  @Alias("is-string-a-number")
  @MediaType(value = MediaType.ANY, strict = false)
  @OutputResolver(output = PassthroughMetadataResolver.class )
  public Result<Object, Map<String, Map<String, String>>> isStringNumber(@Placement(order = 1, tab="Advanced") @Optional(defaultValue="#[payload]") @Expression(REQUIRED) TypedValue<Object> content,
                                                                         @Placement(order = 2, tab="Advanced") @Expression(REQUIRED) @Optional(defaultValue="#[attributes.errorList]") Map<String, String> validationFailures,
                                                                         @Expression(SUPPORTED) String expression,
                                                                         @Optional String message,
                                                                         String fieldName) {
    if (validationFailures == null )
      validationFailures = new LinkedHashMap<>();

    if ( onlyDigits(expression)) {
      validationFailures.put( fieldName, (message != null ? message : STRING_IS_NOT_A_NUMBER) + " [" + expression + "]" );
    }

    Map<String,Map<String,String>> att = new LinkedHashMap<>();
    att.put("errorList",validationFailures);

    return Result.<Object, Map<String, Map<String,String>>>builder()
            .output(content.getValue())
            .mediaType(content.getDataType().getMediaType())
            .attributes(att)
            .build();
  }

  public static class PassthroughMetadataResolver implements OutputTypeResolver<Object> {

    private static final AnyType ANY_TYPE = BaseTypeBuilder.create(MetadataFormat.JAVA).anyType().build();

    @Override
    public String getCategoryName() {
      return "ValidatorTransform";
    }

    @Override
    public MetadataType getOutputType(MetadataContext context, Object key) {
      return ANY_TYPE;
    }
  }

    /**
   *  Check if character is digit from 0-9 then return true else false
   */
  private boolean onlyDigits(String str) {
    if (str == null) return false;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) < '0'
              && str.charAt(i) > '9') {
        return false;
      }
    }
    return true;
  }
}