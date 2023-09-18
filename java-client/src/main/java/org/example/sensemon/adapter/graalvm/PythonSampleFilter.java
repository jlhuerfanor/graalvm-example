package org.example.sensemon.adapter.graalvm;

import org.example.sensemon.adapter.SampleFilter;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.stream.LongStream;

public class PythonSampleFilter implements SampleFilter, AutoCloseable {
    private static final String PYTHON = "python";
    private static final String SAMPLE = "sample";
    private static final URL filterScript = PythonSampleFilter.class.getResource("/filter.py");

    private final Context context;

    public PythonSampleFilter() throws IOException {
        this.context = Context.newBuilder(PYTHON)
                .allowAllAccess(true)
                .build();

        context.eval(Source.newBuilder(PYTHON, Objects.requireNonNull(filterScript))
                .cached(true)
                .build());
    }

    @Override
    public double[] apply(double[] doubles) {
        context.getPolyglotBindings().putMember(SAMPLE, doubles);

        var result = context.eval(PYTHON, """
                    import polyglot
                    
                    sample = polyglot.import_value('sample')
                    
                    filter_sample(sample)
                    """);
        if(result.hasArrayElements()) {
            return LongStream.range(0, result.getArraySize())
                    .mapToDouble(i -> result.getArrayElement(i).asDouble())
                    .toArray();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void close() {
        this.context.close();
    }
}
