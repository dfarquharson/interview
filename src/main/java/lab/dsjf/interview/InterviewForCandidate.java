package lab.dsjf.interview;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class InterviewForCandidate implements Interview {
    // TODO: implement the `Interview` interface on this class such that tests in InterviewTest pass

    @Override
    public <A, B> List<B> map(List<A> xs, Function<A, B> f) {
        return List.of();
    }

    @Override
    public <A> List<A> filter(List<A> xs, Function<A, Boolean> f) {
        return List.of();
    }

    @Override
    public <A, B> B reduce(List<A> xs, BiFunction<A, B, B> f, B accumulator) {
        return null;
    }

}
