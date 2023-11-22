import java.util.Scanner;

public class doze {
    final int row_length = 6;                                                        ///number of rows
    final int column_length = 7;                                                     ///number of columns
    static char[][] board;
    static boolean winner = false;
    static int counter = 0;                                                          ///To count the number of rounds
    static int level = 0;
    static int MAX = 1000;
    static int MIN = -1000;

    public doze()                                                                     ///(board full of pieces)
    {                                                                                 ///and equalize the game
        doze.board = new char[row_length][column_length];
        for (int i = 0; i < row_length; i++) {
            for (int j = 0; j < column_length; j++) {
                board[i][j] = '-';                                                    ///initialize "-" to empty cells
            }
        }
    }

    public void print_board() {
        for (int i = 0; i < row_length; i++) {
            System.out.print((row_length - i) + " $ ");                               /// row number
            for (int j = 0; j < column_length; j++) {
                System.out.print(board[i][j]);                                        ///print value of cells
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("    $ $ $ $ $ $ $ \n    1 2 3 4 5 6 7 \n");               ///column number
    }

    public static boolean check_full_colum_set(int column, char player) {
        column--;                                                                     /// because board array
        if (column > 7 || column < 0)                                                 ///start from 0 to 5
        {
            System.out.println("please enter number between 0 < number < 8 ");
            return false;
        }
        else if (is_full(column))                                                  /// is column full of pieces
        {
            System.out.println("please select a column that is not full");
            return false;
        }
         else
         {
            for (int i = 5; i >= 0; i--)                                              /// to find cell between 6 rows
            {
                if (board[i][column] == '-') {
                    board[i][column] = player;
                    break;
                }
            }
            return true;
        }
    }
    static boolean is_full(int column)
    {
        if (board[0][column] != '-')
        {
            return true;
        }
        else
            return false;
    }

    public static char winner_draw(char player) {
        if (counter == 21)                                                            /// draw
        {
            return 'd';
        }
        else //if (counter >= 3)                                                         ///winner
        {
            for (int i = 5; i >= 0; i--)                                             ///horizontal connect4
            {
                for (int j = 0; j < 4; j++)                                          ///we can have 4 connect4 in a row
                {
                    if (board[i][j] == player &&
                        board[i][j + 1] == player &&
                            board[i][j + 2] == player &&
                            board[i][j + 3] == player) {
                        return player;
                    }
                }
            }
            if (board[2][0] != '-' ||                                                /// We must have at least
                board[2][1] != '-' ||                                                /// one piece in a row 4
                    board[2][2] != '-' ||                                                /// to have a connect4
                    board[2][3] != '-' ||                                                /// vertical & diagonal
                    board[2][4] != '-' ||                                                /// that is in row 2 in board
                    board[2][5] != '-' ||
                    board[2][6] != '-') {
                for (int i = 0; i <= 6; i++)                                         ///vertical connect4
                {
                    for (int j = 5; j > 2; j--)                                      ///we can have 3 connect4
                    {                                                                ///in a column
                        if (board[j][i] == player &&
                             board[j - 1][i] == player &&
                                board[j - 2][i] == player &&
                                board[j - 3][i] == player) {
                            return player;
                        }
                    }
                }
                for (int i = 5; i > 2; i--)                                          ///diagonal connect4
                {
                    for (int j = 0; j < 4; j++)                                      /// we can have 4 diagonal
                    {                                                                /// connect4 from left to right
                        if (board[i][j] == player &&                             /// in matrix
                                board[i - 1][j + 1] == player &&
                                board[i - 2][j + 2] == player &&
                                board[i - 3][j + 3] == player) {
                            return player;
                        }
                    }
                    for (int j = 6; j > 2; j--)                                     /// we can have 4 diagonal
                    {                                                                /// connect4 from right to left
                        if (board[i][j] == player &&                             /// in matrix
                                board[i - 1][j - 1] == player &&
                                board[i - 2][j - 2] == player &&
                                board[i - 3][j - 3] == player) {
                            return player;
                        }
                    }
                }
            }
            return 'n';
        } //else
            //return 'n';                                                             /// nothing

    }
    public void minimax()
    {
        int depth = 6;

    }
    public static void main(String[] args) {
        doze test = new doze();
        System.out.println("\n\t\t\t\tWELCOME TO THE GAME CONNECT4(DOZE)\n\n " +
                "Please choose one of the options below \n");
        int incorrect_number = 0;                                                    /// To go around in the loop if
        do                                                                           ///a wrong value is entered
        {
            System.out.println("1. 1player\n2. 2player");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 1:                                                              /// play with computer
                    test.print_board();
                    System.out.println("\ncomputer has a red piece(R) and you have a blue piece(B)\n" +
                            "Please enter the number of the column where you want the piece to be placed");
                    int temp_1player = 0;                                            /// must be 1 to exit the
                    while (temp_1player < 1)                                         /// loop below and finish the game
                    {
                        boolean is_move_player = false;                            /// is player move?
                        do                                                           /// player1 move if player1
                        {                                                            ///  chose a correct column
                            System.out.println("\nturn you(B)");                 ///  write 'R' in the board
                            is_move_player = check_full_colum_set(scanner.nextInt(), 'B');
                        } while (!is_move_player);
                        //test.print_board();
                        //System.out.println(winner_draw('R'));
                        //System.out.println();
                        if (winner_draw('B') == 'B') {
                            System.out.println("\t\t\tYOU ARE WINNER");
                            break;
                        }
                        System.out.println(find_best_move_alpha_beta(board).column+1);
                        System.out.println(find_best_move(board).column+1);
                        check_full_colum_set(find_best_move_alpha_beta(board).column+1,'R');
                        //check_full_column_set(find_best_move_alpha_beta(board).column+1,'R');

                        test.print_board();

                        //System.out.println(winner_draw('B'));
                        if (winner_draw('R') == 'R') {
                            System.out.println("\t\t\tGAME OVER");
                            break;
                        }
                        counter++;
                        if (winner_draw('B') == 'd') {
                            System.out.println("\t\t\tTHE GAME IS DRAW");
                            break;
                        }
                        /// if counter = 21 so
                    }                                                                 ///the board is full of pieces

                    incorrect_number++;
                    break;

                case 2:
                    test.print_board();
                    System.out.println("\nplayer1 has a red piece(R) and player2 has a blue piece(B)\n" +
                            "Please enter the number of the column where you want the piece to be placed");
                    int temp_2player = 0;                                            /// must be 1 to exit the
                    while (temp_2player < 1)                                         /// loop below and finish the game
                    {
                        boolean is_move_player1 = false;                            /// is player1 move?
                        boolean is_move_player2 = false;                            /// is player2 move?
                        do                                                           /// player1 move if player1
                        {                                                            ///  chose a correct column
                            System.out.println("\nturn player1(R)");                 ///  write 'R' in the board
                            is_move_player1 = check_full_colum_set(scanner.nextInt(), 'R');
                        } while (!is_move_player1);
                        test.print_board();
                        is_move_player2 = false;
                        //System.out.println(winner_draw('R'));
                        //System.out.println();
                        if (winner_draw('R') == 'R') {
                            System.out.println("\t\t\tRED IS WINNER");
                            break;
                        }

                        do                                                            /// player2 move if player2
                        {                                                             ///  chose a correct column
                            System.out.println("\nturn player2(B)");                  ///  write 'B' in the board
                            is_move_player2 = check_full_colum_set(scanner.nextInt(), 'B');
                        } while (!is_move_player2);
                        test.print_board();
                        is_move_player2 = false;

                        //System.out.println(winner_draw('B'));
                        if (winner_draw('B') == 'B') {
                            System.out.println("\t\t\tBLUE IS WINNER");
                            break;
                        }
                        counter++;
                        if (winner_draw('B') == 'd') {
                            System.out.println("\t\t\tTHE GAME IS DRAW");
                            break;
                        }
                        /// if counter = 21 so
                    }                                                                 ///the board is full of pieces

                    incorrect_number++;
                    break;

                default:
                    System.out.println("Please enter the correct number\n");          /// To go around in the loop if
            }                                                                         /// a wrong value is entered





        } while (incorrect_number < 1);

        //test.print_board();
        //System.out.println(minimax(board,2,true));
        //System.out.println(evaluate('R'));
        //System.out.println(evaluate('B'));
        //Scanner scanner = new Scanner(System.in); ///ezaf
        //String j = scanner.nextLine();
        //System.out.println(j);
        //find_best_move(board);
        //System.out.println(find_best_move(board).column);
    }

//}
    static class Move
    {
        int column;
    }

    static char computer = 'R', player = 'B';
    static Boolean is_moves_left(char board[][])
    {
        int counter_column_full = 0;
        for (int i = 0; i < 7; i++)
        {
            if (is_full(i))
            {
                counter_column_full++;
            }
        }
        if (counter_column_full == 7)
        {
            return false;
        }
        else
            return true;
    }
    static int evaluate(char player1)
    {
        // Checking for Rows for X or O victory.

        if (player1 == winner_draw('R'))
        {
            return 10;
        }
        else if ('B' == winner_draw('B'))
        {
            return -10;
        }
        else
        {
            return 0;
        }
    }
    //static int minimax(char board[][], int depth)
    //{
      // return minimax(board,depth,true);
    //}
    //Boolean ismax =true;
    static int minimax(char board1[][], int depth, Boolean ismax )
    {
        level = depth;
        int max_depth = 4;
        int score = evaluate('R');

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!is_moves_left(board1))
            return 0;

        // If this maximizer's move
        if (ismax)
        {
            //System.out.println("123456");
            int best = -1000;
            if (depth == max_depth)
            {
                if (best == -1000)
                {
                    return 0;
                }
                else
                    return best;
            }
            // Traverse all cells

            for (int j = 0; j < 7; j++) {
                int i = 5;
                // Check if cell is empty
                if (!is_full(j))
                {
                    do {
                        if (board[i][j] == '-')
                        {
                            break;
                        } else
                            i--;
                    } while (i >= 0);

                        // Make the move
                        board[i][j] = computer;
                   /* for (int r = 0; r < 6; r++) {
                    System.out.print((6 - r) + " $ ");                               /// row number
                    for (int k = 0; k < 7; k++) {
                        System.out.print(board[r][k]);                                        ///print value of cells
                        System.out.print(" ");
                    }
                    System.out.println();
                }
                    System.out.println("    $ $ $ $ $ $ $ \n    1 2 3 4 5 6 7 \n");*/

                    //System.out.print("ineh");
                        //System.out.println(i);
                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !ismax));

                        // Undo the move
                        board[i][j] = '-';
                    }
                    //i = 5;
                }
            return best;
        }

        // If this minimizer's move
        else
        {
            //System.out.println("99");
            int best = 1000;
            if (depth == max_depth)
            {
                if (best == 1000)
                {
                    return 0;
                }
                else
                return best;
            }
            // Traverse all cells
            for (int j = 0; j < 7; j++)
            {
                int i = 5;
                // Check if cell is empty
                if (!is_full(j))
                {
                    do
                    {
                        if (board[i][j] == '-')
                        {
                            break;
                        }
                        else
                            i--;
                    }while (i >=0 );
                    // Make the move
                    board[i][j] = player;
                    /*for (int r = 0; r < 6; r++) {
                        System.out.print((6 - r) + " $ ");                               /// row number
                        for (int k = 0; k < 7; k++) {
                            System.out.print(board[r][k]);                                        ///print value of cells
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                    System.out.println("    $ $ $ $ $ $ $ \n    1 2 3 4 5 6 7 \n");
                    System.out.println("min");*/

                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.min(best, minimax(board,
                            depth + 1, !ismax));
                    // Undo the move
                    board[i][j] = '-';
                }

            }
            return best;
        }
    }

    // This will return the best possible
