package java8.ch01.ex09;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {
	void forEachIf(Consumer<T> action, Predicate<T> filter);
}
