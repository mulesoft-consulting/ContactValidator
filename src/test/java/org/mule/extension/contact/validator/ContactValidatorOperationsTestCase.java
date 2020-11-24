package org.mule.extension.contact.validator;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ContactValidatorOperationsTestCase extends MuleArtifactFunctionalTestCase {

    /**
     * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
     */
    @Override
    protected String getConfigFile() {
        return "test-mule-config.xml";
    }

    @Test
    public void executeSayHiOperation() throws Exception {

        Exception ex = null;
        try {
            String payloadValue = ((String) flowRunner("is-string-a-number-flow").run().getMessage().getPayload().getValue());
        } catch (Exception e) {
            ex = e;
        }


        assertThat(ex != null ? ex.getMessage() : "", is("Test for fail."));
    }

}
