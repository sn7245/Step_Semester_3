package Week5.ClassProblems;

import java.util.Arrays;

public class PlacementDriveShortlistingRankingEngine {

    static class Candidate implements Comparable<Candidate> {
        private String name;
        private double cgpa;
        private int codingScore;

        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        public static boolean isEligible(double cgpa) {
            return cgpa >= 7.5;
        }

        public static boolean isEligible(double cgpa, int codingScore) {
            return cgpa >= 6.5 && codingScore >= 60;
        }

        public double getCompositeScore() {
            return cgpa * 10 + codingScore;
        }

        @Override
        public int compareTo(Candidate other) {
            return Double.compare(other.getCompositeScore(), this.getCompositeScore());
        }
    }

    public static String shortlistAndRank(Candidate[] candidates) {
        Candidate[] shortlisted = new Candidate[candidates.length];
        int count = 0;

        for (Candidate candidate : candidates) {
            if (Candidate.isEligible(candidate.cgpa) ||
                    Candidate.isEligible(candidate.cgpa, candidate.codingScore)) {
                shortlisted[count++] = candidate;
            }
        }

        shortlisted = Arrays.copyOf(shortlisted, count);
        Arrays.sort(shortlisted);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < shortlisted.length; i++) {
            result.append(i + 1)
                    .append(". ")
                    .append(shortlisted[i].name)
                    .append(" (")
                    .append(shortlisted[i].getCompositeScore())
                    .append(")");

            if (i < shortlisted.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Candidate[] candidates = {
                new Candidate("Aisha", 8.2, 40),
                new Candidate("Rohit", 6.8, 65),
                new Candidate("Meena", 6.0, 90),
                new Candidate("Karan", 7.5, 20)
        };

        System.out.println(shortlistAndRank(candidates));
    }
}