package util;

import java.util.ArrayList;
import java.util.List;

public class CollectionLocalUtils {

	public static List<Integer> cloneList(List<Integer> toClone) {
		final List<Integer> cloned = new ArrayList<Integer>();
		for(final Integer i : toClone) {
			cloned.add(new Integer(i));
		}

		return cloned;
	}

}
