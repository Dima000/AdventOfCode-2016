package day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import util.ReaderLocal;
import util.StringUtilLocal;

public class Main {

	static final List<Pair<Integer, Integer>> chip2Bot = new ArrayList<>();
	static final Map<Integer, Bot> id2Bot = new HashMap<>();

	public static void main(final String[] args) {
		final String[] inputLines = ReaderLocal.readLines("input10.txt");
		for (final String line : inputLines) {
			processInstruction(line);
		}

		assignChips();

		boolean stateFinished = false;
		while (!stateFinished) {
			stateFinished = true;

			for (final Bot bot : id2Bot.values()) {
				if (bot.isFull()) {
					// bot.printBot();
					bot.printOutput();

					final int lowId = bot.getLowTo();
					final int highId = bot.getHighTo();

					if (lowId > 0) {
						id2Bot.get(lowId).addChip(bot.getLowValue());
					}
					if (highId > 0) {
						id2Bot.get(highId).addChip(bot.getHighValue());
					}

					bot.clearArms();

					stateFinished = stateFinished && lowId < 0 && highId < 0;
				}
			}

			System.out.println();
		}

	}

	static void processInstruction(final String instruction) {
		final boolean isValue = instruction.contains("value");
		final String[] numbers = StringUtilLocal.getNumbers(instruction);

		if (isValue) {
			final int chip = Integer.parseInt(numbers[0]);
			final int bot = Integer.parseInt(numbers[1]);
			chip2Bot.add(Pair.of(chip, bot));
		} else {
			final int botId = Integer.parseInt(numbers[0]);
			final Integer lowId = Integer.parseInt(numbers[1]);
			final Integer highId = Integer.parseInt(numbers[2]);

			final int lowTo = findGiveToId(lowId, instruction.contains("low to output"));
			final int highTo = findGiveToId(highId, instruction.contains("high to output"));

			final Bot bot = new Bot(botId, lowTo, highTo);
			id2Bot.put(botId, bot);
		}
	}

	static void assignChips() {
		for (final Pair<Integer, Integer> elem : chip2Bot) {
			id2Bot.get(elem.getValue()).addChip(elem.getKey());
		}
	}

	static int findGiveToId(final int id, final boolean isOutput) {
		if (id == 0 && isOutput) {
			return Bot.OUTPUT_ZERO;
		} else if (isOutput) {
			return -id;
		} else {
			return id;
		}
	}
}
