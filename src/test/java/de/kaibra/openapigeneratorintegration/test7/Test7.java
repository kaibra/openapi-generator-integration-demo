package de.kaibra.openapigeneratorintegration.test7;

import com.github.javaparser.ast.CompilationUnit;
import de.kaibra.openapigeneratorintegration.utils.java.AbstractJavaCodegenITest;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static de.kaibra.openapigeneratorintegration.utils.TestingUtils.aTestConfigurator;
import static de.kaibra.openapigeneratorintegration.utils.TestingUtils.loadCompilationUnit;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Test7 extends AbstractJavaCodegenITest {

    @Test
    void shouldLoadPetAndOrderCompilationUnits() throws IOException {
        CodegenConfigurator codegenConfigurator = aTestConfigurator(CODEGEN_OUTPUT_DIR);
        ClientOptInput opts = codegenConfigurator.toClientOptInput();

        List<String> paths = new DefaultGenerator().opts(opts).generate().stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());

        String petPath = CODEGEN_OUTPUT_DIR.getAbsolutePath() + "/src/main/java/de/kaibra/client/model/Pet.java";
        String orderPath = CODEGEN_OUTPUT_DIR.getAbsolutePath() + "/src/main/java/de/kaibra/client/model/Order.java";

        assertThat(paths).contains(
                petPath,
                orderPath
        );

        CompilationUnit petCompilationUnit = loadCompilationUnit(petPath);
        CompilationUnit orderCompilationUnit = loadCompilationUnit(orderPath);

        AssertionsForClassTypes.assertThat(petCompilationUnit.toString()).contains("\"Pet\" is a class with a short name");
        AssertionsForClassTypes.assertThat(orderCompilationUnit.toString()).contains("\"Order\" is a class with a long name");
    }

}
