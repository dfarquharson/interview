package lab.dsjf.interview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class InterviewTest {

    protected final Interview interview;

    public InterviewTest(Interview interview) {
        this.interview = interview;
    }

    @TestFactory
    @DisplayName("We can map over integers")
    Stream<DynamicTest> testFactory_a19376bf83a4400b830f8976092bb241() {
        record TestCase<A, B>(
                List<A> xs
                , Function<A, B> f
                , List<B> expectedOutput
        ) {
        }
        return Stream.of(
                new TestCase<>(
                        List.of(1, 2, 3)
                        , x -> x + 1
                        , List.of(2, 3, 4))
                , new TestCase<>(
                        List.of(1, 2, 3, 4)
                        , x -> x * 2
                        , List.of(2, 4, 6, 8))
                , new TestCase<>(
                        List.of(1, 2, 3, 4)
                        , x -> Double.valueOf(Math.pow(x, 2)).intValue()
                        , List.of(1, 4, 9, 16))
        ).map(input -> DynamicTest.dynamicTest(input.toString(), () ->
                assertThat(interview.map(input.xs, input.f))
                        .isEqualTo(input.expectedOutput)));
    }

    @TestFactory
    @DisplayName("We can map over strings")
    Stream<DynamicTest> testFactory_19314a218c2846b288fdc4dd54362900() {
        record TestCase<A, B>(
                List<A> xs
                , Function<A, B> f
                , List<B> expectedOutput
        ) {
        }
        return Stream.of(
                new TestCase<>(
                        List.of("a", "b", "c")
                        , x -> x.toUpperCase()
                        , List.of("A", "B", "C"))
                , new TestCase<>(
                        List.of("omg", "wow", "nice")
                        , String::toUpperCase
                        , List.of("OMG", "WOW", "NICE"))
        ).map(input -> DynamicTest.dynamicTest(input.toString(), () ->
                assertThat(this.interview.map(input.xs, input.f))
                        .isEqualTo(input.expectedOutput)));
    }

    @TestFactory
    @DisplayName("We can filter integers")
    Stream<DynamicTest> testFactory_7a59dc3b825a4a3d99245f998051e72c() {
        record TestCase<A>(
                List<A> xs
                , Function<A, Boolean> f
                , List<A> expectedOutput
        ) {
        }
        return Stream.of(
                new TestCase<>(
                        List.of(1, 2, 3, 4)
                        , x -> x % 2 == 0
                        , List.of(2, 4))
                , new TestCase<>(
                        IntStream.range(0, 10).boxed().toList()
                        , x -> x % 2 == 1
                        , List.of(1, 3, 5, 7, 9))
        ).map(input -> DynamicTest.dynamicTest(input.toString(), () ->
                assertThat(this.interview.filter(input.xs, input.f))
                        .isEqualTo(input.expectedOutput)));
    }

    @Test
    @DisplayName("We can reduce a list of things to a single thing")
    void test_3819d196c96f4ceab15a35f2c2eda683() {
        assertThat(this.interview.reduce(
                List.of(1, 2, 3)
                , (a, b) -> a + b // Integer::sum would be a "nicer" way to say it, but somewhat defeats the pedagogic purpose
                , 0))
                .isEqualTo(6);
    }

    @Test
    @DisplayName("We can reduce a list of things to another list of things")
    void test_d180acdf43d5456e8dd1c542d2dfdcec() {
        assertThat(this.interview.reduce(
                List.of(1, 2, 3)
                , (a, b) -> Stream.concat(
                                b.stream(),
                                Stream.of(a + 1))
                        .toList()
                , List.of()))
                .isEqualTo(List.of(2, 3, 4));
    }

}
