import java.io.*;
import java.util.*;

public class Main {

    private static final List<Player> PLAYERS = List.of(
            new Warrior("Jon Snow",   300, 30, 4, 3),
            new Warrior("The Hound",  400, 20, 6, 5),
            new Mage("Melisandre",    100,  5, 1, 300, 30, 15, 5, 6),
            new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
            new Rogue("Arya Stark",   150, 40, 2, 20),
            new Rogue("Bronn",        250, 35, 3, 50),
            new Hunter("Ygritte",     220, 30, 2, 6)
    );

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: java Main <levels_directory>");
            return;
        }
        String levelsDir = args[0];

        Scanner scanner = new Scanner(System.in);
        GameCallback callback = System.out::println;

        // Show player selection
        System.out.println("Select player:");
        for (int i = 0; i < PLAYERS.size(); i++) {
            System.out.println((i + 1) + ". " + PLAYERS.get(i).description());
        }

        int choice = -1;
        while (choice < 1 || choice > PLAYERS.size()) {
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 and " + PLAYERS.size());
            }
        }

        Player selected = PLAYERS.get(choice - 1);
        System.out.println("You have selected:\n" + selected.getName());

        Game game = new Game(selected, levelsDir, callback);

        // Print initial board
        System.out.println(game.getBoardString());
        System.out.println();
        System.out.println(game.getPlayerStatus());

        while (!game.isOver() && !game.isWon()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            char action = line.charAt(0);

            boolean continues = game.tick(action);

            System.out.println(game.getBoardString());
            System.out.println();
            System.out.println(game.getPlayerStatus());

            if (!continues) break;
        }

        if (game.isWon()) {
            // already printed by game
        } else if (game.isOver()) {
            System.out.println("Game over.");
        }

        scanner.close();
    }
}
