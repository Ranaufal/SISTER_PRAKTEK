/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufal181023;

/**
 *
 * @author ranaufal
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chessboard extends JFrame implements MouseListener {
    private int squareSize = 50;
    private char[][] board = new char[8][8];
    private boolean isWhiteTurn = true;
    private Point selectedSquare = null;

    public Chessboard() {
        setTitle("Chess Game");
        setSize(squareSize * 8, squareSize * 8);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);

        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize the chessboard with starting positions of pieces
        // 'P' represents white pawn, 'p' represents black pawn
        // Other pieces: 'R' (rook), 'N' (knight), 'B' (bishop), 'Q' (queen), 'K' (king)
        char[][] initialBoard = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
        };

        board = initialBoard;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int x = col * squareSize;
                int y = row * squareSize;
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(x, y, squareSize, squareSize);
                char piece = board[row][col];
                if (piece != ' ') {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    g.drawString(String.valueOf(piece), x + squareSize / 2 - 10, y + squareSize / 2 + 10);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / squareSize;
        int y = e.getY() / squareSize;
        if (selectedSquare == null) {
            if (board[y][x] != ' ' && isWhiteTurn == (Character.isUpperCase(board[y][x]))) {
                selectedSquare = new Point(x, y);
            }
        } else {
            if (isValidMove(selectedSquare.x, selectedSquare.y, x, y)) {
                board[y][x] = board[selectedSquare.y][selectedSquare.x];
                board[selectedSquare.y][selectedSquare.x] = ' ';
                selectedSquare = null;
                isWhiteTurn = !isWhiteTurn;
                repaint();
            } else {
                selectedSquare = null;
            }
        }
    }

    private boolean isValidMove(int startX, int startY, int endX, int endY) {
        // Implement the logic for valid moves here
        // For simplicity, allow any move for now
        return true;
    }

    // Other MouseListener methods (mousePressed, mouseReleased, mouseEntered, mouseExited) are not used in this example
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Chessboard();
        });
    }
}