package org.camunda.example.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.example.model.ExampleDto;

import java.time.LocalDate;
import java.util.Map;

public class SetVariables implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //create typed variable with json as serialization format
        Map<String, Object> vars = Variables.createVariables();
        ExampleDto exampleDto = new ExampleDto("prop1", "shouldBeIgnored", "prop3",
                LocalDate.now(), org.joda.time.LocalDate.now());
        ObjectValue typedObjectValue = Variables.objectValue(exampleDto)
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create();
        vars.put("variable", typedObjectValue);
        delegateExecution.setVariables(vars);
    }
}
