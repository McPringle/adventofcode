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
package swiss.fihlon.aoc2024.day07;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class BridgeRepair {

    private final Map<Long, List<Long>> puzzle;

    public BridgeRepair(@NotNull final String puzzleInput) {
        this.puzzle = new HashMap<>();
        puzzleInput
                .lines()
                .filter(line -> !line.isBlank())
                .forEach(line -> {
                    var data = line.split(":");
                    var testValue = Long.valueOf(data[0].trim());
                    var numbers = Arrays.stream(data[1].trim().split("\\s+"))
                            .map(Long::valueOf)
                            .toList();
                    this.puzzle.put(testValue, numbers);
                });
    }

    public long part1() {
        long calibrationResult = 0;

        for (var singleEquation : this.puzzle.entrySet()) {
            final var testValue = singleEquation.getKey();
            final var numbers = singleEquation.getValue();
            final var results = new ArrayList<Long>();
            calculateRecursivePart1(numbers, 1, numbers.getFirst(), results);
            for (var result : results) {
                if (result.equals(testValue)) {
                    calibrationResult += result;
                    break;
                }
            }
        }

        return calibrationResult;
    }

    private static void calculateRecursivePart1(
            @NotNull final List<Long> numbers,
            final int index,
            final long currentResult,
            @NotNull final List<Long> results) {

        // if we've processed all numbers, add the result
        if (index == numbers.size()) {
            results.add(currentResult);
            return;
        }

        // recursion: explore both addition and multiplication
        final long nextNumber = numbers.get(index);
        calculateRecursivePart1(numbers, index + 1, currentResult + nextNumber, results);
        calculateRecursivePart1(numbers, index + 1, currentResult * nextNumber, results);
    }

    public long part2() {
        long calibrationResult = 0;

        for (var singleEquation : this.puzzle.entrySet()) {
            final var testValue = singleEquation.getKey();
            final var numbers = singleEquation.getValue();
            calibrationResult += processLine(testValue, numbers);
        }

        return calibrationResult;
    }

    private static long processLine(final long testValue, @NotNull final List<Long> numbers) {
        final Set<Long> results = calculateAllResults(numbers);
        boolean matches = results.contains(testValue);
        return matches ? testValue : 0;
    }

    private static Set<Long> calculateAllResults(@NotNull final List<Long> numbers) {
        final Set<Long> results = new HashSet<>();
        generateResults(numbers, 0, numbers.getFirst(), results);
        return results;
    }

    private static void generateResults(@NotNull final List<Long> numbers,
                                        final int index,
                                        final long currentResult,
                                        @NotNull final Set<Long> results) {
        if (index == numbers.size() - 1) {
            results.add(currentResult);
            return;
        }

        long nextNumber = numbers.get(index + 1);

        // recursion: explore both addition and multiplication
        generateResults(numbers, index + 1, currentResult + nextNumber, results);
        generateResults(numbers, index + 1, currentResult * nextNumber, results);

        // recursion: apply concatenation
        final String concatenated = String.valueOf(currentResult) + nextNumber;
        generateResults(numbers, index + 1, Long.parseLong(concatenated), results);
    }

}
