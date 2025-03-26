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

public class AddMaintainServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        BufferedReader reader = request.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);

        Integer vehicleId = jsonRequest.get("vehicleId").getAsInt();
        String maintainTime = jsonRequest.get("maintainTime").getAsString();
        boolean success = controller.addMaintenance(vehicleId, maintainTime);
        TransitResponse<String> resObj;
        if (success) {
            resObj = new TransitResponse<>(true, null);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resObj = new TransitResponse<>(false, "Username or password incorrect!");
        }

        String jsonResponse = gson.toJson(resObj);
        response.getWriter().write(jsonResponse);
    }
}
