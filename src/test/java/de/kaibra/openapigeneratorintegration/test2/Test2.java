package de.kaibra.openapigeneratorintegration.test2;

import de.kaibra.openapigeneratorintegration.config.MyCodegenConfig;
import de.kaibra.openapigeneratorintegration.helpers.ClassDescriptionHelper;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openapitools.codegen.CodegenModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Test2 {

    @Test
    void shouldAddVendorExtensionForEachModel() {
        // WHEN
        MyCodegenConfig custom = new MyCodegenConfig();

        CodegenModel someModel = custom.fromModel("someModel", new Schema());

//        Assert.assertNotNull(custom.getClassDescriptionHelper());
        ClassDescriptionHelper helperMock = mock(ClassDescriptionHelper.class);
//        custom.setClassDescriptionHelper(helperMock);

        Assert.assertTrue(someModel.getVendorExtensions()
                .containsKey("myCustomComment"));

        Assert.assertNotNull(someModel.getVendorExtensions()
                .get("myCustomComment"));

        verify(helperMock).getCommentFor("someModel");
    }
}
