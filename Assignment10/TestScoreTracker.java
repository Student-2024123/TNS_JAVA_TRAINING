package Assignment10;

import java.util.ArrayList;

public class TestScoreTracker {
    private int[] scoresArray;

    public TestScoreTracker(int size) {
        this.scoresArray = new int[size];
    }

    public int[] getScoresArray() {
        return scoresArray;
    }

    public void convertAndDisplay() {
        ArrayList<Integer> scoreList = new ArrayList<>();

        for (int score : scoresArray) {
            scoreList.add(score);
        }

        System.out.println("Elements in the ArrayList:");
        for (int score : scoreList) {
            System.out.println(score);
        }
    }
}
