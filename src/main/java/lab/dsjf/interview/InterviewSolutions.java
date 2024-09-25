package lab.dsjf.interview;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class InterviewSolutions implements Interview {

    @Override
    public <A, B> List<B> map(List<A> xs, Function<A, B> f) {
        if (xs.isEmpty()) {
            return List.of();
        } else {
            return Stream.concat(
                    Stream.of(f.apply(xs.getFirst())),
                    map(xs.subList(1, xs.size()), f).stream()
            ).toList();
        }
    }

    @Override
    public <A> List<A> filter(List<A> xs, Function<A, Boolean> f) {
        if (xs.isEmpty()) {
            return List.of();
        } else {
            A first = xs.getFirst();
            return Stream.concat(
                    f.apply(first) ? Stream.of(first) : Stream.<A>of(),
                    filter(xs.subList(1, xs.size()), f).stream()
            ).toList();
        }
    }

    @Override
    public <A, B> B reduce(List<A> xs, BiFunction<A, B, B> f, B accumulator) {
        if (xs.isEmpty()) {
            return accumulator;
        } else {
            return reduce(xs.subList(1, xs.size()), f, f.apply(xs.getFirst(), accumulator));
        }
    }

}
