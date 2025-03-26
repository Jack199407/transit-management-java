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

public class AddVehicleServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        BufferedReader reader = request.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);
        String type = jsonRequest.get("vehicleType").getAsString();
        controller.addVehicle(type);
        TransitResponse<String> resObj;
        resObj = new TransitResponse<>(true, null);
        String jsonResponse = gson.toJson(resObj);
        response.getWriter().write(jsonResponse);
    }
}
