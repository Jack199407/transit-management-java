package transit.management.viewlayer.servlet;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import transit.management.businesslayer.VehicleController;
import transit.management.businesslayer.dto.VehiclesAndRoutesDto;
import transit.management.viewlayer.dto.FuelMonitorDto;
import transit.management.viewlayer.response.TransitResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class QueryVehicleServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        BufferedReader reader = req.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);
        Integer vehicleId = jsonRequest.get("vehicleId").getAsInt();
        FuelMonitorDto res = controller.listFuelMonitor(vehicleId);
        TransitResponse<FuelMonitorDto> resObj;
        resObj = new TransitResponse<>(true, res);

        String jsonResponse = gson.toJson(resObj);
        resp.getWriter().write(jsonResponse);
    }
}
