package cz.osu.kip.TTT.servlets;

import cz.osu.kip.TTT.services.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MoveHandler", urlPatterns = {"/web/moveHandler"})
public class MoveHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String challenger_id_string = request.getParameter("challenger");
        String opponent_id_string = request.getParameter("opponent");
        String game_id_string = request.getParameter("game");
        String key = request.getParameter("key");

        String row_string = request.getParameter("input_cellRow");
        String col_string = request.getParameter("input_cellCol");

        String player_character = " ";
        String response_link = "";

        if (game_id_string == null || game_id_string.equals("")){
            System.out.println("Error. Game id not found.");
        } else {
            if (challenger_id_string == null || challenger_id_string.equals("")){
                System.out.println("Error. Challenger id not found.");
            } else {
                if (opponent_id_string == null || opponent_id_string.equals("")){
                    //challenger
                    player_character = "X";
                    response_link = "http://localhost:8080/TicTacToePls-01/web/multi-player-game.jsp?" +
                            "key=" + key +
                            "&game=" + game_id_string +
                            "&challenger=" + challenger_id_string;
                } else {
                    //opponent
                    player_character = "O";
                    response_link = "http://localhost:8080/TicTacToePls-01/web/multi-player-game.jsp?" +
                            "key=" + key +
                            "&game=" + game_id_string +
                            "&challenger=" + challenger_id_string +
                            "&opponent=" + opponent_id_string;
                }
                if (row_string == null || row_string.equals("") || col_string == null || col_string.equals("")){
                    System.out.println("Error. Row or Col not found.");
                } else {
                    BoardService boardService = (BoardService) request.getSession().getAttribute("boardService");
                    boardService.move(Integer.parseInt(row_string), Integer.parseInt(col_string), player_character);
                    boardService.printBoard();
                    response.sendRedirect(response_link);
                }
            }
        }




    }
}
