package transit.management.viewlayer.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import transit.management.businesslayer.VehicleController;
import transit.management.viewlayer.response.TransitResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        BufferedReader reader = req.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);

        String name = jsonRequest.get("name").getAsString();
        String email = jsonRequest.get("email").getAsString();
        String password = jsonRequest.get("password").getAsString();
        byte role = jsonRequest.get("role").getAsByte();

        String msg = controller.signUp(name, email, password, role);
        TransitResponse<String> resObj;
        if ("success".equals(msg)) {
            resObj = new TransitResponse<>(true, null);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resObj = new TransitResponse<>(false, msg);
        }
        String jsonResponse = gson.toJson(resObj);
        resp.getWriter().write(jsonResponse);
    }
}
