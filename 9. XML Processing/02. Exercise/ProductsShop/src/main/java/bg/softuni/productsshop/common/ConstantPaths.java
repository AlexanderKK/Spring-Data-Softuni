package bg.softuni.productsshop.common;

import java.nio.file.Path;

public enum ConstantPaths {

    ;
    public static final Path USERS_XML_PATH =
            Path.of("src", "main", "resources", "files", "users.xml");
    public static final Path CATEGORIES_XML_PATH =
            Path.of("src", "main", "resources", "files", "categories.xml");
    public static final Path PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "files", "products.xml");
    public static final Path OUTPUT_PRODUCTS_IN_RANGE_XML_PATH =
            Path.of("src", "main", "resources", "output", "products-in-range.xml");
    public static final Path OUTPUT_USERS_WITH_SOLD_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "output", "users-sold-products.xml");
    public static final Path OUTPUT_CATEGORIES_BY_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "output", "categories-by-products.xml");
    public static final Path OUTPUT_USERS_AND_PRODUCTS_COUNT_XML_PATH =
            Path.of("src", "main", "resources", "output", "users-and-products.xml");

}
