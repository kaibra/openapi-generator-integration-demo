# openapi-generator-integration-demo

This is a small demo to show 
* how [openapi-generator](https://github.com/OpenAPITools/openapi-generator) works
* how [openapi-generator](https://github.com/OpenAPITools/openapi-generator) can be tested from unit to integration (testcontainers)

## How the demo works
By fixing the tests `1` to `7` a small feature is implemented which just adds a custom comment to the top of the generated `Java` classes.

```Java
package de.kaibra.client.model;

public class Pet {
}

public class Order {
}
```

becomes

```Java
package de.kaibra.client.model;

/** "Pet" is a class with a short name **/
public class Pet {
}

/** "Order" is a class with a long name **/
public class Order {
}
```



-----

Kai Brandes, October 2020


