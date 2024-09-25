package lab.dsjf.interview;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Interview {

    <A, B> List<B> map(List<A> xs, Function<A, B> f);

    <A> List<A> filter(List<A> xs, Function<A, Boolean> f);

    <A, B> B reduce(List<A> xs, BiFunction<A, B, B> f, B accumulator);

}