// move for the player
    static Move find_best_move(char board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.column = -1;
        int best_level = 10;
        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        //for (int i = 5; i >= 0; i--)
        //{
            for (int j = 0; j < 7; j++)
            {
                int i = 5;
                // Check if cell is empty
                if (!is_full(j))
                {
                    do
                    {
                        if (board[i][j] == '-')
                        {
                            break;
                        }
                        else
                            i--;
                    }while (i >=0 );
                    // Make the move
                    board[i][j] = computer;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);
                   /* System.out.println(moveVal);
                    for (int r = 0; r < 6; r++) {
                        System.out.print((6 - r) + " $ ");                               /// row number
                        for (int k = 0; k < 7; k++) {
                            System.out.print(board[r][k]);                                        ///print value of cells
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                    System.out.println("    $ $ $ $ $ $ $ \n    1 2 3 4 5 6 7 \n");*/
                    //System.out.println(minimax(board, 0, false));
                    //System.out.println(minimax(board, 0, true));
                    // Undo the move
                    board[i][j] = '-';
                    if (moveVal == bestVal && best_level > level )
                    {
                        bestMove.column = j;
                        bestVal = moveVal;
                        best_level = level;
                    }
                    if (moveVal > bestVal )
                    {
                        bestMove.column = j;
                        bestVal = moveVal;
                        best_level = level;
                    }
                }
           // }
        }
        //System.out.printf("The value of the best Move " +
          //      "is : %d\n\n", bestMove);
        return bestMove;
    }





        // Initial values of
