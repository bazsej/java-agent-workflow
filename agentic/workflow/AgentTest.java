package agentic.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import agentic.workflow.llm.*;

public class AgentTest{
    @Test
    public void testStepCount(){
        Agent agent = new Agent("Test");
        agent.addStep(new WorkflowStep("a", "b", "c", new StructuredOutput(SchemaType.INT)));

        assertEquals(agent.getStepCount(), 1);
    }
    @Test
    public void testAddDuplicateStepRejected(){
        Agent agent = new Agent("Test");
        WorkflowStep step = new WorkflowStep("a", "b", "c", new StructuredOutput(SchemaType.INT));
        agent.addStep(step);

        assertThrows(IllegalArgumentException.class, () -> agent.addStep(step));
    }

    @Test
    public void findStepByName(){
        Agent agent = new Agent("Test");
        WorkflowStep step = new WorkflowStep("a", "b", "c", new StructuredOutput(SchemaType.INT));
        agent.addStep(step);

        assertEquals(step, agent.findStepByName("a"));
    }

    @Test
    public void findStepByNameMissing(){
        Agent agent = new Agent("Test");
        assertEquals(null, agent.findStepByName("a"));
    }
    
    @Test
    public void testLoadAgentSuccess(){
        assertDoesNotThrow(() -> Agent.loadAgent("successful.txt"));
    }

    @Test
    public void testLoadAgentRejectsMissingHeader(){
        assertThrows(WorkflowFormatException.class, () -> Agent.loadAgent("missingheader.txt"));
    }

    @Test
    public void testLoadAgentRejectsDuplicateStepNames(){
        assertThrows(WorkflowFormatException.class, () -> Agent.loadAgent("duplicate.txt"));
    }
}