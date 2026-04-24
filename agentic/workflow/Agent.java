package agentic.workflow;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Agent{
    private String name;
    private final List<WorkflowStep> steps = new ArrayList<WorkflowStep>();

    public Agent(String name){
        if(name == null || name.equals(" ") || name.equals(""))
            throw new IllegalArgumentException("a név nem lehet `null`, üres vagy csak szóközökből álló.");
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<WorkflowStep> getSteps(){
        return List.copyOf(steps);
    }

    public void setName(String name){
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
        stepName = stepName.strip();
        if(stepName == null || stepName.equals("") || stepName.equals(" "))
            throw new IllegalArgumentException("a lépés neve nem lehet `null`, üres vagy csak szóközökből álló.");
        for(WorkflowStep step : this.steps){
            if(stepName.equals(step.getName())) return step;
        }
        return null;
    }

    public int getStepCount(){
        return this.steps.size();
    }
    
    public void run(){
        for(WorkflowStep step : steps){
            System.out.println(step.simulateResponse());
        }
    }

    public static Agent loadAgent(String filename) throws IOException, WorkflowFormatException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));){
            String line = reader.readLine();
            if(line == null) throw new WorkflowFormatException("File is empty");
            String[] parts = line.split(":", 2);
            if(parts.length < 2 || !"AGENT".equals(parts[0].trim())) throw new WorkflowFormatException();
            String name = parts[1].trim();
            Agent newAgent = new Agent(name);
            
            while((line = reader.readLine()) != null){
                if(line.isEmpty()) continue;
                if("STEP".equals(line.trim())){
                    WorkflowStep newStep = parseStep(reader);
                    if(newAgent.findStepByName(newStep.getName()) != null) throw new WorkflowFormatException("A step with this name already exists");
                    newAgent.addStep(newStep);
                } else{
                    throw new WorkflowFormatException("Wrong format!");
                }
            }
            System.err.println(newAgent.getStepCount());
            return newAgent;
        } catch(FileNotFoundException e){
            System.err.println("File not found.");
            throw e;
        }
    }

    private static WorkflowStep parseStep(BufferedReader reader) throws IOException, WorkflowFormatException {
        HashMap<String, String> stepData = new HashMap<String, String>();
        ArrayList<String> validSteps = new ArrayList<String>();
        validSteps.add("name");
        validSteps.add("prompt");
        validSteps.add("systemPrompt");
        validSteps.add("output");
        String line;
        while(!"ENDSTEP".equals((line = reader.readLine()))){
            if(line == null || line.isEmpty() || " ".equals(line)) throw new IOException("A lepesben meghiusult a fajlolvasas");
            String[] parts = line.split("=");
            if(!validSteps.contains(parts[0].strip()) || stepData.containsKey(parts[0].strip())) throw new WorkflowFormatException("Ervenytelen adat a lepesben.");
            stepData.put(parts[0].strip(), parts[1].strip());
        }
        return new WorkflowStep(
            stepData.get("name"),
            stepData.get("prompt"),
            stepData.get("systemPrompt"),
            new StructuredOutput(SchemaType.valueOf(stepData.get("output"))));
    }
}