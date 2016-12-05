package day1;

import java.util.List;
import java.util.Objects;

public class CircularLinkedList<T> {

	private final List<T> states;
	private int current;

	CircularLinkedList(final List<T> states, final int current) {
		this.states = Objects.requireNonNull(states);
		this.current = current;
	}

	public T nextState() {
		current++;

		if (current == states.size()) {
			current = 0;
		}

		return states.get(current);
	}

	public T previousState() {
		current--;

		if (current < 0) {
			current = states.size() - 1;
		}

		return states.get(current);
	}
}
