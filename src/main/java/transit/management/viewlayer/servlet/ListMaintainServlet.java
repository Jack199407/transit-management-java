package transit.management.viewlayer.servlet;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import transit.management.businesslayer.VehicleController;
import transit.management.viewlayer.dto.MaintenanceInfoDto;
import transit.management.viewlayer.response.TransitResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class ListMaintainServlet extends HttpServlet {
    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        BufferedReader reader = req.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);
        Integer vehicleId = jsonRequest.get("vehicleId").getAsInt();
        MaintenanceInfoDto dto = controller.queryMainInfos(vehicleId);
        TransitResponse<MaintenanceInfoDto> resObj;
        resObj = new TransitResponse<>(true, dto);

        String jsonResponse = gson.toJson(resObj);
        resp.getWriter().write(jsonResponse);
    }
}
