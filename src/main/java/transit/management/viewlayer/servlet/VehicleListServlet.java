package transit.management.viewlayer.servlet;


import com.google.gson.Gson;
import transit.management.businesslayer.VehicleController;
import transit.management.transferobjects.Vehicle;
import transit.management.viewlayer.response.TransitResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VehicleListServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        List<Vehicle> list = controller.listVehicles();
        TransitResponse<List<Vehicle>> resObj;
        resObj = new TransitResponse<>(true, list);

        String jsonResponse = gson.toJson(resObj);
        response.getWriter().write(jsonResponse);
    }
}
