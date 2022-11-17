package com.ggalmazor.ltdownsampling;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

class Bucket<T extends Point> {
  private final List<T> data;
  private final T first;
  private final T last;
  private final Point center;
  private T result = null;

  private Bucket(List<T> data, T first, T last, Point center, T result) {
    this.data = data;
    this.first = first;
    this.last = last;
    this.center = center;
    this.result = result;
  }

  static <U extends Point> Bucket<U> of(List<U> us) {
    U first = us.get(0);
    U last = us.get(us.size() - 1);
    Point center = first.add(last.subtract(first).half());
    return new Bucket<>(us, first, last, center, first);
  }

  static <U extends Point> Bucket<U> of(U u) {
    return new Bucket<>(Collections.singletonList(u), u, u, u, u);
  }

  T getResult() {
    return result;
  }

  void setResult(T result) {
    this.result = result;
  }

  T getFirst() {
    return first;
  }

  T getLast() {
    return last;
  }

  Point getCenter() {
    return center;
  }

  <U> List<U> map(Function<T, U> mapper) {
    return data.stream().map(mapper).collect(toList());
  }
}
