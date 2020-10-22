package de.kaibra.openapigeneratorintegration.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import org.openapitools.codegen.config.CodegenConfigurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Map.entry;

final public class TestingUtils {

    private final static String TARGET_DIR = "target";
    private final static String GENERATED_CODE_DIR = TARGET_DIR + "/generated-code";

    private TestingUtils() {
    }

    public static CodegenConfigurator aTestConfigurator(File testDir) {
        return new CodegenConfigurator()
                .setGeneratorName("my-custom-template")
                .setOutputDir(testDir.getAbsolutePath())
                .setInputSpec("specs/petstore_v3.json")
                .setAdditionalProperties(Map.ofEntries(
                        entry("dateLibrary", "java8"),
                        entry("java8", true)))
                .setInvokerPackage("de.kaibra.client")
                .setApiPackage("de.kaibra.client.api")
                .setModelPackage("de.kaibra.client.model")
                .setLibrary("resttemplate");
    }

    public static CompilationUnit loadCompilationUnit(String filename) throws FileNotFoundException {
        File javaFile = new File(filename);
        return StaticJavaParser.parse(javaFile);
    }

    public static String slurp(String filename) throws IOException {
        File f = new File(filename);
        byte[] bytes = Files.readAllBytes(f.toPath());
        return new String(bytes, UTF_8);
    }

    public static void deleteRecursivly(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteRecursivly(file);
            }
        }
        dir.delete();
    }

    public static JsonNode loadYamlOrJson(final String file) throws IOException {
        try (InputStream in = TestingUtils.class.getClassLoader().getResourceAsStream(file)) {
            if (file.endsWith("yml")) {
                return Yaml.mapper().readTree(in);
            } else {
                return Json.mapper().readTree(in);
            }
        }
    }

    public static void createGeneratedCodeDir() {
        File targetDir = new File(TARGET_DIR);
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }
        File generatedCodeDir = new File(GENERATED_CODE_DIR);
        if (!generatedCodeDir.exists()) {
            generatedCodeDir.mkdir();
        }
    }

    public static File createNewCodegenOutputDir() {
        try {
            createGeneratedCodeDir();
            File outputFolder = File.createTempFile("codegentest-", "-tmp", new File(GENERATED_CODE_DIR));
            outputFolder.delete();
            outputFolder.mkdir();
            return outputFolder;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
