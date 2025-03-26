package transit.management.viewlayer.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import transit.management.businesslayer.VehicleController;
import transit.management.transferobjects.User;
import transit.management.viewlayer.response.TransitResponse;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json;charset=UTF-8");

        BufferedReader reader = request.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);
        String name = jsonRequest.get("name").getAsString();
        String password = jsonRequest.get("password").getAsString();
        User user = controller.validUserInfo(name, password);

        TransitResponse<User> resObj;
        if (Objects.nonNull(user)) {
            resObj = new TransitResponse<>(true, user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resObj = new TransitResponse<>(false, "Username or password incorrect!");
        }

        String jsonResponse = gson.toJson(resObj);
        response.getWriter().write(jsonResponse);
    }
}
