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

public class AssignRouteServlet extends HttpServlet {
    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json;charset=UTF-8");

        // 读取 JSON 请求体
        BufferedReader reader = request.getReader();
        JsonObject jsonRequest = gson.fromJson(reader, JsonObject.class);
        Integer vehicleId = jsonRequest.get("vehicleId").getAsInt();
        Integer routeId = jsonRequest.get("routeId").getAsInt();
        controller.AssignRoute(vehicleId, routeId);
        // 构造响应
        TransitResponse<String> resObj;
        resObj = new TransitResponse<>(true, null);
        String jsonResponse = gson.toJson(resObj);
        response.getWriter().write(jsonResponse);
    }
}
