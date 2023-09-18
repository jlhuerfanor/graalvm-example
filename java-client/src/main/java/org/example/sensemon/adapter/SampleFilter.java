package org.example.sensemon.adapter;

import java.util.function.Function;

@FunctionalInterface
public interface SampleFilter extends Function<double[], double[]> {
}
