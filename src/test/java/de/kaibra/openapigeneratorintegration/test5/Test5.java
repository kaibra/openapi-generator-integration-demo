package de.kaibra.openapigeneratorintegration.test5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static de.kaibra.openapigeneratorintegration.utils.TestingUtils.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Test5 {
    private File TEST_DIR;

    @BeforeEach
    void setUp() throws IOException {
        createGeneratedCodeDir();
        TEST_DIR = createNewCodegenOutputDir();
    }

    @Test
    void shouldHavePetAndOrderFilesWithClassDescription() throws IOException {
        CodegenConfigurator codegenConfigurator = aTestConfigurator(TEST_DIR);
        ClientOptInput opts = codegenConfigurator.toClientOptInput();

        List<String> paths = new DefaultGenerator().opts(opts).generate().stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());

        String petPath = TEST_DIR.getAbsolutePath() + "/src/main/java/de/kaibra/client/model/Pet.java";
        String orderPath = TEST_DIR.getAbsolutePath() + "/src/main/java/de/kaibra/client/model/Order.java";

        assertThat(paths).contains(
                petPath,
                orderPath
        );

        assertThat(slurp(petPath)).contains("\"Pet\" is a class with a short name");
        assertThat(slurp(orderPath)).contains("\"Order\" is a class with a long name");

    }

    @AfterEach
    public void cleanup() {
        deleteRecursivly(TEST_DIR);
    }
}
