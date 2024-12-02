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
package swiss.fihlon.aoc2024.day02;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class RedNosedReports {

    private final List<List<Integer>> reports;

    public RedNosedReports(@NotNull final List<List<Integer>> reports) {
        this.reports = reports;
    }

    public int safeLevels() {
        return analyze(false);
    }

    public int problemDampener() {
        return analyze(true);
    }

    @SuppressWarnings("java:S135") // multiple continue statements
    private int analyze(final boolean enableProblemDampener) {
        int safeLevels = 0;

        for (List<Integer> report : reports) {
            final int problemLevel = check(report);
            if (problemLevel > -1) {
                if (enableProblemDampener) {
                    if (check(removeLevel(report, problemLevel)) > -1
                            && check(removeLevel(report, problemLevel + 1)) > -1) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            safeLevels++;
        }

        return safeLevels;
    }

    private int check(@NotNull final List<Integer> report) {
        boolean increment = isIncrement(report);

        for (int i = 0; i < report.size() - 1; i++) {
            final int actualLevel = report.get(i);
            final int nextLevel = report.get(i + 1);
            final int difference = increment ? nextLevel - actualLevel : actualLevel - nextLevel;
            if (difference < 1 || difference > 3) {
                return i;
            }
        }

        return -1;
    }

    private boolean isIncrement(@NotNull final List<Integer> report) {
        int incrementCounter = 0;
        int decrementCounter = 0;

        for (int i = 0; i < report.size() - 1; i++) {
            final int actualLevel = report.get(i);
            final int nextLevel = report.get(i + 1);
            final int difference = nextLevel - actualLevel;
            if (difference >= 0) {
                incrementCounter++;
            } else {
                decrementCounter++;
            }
        }

        return incrementCounter >= decrementCounter;
    }

    private @NotNull List<Integer> removeLevel(@NotNull final List<java.lang.Integer> report, final int level) {
        final var fixedReport =  new ArrayList<>(report);
        fixedReport.remove(level);
        return fixedReport;
    }

}
