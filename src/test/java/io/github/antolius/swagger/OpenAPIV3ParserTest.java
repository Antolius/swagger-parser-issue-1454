package io.github.antolius.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.BDDAssertions.then;

public class OpenAPIV3ParserTest {

    private static final String GIVEN_SPEC_PATH = "src/test/resources/openapi.json";
    private static final String GIVEN_LOCATION = new File(GIVEN_SPEC_PATH).getAbsolutePath();

    private OpenAPIV3Parser openAPIV3Parser;

    @Before
    public void setUp() {
        openAPIV3Parser = new OpenAPIV3Parser();
    }

    @Test
    public void shouldParseDefaultForEnumSchema() {
        // when
        OpenAPI openAPI = openAPIV3Parser.read(GIVEN_LOCATION);

        // then
        then(openAPI.getComponents()
                .getSchemas()
                .get("Enum")
                .getDefault()
        ).isNull();
    }

    @Test
    public void shouldParseDefaultForEnumWithDefaultSchema() {
        // when
        OpenAPI openAPI = openAPIV3Parser.read(GIVEN_LOCATION);

        // then
        then(openAPI.getComponents()
                .getSchemas()
                .get("EnumWithDefault")
                .getDefault()
        ).isEqualTo("ENUM_DEFAULT");
    }

    @Test
    public void shouldParseDefaultForSchemaWithEnum() {
        // when
        OpenAPI openAPI = openAPIV3Parser.read(GIVEN_LOCATION);

        // then
        then(openAPI.getComponents()
                .getSchemas()
                .get("SchemaWithEnum")
                .getDefault()
        ).isNull();
    }

    @Test
    public void shouldParseDefaultForSchemaWithDefaultAndEnum() {
        // when
        OpenAPI openAPI = openAPIV3Parser.read(GIVEN_LOCATION);

        // then
        then(openAPI.getComponents()
                .getSchemas()
                .get("SchemaWithDefaultAndEnum")
                .getDefault()
        ).isEqualTo("SCHEMA_DEFAULT");
    }

    @Test
    public void shouldParseDefaultForSchemaWithEnumWithDefault() {
        // when
        OpenAPI openAPI = openAPIV3Parser.read(GIVEN_LOCATION);

        // then
        then(openAPI.getComponents()
                .getSchemas()
                .get("SchemaWithEnumWithDefault")
                .getDefault()
        ).isEqualTo("ENUM_DEFAULT");
    }

    @Test
    public void shouldParseDefaultForSchemaWithDefaultAndEnumWithDefault() {
        // when
        OpenAPI openAPI = openAPIV3Parser.read(GIVEN_LOCATION);

        // then
        then(openAPI.getComponents()
                .getSchemas()
                .get("SchemaWithDefaultAndEnumWithDefault")
                .getDefault()
        ).isEqualTo("SCHEMA_DEFAULT");
    }

}
