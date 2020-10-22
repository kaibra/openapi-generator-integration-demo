package de.kaibra.openapigeneratorintegration.test1;

import de.kaibra.openapigeneratorintegration.config.MyCodegenConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenConfigLoader;

import static de.kaibra.openapigeneratorintegration.config.MyCodegenConfig.MY_CUSTOM_TEMPLATE;

public class Test1 {

    private MyCodegenConfig custom;

    @BeforeEach
    public void setup() {
        custom = new MyCodegenConfig();
    }

    @Test
    void shouldFindCodegenConfigByTemplateName() {
        CodegenConfig config = CodegenConfigLoader.forName(MY_CUSTOM_TEMPLATE);
        Assert.assertNotNull(config);
    }

    @Test
    void shouldHaveCommonTemplateDir() {
        Assertions.assertEquals(custom.embeddedTemplateDir(), "templates/"+MY_CUSTOM_TEMPLATE);
    }

    @Test
    void shouldHaveTemplateDir() {
        Assertions.assertEquals(custom.templateDir(), "templates/"+MY_CUSTOM_TEMPLATE);
    }

    @Test
    void shouldHaveTemplateName() {
        Assertions.assertEquals(custom.getName(), MY_CUSTOM_TEMPLATE);
    }

}
