package cz.osu.kip.TTT.servlets;

import cz.osu.kip.TTT.services.CreateGameService;
import cz.osu.kip.TTT.services.LoginUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateGameServlet", urlPatterns = {"/web/createGame"})
public class CreateGameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idChallenger = request.getParameter("idChallenger");
        if (idChallenger == null) System.out.println("id did not pass.");
        else {
            CreateGameService createGameService = new CreateGameService(Integer.parseInt(idChallenger));
            createGameService.insert();

            String game_key_code = createGameService.getKeyCodeOfGame();
            int game_id = createGameService.getIdOfGame();

            if (game_key_code.equals("") || game_id == -1){
                System.out.println("KeyCode or GameId is empty.");
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("multi-player-game.jsp?" +
                        "key=" + game_key_code +
                        "&game=" + game_id +
                        "&challenger=" + idChallenger
                );
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key_code = request.getParameter("key");
        int game_id = Integer.parseInt(request.getParameter("game"));
        int challenger_id = Integer.parseInt(request.getParameter("challenger"));
        String opponent_id_String = request.getParameter("opponent");

        if(opponent_id_String == null || opponent_id_String.equals("")){
            CreateGameService createGameService = new CreateGameService();
            createGameService.update(game_id, key_code);

            response.sendRedirect("multi-player-game.jsp?" +
                    "key=" + key_code +
                    "&game=" + game_id +
                    "&challenger=" + challenger_id
            );
        } else {
            int opponent_id = Integer.parseInt(opponent_id_String);
            CreateGameService createGameService = new CreateGameService();
            createGameService.update(game_id, key_code, opponent_id);

            response.sendRedirect("multi-player-game.jsp?" +
                    "key=" + key_code +
                    "&game=" + game_id +
                    "&challenger=" + challenger_id +
                    "&opponent=" + opponent_id
            );
        }
    }


}
