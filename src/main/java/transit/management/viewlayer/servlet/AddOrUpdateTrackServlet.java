package transit.management.viewlayer.servlet;

import com.google.gson.Gson;
import transit.management.businesslayer.VehicleController;
import transit.management.businesslayer.dto.ScheduleTracksDto;
import transit.management.viewlayer.dto.AddOrUpdateDto;
import transit.management.viewlayer.response.TransitResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class AddOrUpdateTrackServlet extends HttpServlet  {
    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        BufferedReader reader = req.getReader();
        AddOrUpdateDto dto = gson.fromJson(reader, AddOrUpdateDto.class);
        boolean success = controller.addOrUpdateTrack(dto);
        TransitResponse<List<ScheduleTracksDto>> resObj;
        if (success) {
            resObj = new TransitResponse<>(true, null);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resObj = new TransitResponse<>(false, "update or insert error!");
        }
        String jsonResponse = gson.toJson(resObj);
        resp.getWriter().write(jsonResponse);
    }
}