// Alpha and Beta

    static int minimax_alpha_beta(char board1[][], int depth, Boolean ismax , int alpha, int beta)
    {
        level = depth;
        int max_depth = 4;
        int score = evaluate('R');

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!is_moves_left(board1))
            return 0;

        // If this maximizer's move
        if (ismax)
        {
            //System.out.println("123456");
            int best = MIN;
            if (depth == max_depth)
            {
                if (best == -1000)
                {
                    return 0;
                }
                else
                    return best;
            }
            // Traverse all cells

            for (int j = 0; j < 7; j++) {
                int i = 5;
                // Check if cell is empty
                if (!is_full(j))
                {
                    do {
                        if (board[i][j] == '-')
                        {
                            break;
                        } else
                            i--;
                    } while (i >= 0);

                    // Make the move
                    board[i][j] = computer;
                   /* for (int r = 0; r < 6; r++) {
                    System.out.print((6 - r) + " $ ");                               /// row number
                    for (int k = 0; k < 7; k++) {
                        System.out.print(board[r][k]);                                        ///print value of cells
                        System.out.print(" ");
                    }
                    System.out.println();
                }
                    System.out.println("    $ $ $ $ $ $ $ \n    1 2 3 4 5 6 7 \n");*/

                    //System.out.print("ineh");
                    //System.out.println(i);
                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.max(best, minimax_alpha_beta(board, depth + 1, !ismax, alpha , beta));
                    alpha = Math.max(alpha , best);
                    // Undo the move
                    board[i][j] = '-';
                    if (beta <= alpha)
                        break;
                }
                //i = 5;
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            //System.out.println("99");
            int best = MAX;
            if (depth == max_depth)
            {
                if (best == 1000)
                {
                    return 0;
                }
                else
                    return best;
            }
            // Traverse all cells
            for (int j = 0; j < 7; j++)
            {
                int i = 5;
                // Check if cell is empty
                if (!is_full(j))
                {
                    do
                    {
                        if (board[i][j] == '-')
                        {
                            break;
                        }
                        else
                            i--;
                    }while (i >=0 );
                    // Make the move
                    board[i][j] = player;
                    /*for (int r = 0; r < 6; r++) {
                        System.out.print((6 - r) + " $ ");                               /// row number
                        for (int k = 0; k < 7; k++) {
                            System.out.print(board[r][k]);                                        ///print value of cells
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                    System.out.println("    $ $ $ $ $ $ $ \n    1 2 3 4 5 6 7 \n");
                    System.out.println("min");*/

                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.min(best, minimax_alpha_beta(board, depth + 1, !ismax,alpha,beta));
                    beta = Math.min(beta , best);
                    // Undo the move
                    board[i][j] = '-';
                    if (beta <= alpha)
                        break;
                }

            }
            return best;
        }
    }
    static Move find_best_move_alpha_beta(char board[][])
    {
        int bestVal = MIN;
        Move bestMove = new Move();
        bestMove.column = -1;
        int best_level = 10;
        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        //for (int i = 5; i >= 0; i--)
        //{
        for (int j = 0; j < 7; j++)
        {
            int i = 5;
            // Check if cell is empty
            if (!is_full(j))
            {
                do
                {
                    if (board[i][j] == '-')
                    {
                        break;
                    }
                    else
                        i--;
                }while (i >=0 );
                // Make the move
                board[i][j] = computer;

                // compute evaluation function for this
                // move.
                int moveVal = minimax_alpha_beta(board, 0, false , MIN, MAX );
                   /* System.out.println(moveVal);
                    for (int r = 0; r < 6; r++) {
                        System.out.print((6 - r) + " $ ");                               /// row number
                        for (int k = 0; k < 7; k++) {
                            System.out.print(board[r][k]);                                        ///print value of cells
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                    System.out.println("    $ $ $ $ $ $ $ \n    1 2 3 4 5 6 7 \n");*/
                //System.out.println(minimax(board, 0, false));
                //System.out.println(minimax(board, 0, true));
                // Undo the move
                board[i][j] = '-';
                if (moveVal == bestVal && best_level > level )
                {
                    bestMove.column = j;
                    bestVal = moveVal;
                    best_level = level;
                }
                if (moveVal > bestVal )
                {
                    bestMove.column = j;
                    bestVal = moveVal;
                    best_level = level;
                }
            }
            // }
        }
        //System.out.printf("The value of the best Move " +
        //      "is : %d\n\n", bestMove);
        return bestMove;
    }
        // Returns optimal value for
