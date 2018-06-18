/*
 * Copyright 2016-2018 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.opentracing;

import java.util.Map;
import java.util.UUID;
import org.junit.Test;

public class SpanContextTest {

  @Test
  public void testUnwrap() {
    new SpanImpl().context().unwrap(SpanContextImpl.class).getId();
  }

  static class SpanContextImpl implements SpanContext {
    private final String uuid = UUID.randomUUID().toString();

    @Override
    public Iterable<Map.Entry<String, String>> baggageItems() {
      return null;
    }

    @Override
    public <T extends SpanContext> T unwrap(Class<T> c) {
      if (SpanContext.class.isAssignableFrom(c)) {
        //noinspection unchecked
        return (T) this;
      } else {
        throw new ClassCastException("Expected the class " + c.getName() + " to be a SpanContext.");
      }
    }

    public String getId() {
      return this.uuid;
    }
  }

  static class SpanImpl implements Span {

    @Override
    public SpanContext context() {
      return new SpanContextImpl();
    }

    @Override
    public Span setTag(String key, String value) {
      return null;
    }

    @Override
    public Span setTag(String key, boolean value) {
      return null;
    }

    @Override
    public Span setTag(String key, Number value) {
      return null;
    }

    @Override
    public Span log(Map<String, ?> fields) {
      return null;
    }

    @Override
    public Span log(long timestampMicroseconds, Map<String, ?> fields) {
      return null;
    }

    @Override
    public Span log(String event) {
      return null;
    }

    @Override
    public Span log(long timestampMicroseconds, String event) {
      return null;
    }

    @Override
    public Span setBaggageItem(String key, String value) {
      return null;
    }

    @Override
    public String getBaggageItem(String key) {
      return null;
    }

    @Override
    public Span setOperationName(String operationName) {
      return null;
    }

    @Override
    public void finish() {

    }

    @Override
    public void finish(long finishMicros) {

    }
  }
}
