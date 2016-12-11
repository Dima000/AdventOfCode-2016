package day10;

public class Bot {

	public static int OUTPUT_ZERO = -90000;

	private final int id;
	private Integer arm1;
	private Integer arm2;
	private final Integer lowTo;
	private final Integer highTo;

	Bot(final int id, final Integer lowTo, final Integer highTo) {
		this.id = id;
		this.lowTo = lowTo;
		this.highTo = highTo;
	}

	void addChip(final int chipValue) {
		if (arm1 == null) {
			arm1 = chipValue;
		} else {
			arm2 = chipValue;
		}
	}

	boolean isFull() {
		return arm1 != null && arm2 != null;
	}

	void clearArms() {
		arm1 = null;
		arm2 = null;
	}

	int getLowValue() {
		return Math.min(arm1, arm2);
	}

	int getHighValue() {
		return Math.max(arm1, arm2);
	}

	public Integer getLowTo() {
		return lowTo;
	}

	public Integer getHighTo() {
		return highTo;
	}

	public int getId() {
		return id;
	}

	public void printOutput() {
		if (lowTo < 0) {
			System.out.print("[" + -lowTo + ";" + getLowValue() + "]\t");
		}

		if (highTo < 0) {
			System.out.print("[" + -highTo + ";" + getHighValue() + "]\t");
		}
	}

	public void printBot() {
		System.out.print("Bot " + id + " [" + getLowValue() + ";" + getHighValue() + "]\t");
	}

}
