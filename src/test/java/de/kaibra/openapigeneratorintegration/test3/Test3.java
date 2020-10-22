package de.kaibra.openapigeneratorintegration.test3;

import de.kaibra.openapigeneratorintegration.helpers.ClassDescriptionHelper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Test3 {

    private ClassDescriptionHelper myHandlebarsFn;

    @BeforeEach
    void setUp() {
        myHandlebarsFn = new ClassDescriptionHelper();
    }

    @Test
    void shouldRenderAShortName() throws IOException {
        String result = myHandlebarsFn.getCommentFor("Pet");
        Assert.assertEquals(result, "\"Pet\" is a class with a short name");
    }

    @Test
    void shouldRenderALongName() throws IOException {
        String result = myHandlebarsFn.getCommentFor("Order");
        Assert.assertEquals(result, "\"Order\" is a class with a long name");
    }

}
