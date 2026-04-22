package agentic.workflow.llm;

import java.util.Arrays;

public class StructuredOutput{
    private final SchemaType[] schemaTypes;
    
    public StructuredOutput(SchemaType... schemaTypes){
        if (schemaTypes.length == 0) throw new IllegalArgumentException("legalább egy sématípust meg kell adni.");
        for(SchemaType type : schemaTypes){
            if(type == null) throw new NullPointerException("a megadott sématípusok között nem lehet `null`.");
        }
        this.schemaTypes = schemaTypes;
    }

    public SchemaType[] getSchemaTypes(){
        return Arrays.copyOf(schemaTypes, schemaTypes.length);
    }

    public boolean contains(SchemaType schemaType){
        for(SchemaType type : schemaTypes){
            if(type.equals(schemaType)) { return true; }
        }
        return false;
    }

    public int size(){
        return schemaTypes.length;
    }
}