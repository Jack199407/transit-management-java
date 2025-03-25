package transit.management.viewlayer.servlet;


import com.google.gson.Gson;
import transit.management.businesslayer.VehicleController;
import transit.management.businesslayer.dto.ScheduleTracksDto;
import transit.management.transferobjects.DepartureSchedule;
import transit.management.viewlayer.response.TransitResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ListTrackServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final VehicleController controller = VehicleController.getInstance();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        BufferedReader reader = request.getReader();
        DepartureSchedule schedule = gson.fromJson(reader, DepartureSchedule.class);
        List<ScheduleTracksDto> tracks = controller.listTracksByVehicleIdAndScheduleId(schedule);
        TransitResponse<List<ScheduleTracksDto>> resObj;
        resObj = new TransitResponse<>(true, tracks);
        String jsonResponse = gson.toJson(resObj);
        response.getWriter().write(jsonResponse);
    }
}