// current player (Initially called
// for root and maximizer)
        static int minimax_2(int depth, int nodeIndex,
                           Boolean maximizingPlayer,
                           int values[], int alpha,
                           int beta)
        {
            // Terminating condition. i.e
            // leaf node is reached
            if (depth == 3)
                return values[nodeIndex];

            if (maximizingPlayer)
            {
                int best = MIN;

                // Recur for left and
                // right children
                for (int i = 0; i < 2; i++)
                {
                    int val = minimax_2(depth + 1, nodeIndex * 2 + i,
                            false, values, alpha, beta);
                    best = Math.max(best, val);
                    alpha = Math.max(alpha, best);

                    // Alpha Beta Pruning
                    if (beta <= alpha)
                        break;
                }
                return best;
            }
            else
            {
                int best = MAX;

                // Recur for left and
                // right children
                for (int i = 0; i < 2; i++)
                {

                    int val = minimax_2(depth + 1, nodeIndex * 2 + i,
                            true, values, alpha, beta);
                    best = Math.min(best, val);
                    beta = Math.min(beta, best);

                    // Alpha Beta Pruning
                    if (beta <= alpha)
                        break;
                }
                return best;
            }
        }

    public int heuristic(char[][] board, char player) {
        int score = 0;
        for (int i = 5; i >= 0; i--)                                             ///horizontal connect4
        {
            for (int j = 0; j < 4; j++)                                          ///we can have 4 connect4 in a row
            {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    if (board[i][j + k] == player) {
                        count++;
                    } else if (board[i][j + k] != '-') {
                        count = 0;
                        break;
                    }
                }
                score += count * count;
            }
        }
        for (int i = 0; i <= 6; i++)                                         ///vertical connect4
        {
            for (int j = 5; j > 2; j--)                                      ///we can have 3 connect4
            {                                                                ///in a column
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    if (board[j - k][i] == player) {
                        count++;
                    } else if (board[j - k][i] != '-') {
                        count = 0;
                        break;
                    }
                }
                score += count * count;
            }
        }
        for (int i = 5; i > 2; i--)                                          ///diagonal connect4
        {
            for (int j = 0; j < 4; j++)                                      /// we can have 4 diagonal
            {                                                                /// connect4 from left to right
                int count = 0;                                               /// in matrix
                for (int k = 0; k < 4; k++) {
                    if (board[i - k][j + k] == player) {
                        count++;
                    } else if (board[i - k][j + k] != '-') {
                        count = 0;
                        break;
                    }
                }
                score += count * count;
            }
            for (int j = 6; j > 2; j--)                                      /// we can have 4 diagonal
            {                                                                /// connect4 from right to left
                int count = 0;                                                   /// in matrix
                for (int k = 0; k < 4; k++) {
                    if (board[i - k][j - k] == player) {
                        count++;
                    } else if (board[i - k][j - k] != '-') {
                        count = 0;
                        break;
                    }
                }
                score += count * count;

            }
        }
        return score;
    }
}