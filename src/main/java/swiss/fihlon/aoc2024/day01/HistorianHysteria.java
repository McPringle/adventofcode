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
package swiss.fihlon.aoc2024.day01;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class HistorianHysteria {

    private final @NotNull List<Integer> leftList;
    private final @NotNull List<Integer> rightList;

    public HistorianHysteria(@NotNull final List<Integer> leftList, @NotNull final List<Integer> rightList) {
        this.leftList = leftList;
        this.rightList = rightList;
    }

    public long totalDistance() {
        long totalDistance = 0;

        final var leftOrdered = leftList.stream().sorted().toList();
        final var rightOrdered = rightList.stream().sorted().toList();

        for (int i = 0; i < leftList.size(); i++) {
            final var smallestLeft = leftOrdered.get(i);
            final var smallestRight = rightOrdered.get(i);
            final var distance = Math.abs(smallestLeft - smallestRight);
            totalDistance += distance;
        }

        return totalDistance;
    }

    public long similarityScore() {
        long similarityScore = 0;

        for (final Integer leftNumber : leftList) {
            final var count = rightList.stream().filter(rightNumber -> rightNumber.equals(leftNumber)).count();
            final var score = leftNumber * count;
            similarityScore += score;
        }

        return similarityScore;
    }

}
