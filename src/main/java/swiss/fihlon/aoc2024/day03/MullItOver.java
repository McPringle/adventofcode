/*
 * Advent of Code - An Advent calendar of small programming puzzles
 * Copyright (C) Marcus Fihlon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package swiss.fihlon.aoc2024.day03;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

public final class MullItOver {

    @NotNull
    private final List<String> instructions;

    public MullItOver(@NotNull final List<String> instructions) {
        this.instructions = instructions;
    }

    public int multiplicationResult() {
        int sum = 0;

        final var mulPattern = Pattern.compile("mul\\((\\d+,\\d+)\\)");
        for (final String instruction : instructions) {
            final var matcher = mulPattern.matcher(instruction);
            while (matcher.find()) {
                final var command = matcher.group();
                final var numbers = command.substring(4, command.length() - 1).split(",");
                sum += Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
            }
        }

        return sum;
    }

    public int conditionalMultiplicationResult() {
        int sum = 0;
        boolean execute = true;

        final var mulPattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d+,\\d+)\\))");
        for (final String instruction : instructions) {
            final var matcher = mulPattern.matcher(instruction);
            while (matcher.find()) {
                final var command = matcher.group();
                switch (command) {
                    case "do()" -> execute = true;
                    case "don't()" -> execute = false;
                    default -> {
                        if (execute) {
                            final var numbers = command.substring(4, command.length() - 1).split(",");
                            sum += Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
                        }
                    }
                }
            }
        }

        return sum;
    }

}
