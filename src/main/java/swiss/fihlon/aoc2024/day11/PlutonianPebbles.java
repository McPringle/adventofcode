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
package swiss.fihlon.aoc2024.day11;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class PlutonianPebbles {

    @NotNull
    private final String puzzleInput;

    public PlutonianPebbles(@NotNull final String puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    public long solve(final int timesBlinking) {
        var stoneData = parseData();
        for (int i = 0; i < timesBlinking; i++) {
            stoneData = blink(stoneData);
        }

        return stoneData.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    @NotNull
    private Map<String, Long> blink(final Map<String, Long> stoneData) {
        final var result = new HashMap<String, Long>();
        for (var data : stoneData.entrySet()) {
            final var numbers = applyRules(data.getKey());
            for (var number : numbers) {
                final var count = result.getOrDefault(number, 0L) + data.getValue();
                result.put(number, count);
            }
        }
        return result;
    }

    @NotNull
    private String[] applyRules(@NotNull final String number) {
        if (number.equals("0")) {
            return new String[] {"1"};
        } else if (number.length() % 2 == 0) {
            var half = number.length() / 2;
            var left = number.substring(0, half);
            var right = Long.valueOf(number.substring(half)).toString();
            return new String[] {left, right};
        } else {
            return new String[] {Long.toString(Long.parseLong(number) * 2024)};
        }
    }

    @NotNull
    private Map<String, Long> parseData() {
        return Arrays.stream(puzzleInput.split("\\s+"))
                .collect(Collectors.groupingByConcurrent((s -> s), Collectors.counting()));
    }

}
