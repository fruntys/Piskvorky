package cz.osu.kip.TTT.servlets;

import cz.osu.kip.TTT.db.entities.UserRepository;
import cz.osu.kip.TTT.db.entities.UsersEntity;
import cz.osu.kip.TTT.services.RegisterUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateUserServlet", urlPatterns = {"/web/registerUser"})
public class RegisterUserServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nick = request.getParameter("nickname");
        String mail = request.getParameter("mail");
        String pw1 = request.getParameter("pw1");
        String pw2 = request.getParameter("pw2");

        if (nick == null) System.out.println("Nick did not pass."); //alert nick
        else if (mail == null) System.out.println("mail did not pass."); //alert nick
        else if (pw1 == null) System.out.println("pw1 did not pass."); //alert nick
        else if (pw2 == null) System.out.println("pw2 did not pass."); //alert nick
        else {
            if (pw1.equals(pw2)){
                RegisterUserService rus = new RegisterUserService(nick, mail, pw1);
                rus.insert();
                response.sendRedirect("index.jsp");
            }
        }
    }

}
