package com.google.api.pathtemplate;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_PathTemplate_Segment extends PathTemplate.Segment {

  private final PathTemplate.SegmentKind kind;

  private final String value;

  private final String complexSeparator;

  AutoValue_PathTemplate_Segment(
      PathTemplate.SegmentKind kind,
      String value,
      String complexSeparator) {
    if (kind == null) {
      throw new NullPointerException("Null kind");
    }
    this.kind = kind;
    if (value == null) {
      throw new NullPointerException("Null value");
    }
    this.value = value;
    if (complexSeparator == null) {
      throw new NullPointerException("Null complexSeparator");
    }
    this.complexSeparator = complexSeparator;
  }

  @Override
  PathTemplate.SegmentKind kind() {
    return kind;
  }

  @Override
  String value() {
    return value;
  }

  @Override
  String complexSeparator() {
    return complexSeparator;
  }

  @Override
  public String toString() {
    return "Segment{"
        + "kind=" + kind + ", "
        + "value=" + value + ", "
        + "complexSeparator=" + complexSeparator
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PathTemplate.Segment) {
      PathTemplate.Segment that = (PathTemplate.Segment) o;
      return this.kind.equals(that.kind())
          && this.value.equals(that.value())
          && this.complexSeparator.equals(that.complexSeparator());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= kind.hashCode();
    h$ *= 1000003;
    h$ ^= value.hashCode();
    h$ *= 1000003;
    h$ ^= complexSeparator.hashCode();
    return h$;
  }

}
