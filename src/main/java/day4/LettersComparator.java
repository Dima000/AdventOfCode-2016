package day4;

import java.util.Comparator;
import java.util.Objects;

import org.apache.commons.lang3.tuple.Pair;

public class LettersComparator implements Comparator<Pair<String,Integer>>{

	@Override
	public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
		if(Objects.equals(o1.getValue(), o2.getValue())) {
			return o1.getKey().compareTo(o2.getKey());
		}

		return o1.getValue() > o2.getValue() ? -1 : 1;
	}

}
