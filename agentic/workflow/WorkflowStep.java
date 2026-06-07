package agentic.workflow;

import agentic.workflow.llm.StructuredOutput;
import agentic.workflow.llm.SchemaType;

public class WorkflowStep {
    private String name;
    private String prompt;
    private String systemPrompt;
    private StructuredOutput structuredOutput;

    public WorkflowStep(String name, String prompt, String systemPrompt, StructuredOutput structuredOutput){
        if(name.equals("") || name.equals(" ") || 
            prompt.equals("") || prompt.equals(" ") ||
            systemPrompt.equals("") || systemPrompt.equals(" ") ||
            structuredOutput == null) throw new IllegalArgumentException("a `name`, `prompt` és `systemPrompt` nem lehet üres, a `structuredOutput` pedig nem lehet `null`.");
        
        this.name = name;
        this.prompt = prompt;
        this.systemPrompt = systemPrompt;
        this.structuredOutput = structuredOutput;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        if(!name.equals("") && !name.equals(" ")){
            this.name = name;
        }
    }

    public String getPrompt(){
        return this.prompt;
    }

    public void setPrompt(String prompt){
        if(!prompt.equals("") && !prompt.equals(" ")){
            this.prompt = prompt;
        }
    }

    public String getSystemPrompt(){
        return this.systemPrompt;
    }

    public void setSystemPrompt(String systemPrompt){
        if(!systemPrompt.equals("") && !systemPrompt.equals(" ")){
            this.systemPrompt = systemPrompt;
        }
    }

    public StructuredOutput getStructuredOutput(){
        return this.structuredOutput;
    }

    public void setStructuredOutput(StructuredOutput structuredOutput){
        if(structuredOutput != null){
            this.structuredOutput = structuredOutput;
        }
    }

    public boolean expectsStructuredOutput(){
        if(this.structuredOutput.size() > 0){ return true; }
        return false;
    }

    public String simulateResponse(){
        switch(this.structuredOutput.getSchemaTypes()[0]){
            case SchemaType.INT:
                return "0";
            case SchemaType.STRING:
                return "SAMPLE";
            case SchemaType.BOOLEAN:
                return "true";
            case SchemaType.LIST_INT:
                return "[1,2,3]";
            case SchemaType.LIST_STRING:
                return "[\"a\",\"b\"]";
            case SchemaType.MAP_STRING_STRING:
                return "{\"kulcs\":\"érték\"}";
        }
        return "";
    }
}