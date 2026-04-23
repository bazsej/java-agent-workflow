package agentic.workflow;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;
import java.util.List;


public class Agent{
    private String name;
    private List<WorkflowStep> steps;

    public Agent(String name){
        if(name == null || name.equals(" ") || name.equals(""))
            throw new IllegalArgumentException("a név nem lehet `null`, üres vagy csak szóközökből álló.");
        this.name = name;
    }

    public void addStep(WorkflowStep step){
        if(step == null) throw new IllegalArgumentException();
        for(WorkflowStep s : this.steps){
            if(s.getName().equals(step.getName())) throw new IllegalArgumentException();
        }
        this.steps.add(step);
    }

    public WorkflowStep findStepByName(String stepName){
        if(stepName == null || stepName.equals("") || stepName.equals(" "))
            throw new IllegalArgumentException("a lépés neve nem lehet `null`, üres vagy csak szóközökből álló.");
        for(WorkflowStep step : this.steps){
            if(stepName.equals(step.getName())) return step;
        }
        return null;
    }
}