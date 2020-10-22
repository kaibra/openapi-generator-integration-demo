package de.kaibra.openapigeneratorintegration.test2;

import de.kaibra.openapigeneratorintegration.config.MyCodegenConfig;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openapitools.codegen.CodegenModel;

public class Test2 {

    @Test
    void shouldAddVendorExtensionForEachModel() {
        // WHEN
        MyCodegenConfig custom = new MyCodegenConfig();

        CodegenModel someModel = custom.fromModel("someModel", new Schema());

        Assert.assertTrue(someModel.getVendorExtensions()
                .containsKey("myCustomComment"));

        Object myCustomComment = someModel.getVendorExtensions().get("myCustomComment");
        Assert.assertNotNull(myCustomComment);
    }
}
