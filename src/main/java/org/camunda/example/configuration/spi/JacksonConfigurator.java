package org.camunda.example.configuration.spi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.camunda.spin.spi.DataFormatConfigurator;

public class JacksonConfigurator implements DataFormatConfigurator<JacksonJsonDataFormat> {
//
//  public JacksonConfigurator() {
//  }

  @Override
  public Class<JacksonJsonDataFormat> getDataFormatClass() {
    return JacksonJsonDataFormat.class;
  }

  @Override
  public void configure(JacksonJsonDataFormat jacksonJsonDataFormat) {
    // This is not needed for JSR310.
    // But, while you're at it, you might as well configure it.
    // It allows Spin to (de)serialize complex JSON TypedVariable variables, e.g.:
//    Map<String,List<SomeDto>> theVariable = new HashMap<>();
//    jacksonJsonDataFormat.addTypeDetector(MapJacksonJsonTypeDetector.INSTANCE);

    // This part is needed to register Jackson JSR310 and Joda datatypes.
    ObjectMapper mapper = jacksonJsonDataFormat.getObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.registerModule(new JodaModule());
  }
}