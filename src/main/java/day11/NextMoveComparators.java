package day11;

import java.util.Comparator;

public class NextMoveComparators {

	static final int CHIPS_UP = 0;

	public static NextMoveComparators getComparator(final int type) {
		if (type == CHIPS_UP) {
			return new CHIPS_UP();
		}
		return null;
	}

	class CHIPS_UP extends NextMoveComparators implements Comparator<Byte[]> {
		CHIPS_UP() {
			super();
		}

		@Override
		public int compare(final Byte[] o1, final Byte[] o2) {
			boolean isChip1 = o1[2] < 10;
			boolean isChip2 = o1[4] > 0 && o1[4] < 10;
			boolean isChip3 = o2[2] < 10;;
			boolean isChip4 = o2[4] > 0 && o2[4] < 10;

			if (isChip1 && isChip2) {
				return 1;
			}
			else if (isChip3 && isChip4) {
				return -1;
			}
			else if (isChip1 || isChip2) {
				return 1;
			}
			else if (isChip3 || isChip4) {
				return -1;
			}
			else {
				return 0;
			}
		}

	}

}
