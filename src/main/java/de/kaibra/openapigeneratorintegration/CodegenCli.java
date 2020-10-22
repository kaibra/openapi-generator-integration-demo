package de.kaibra.openapigeneratorintegration;


import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;

import java.util.Map;

import static java.util.Map.entry;

public class CodegenCli {

    public static void main(String[] args) {
        assert args.length == 2;
        assert args[0] != null;
        assert args[1] != null;

        CodegenConfigurator codegenConfigurator = new CodegenConfigurator()
                .setGeneratorName("my-custom-template")
                .setInputSpec(args[0])
                .setAdditionalProperties(Map.ofEntries(
                        entry("dateLibrary", "java8"),
                        entry("java8", true)))
                .setInvokerPackage("de.kaibra.client")
                .setApiPackage("de.kaibra.client.api")
                .setModelPackage("de.kaibra.client.model")
                .setLibrary("resttemplate");

        ClientOptInput opts = codegenConfigurator.toClientOptInput();

        new DefaultGenerator().opts(opts).generate();
    }
}
