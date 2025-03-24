package transit.management.viewlayer.servlet;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import transit.management.businesslayer.VehicleController;
import transit.management.viewlayer.response.TransitResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UpdateStatusServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        BufferedReader reader = request.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);
        Integer scheduleId = jsonRequest.get("id").getAsInt();
        String newStatus = jsonRequest.get("status").getAsString();
        boolean success = controller.updateStatus(scheduleId, newStatus);
        TransitResponse<String> resObj;
        if (success) {
            resObj = new TransitResponse<>(true, "Success!");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resObj = new TransitResponse<>(false, "Username or password incorrect!");
        }
        String jsonResponse = gson.toJson(resObj);
        response.getWriter().write(jsonResponse);
    }
}
