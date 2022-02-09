/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.examples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

/**
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
public class ExampleDto {

  public String property1;
  /**
   * This property should be ignored on serialization.
   */
  @JsonIgnore
  public String property2;
  public String property3;
  public LocalDate localDate;
  public org.joda.time.LocalDate jodaDate;

  public ExampleDto(String property1, String property2, String property3, LocalDate localDate,
      org.joda.time.LocalDate jodaDate) {
    this.property1 = property1;
    this.property2 = property2;
    this.property3 = property3;
    this.localDate = localDate;
    this.jodaDate = jodaDate;
  }

//  public ExampleDto(String property1, String property2, String property3, LocalDate localDate) {
//    this.property1 = property1;
//    this.property2 = property2;
//    this.property3 = property3;
//    this.localDate = localDate;
//  }

  public ExampleDto() {
  }

}