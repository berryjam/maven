package org.apache.maven.wrapper.cli;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPropertiesCommandLineConverter extends AbstractCommandLineConverter<Map<String, String>> {
  protected abstract String getPropertyOption();

  protected abstract String getPropertyOptionDetailed();

  protected abstract String getPropertyOptionDescription();

  public void configure(CommandLineParser parser) {
    CommandLineOption option = parser.option(getPropertyOption(), getPropertyOptionDetailed());
    option = option.hasArguments();
    option.hasDescription(getPropertyOptionDescription());
  }

  protected Map<String, String> newInstance() {
    return new HashMap<String, String>();
  }

  public Map<String, String> convert(ParsedCommandLine options, Map<String, String> properties) throws CommandLineArgumentException {
    for (String keyValueExpression : options.option(getPropertyOption()).getValues()) {
      int pos = keyValueExpression.indexOf("=");
      if (pos < 0) {
        properties.put(keyValueExpression, "");
      } else {
        properties.put(keyValueExpression.substring(0, pos), keyValueExpression.substring(pos + 1));
      }
    }
    return properties;
  }
}
