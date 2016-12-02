package day1;
import java.util.Objects;

public class Location {

	private final int x;
	private final int y;

	Location(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(final Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Location)) {
			return false;
		}
		final Location loc2 = (Location) o;
		return Objects.equals(x, loc2.getX()) && Objects.equals(y, loc2.getY());
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
