package agentic.workflow.llm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StructuredOutputFunctionalTest{
    @Test
    public void testContainsExistingType(){
        SchemaType[] schemaTypes = {SchemaType.INT, SchemaType.STRING};
        StructuredOutput structuredOutput = new StructuredOutput(schemaTypes);

        assertTrue(structuredOutput.contains(SchemaType.INT));
        assertTrue(structuredOutput.contains(SchemaType.STRING));
    }

    @Test
    public void testContainsMissingType(){
        SchemaType[] schemaTypes = {SchemaType.INT, SchemaType.STRING};
        StructuredOutput structuredOutput = new StructuredOutput(schemaTypes);

        assertFalse(structuredOutput.contains(SchemaType.LIST_INT));
        assertFalse(structuredOutput.contains(SchemaType.LIST_STRING));
    }
    
    @Test
    public void testSize(){
        StructuredOutput structuredOutput = new StructuredOutput(SchemaType.INT, SchemaType.STRING);

        assertEquals(structuredOutput.size(), 2);
    }
}