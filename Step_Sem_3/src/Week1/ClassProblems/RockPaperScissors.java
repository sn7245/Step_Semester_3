package Week1.ClassProblems;
import java.util.*;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove))
            return "Draw";

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        int n = 5;
        int wins = 0, losses = 0, draws = 0;

        String[] player = new String[n];
        String[] computer = new String[n];
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            player[i] = sc.next();

            computer[i] = moves[r.nextInt(3)];
            result[i] = playRound(player[i], computer[i]);

            if (result[i].equals("Player Wins"))
                wins++;
            else if (result[i].equals("Computer Wins"))
                losses++;
            else
                draws++;
        }

        System.out.println("\n-------------------------------------------------------");
        System.out.printf("%-8s %-15s %-17s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");
        System.out.println("-------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.printf("%-8d %-15s %-17s %-15s%n",
                    i + 1, player[i], computer[i], result[i]);
        }

        double winPercentage = (wins * 100.0) / n;

        System.out.println("-------------------------------------------------------");
        System.out.println("Wins   : " + wins);
        System.out.println("Losses : " + losses);
        System.out.println("Draws  : " + draws);
        System.out.printf("Win %%  : %.1f%%%n", winPercentage);

        sc.close();
    }
}