package agentic.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import agentic.workflow.llm.*;

public class WorkflowStepTest{
    @Test
    public void testExpectsStructuredOutput(){
        WorkflowStep workflowStep = new WorkflowStep("name", "prompt", "systemprompt", new StructuredOutput(SchemaType.LIST_INT, SchemaType.LIST_STRING));
        assertTrue(workflowStep.expectsStructuredOutput());
    }

    @Test
    public void testSimulateResponseByPrimaryType(){
        WorkflowStep workflowStep = new WorkflowStep("name", "prompt", "systemprompt", new StructuredOutput(SchemaType.LIST_INT, SchemaType.LIST_STRING));
        assertEquals(workflowStep.simulateResponse(), "[1,2,3]");
    }
}