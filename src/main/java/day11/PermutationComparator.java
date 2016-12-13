package day11;

import java.util.Comparator;

public class PermutationComparator implements Comparator<Byte[]> {

	@Override
	public int compare(Byte[] o1, Byte[] o2) {
		final boolean o1IsUp = o1[3] == Main.UP;
		final boolean o2IsUp = o2[3] == Main.UP;
		
		if (o1IsUp && !o2IsUp) {
			return 1;
		} else if(!o1IsUp && o2IsUp) {
			return -1;
		} else if(o1IsUp) { //both are up, pick one with 2 values
			return o1[2] != Main.NONE ? -1 : 1;
		} else if(!o1IsUp) { //both are down, pick one with 1 values
			return o2[2] != Main.NONE ? 1 : -1;
		} else {
			return 0;
		}
	}

}
