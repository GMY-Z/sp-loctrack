package io.github.gmy.sp.loctrack.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.github.gmy.sp.loctrack.DTO.CircleFenceDTO;
import io.github.gmy.sp.loctrack.DTO.GpsDTO;
import io.github.gmy.sp.loctrack.DTO.HeartDTO;
import io.github.gmy.sp.loctrack.provider.FenceProvider;
import io.github.gmy.sp.loctrack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.alibaba.fastjson.JSON.isValid;
import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @authon GMY
 * @create 2020-06-12 21:47
 */
@Controller
public class MapController {
    @Autowired
    private GpsService gpsService;

    @Autowired
    private FenceProvider fenceProvider;

    @Autowired
    private FenceService fenceService;

    @Autowired
    private SmsAlertService smsAlertService;

    @Autowired
    HeartService heartService;

    @Autowired
    StepsService stepsService;
    @GetMapping("/map")
    public String map(HttpServletRequest request,Model model){
        String phone = (String) request.getSession().getAttribute("phone");
        Object danger = request.getSession().getAttribute("danger");
        System.out.println(danger);
        if (danger != null) {
            model.addAttribute("danger", 1);
        }
        //返回围栏数据
        List<GpsDTO> fenceArr = new ArrayList<>();
        fenceArr = fenceService.queryFenceByPhone(phone);
        model.addAttribute("fenceJson",fenceArr);

        //定位
        GpsDTO location = gpsService.queryLastLocationByPhone(phone);
        return "map";
    }

    @RequestMapping(value = "/track")
    @ResponseBody
    public GpsDTO track(HttpServletRequest request){
        String phone = (String) request.getSession().getAttribute("phone");
        GpsDTO location = gpsService.queryLastLocationByPhone(phone);
        return location;
    }

    @GetMapping("/map/fence")
    public String locate(HttpServletRequest request,Model model){
        String phone = (String) request.getSession().getAttribute("phone");

        //返回围栏数据
        List<GpsDTO> fenceArr = new ArrayList<>();
        fenceArr = fenceService.queryFenceByPhone(phone);
        model.addAttribute("fenceJson",fenceArr);
        GpsDTO location = gpsService.queryLastLocationByPhone(phone);
        request.setAttribute("location", location);
        return "map1";
    }


    @GetMapping("/saveFence")
    public String saveFence(@RequestParam(value = "polygon",required = false) String polygon,
                            HttpServletRequest request){
        System.out.println(polygon);
        String phone = (String) request.getSession().getAttribute("phone");
        //save
        List<GpsDTO> gpsDTOList = new ArrayList<>();
        String[] pos = polygon.split(";");
        for (String p : pos) {
            String[] split = p.split(",");
            GpsDTO gpsDTO = new GpsDTO( Double.parseDouble(split[0]), Double.parseDouble(split[1]));
            gpsDTOList.add(gpsDTO);
        }
        fenceService.addFenceByPhone(gpsDTOList,phone);
        return "map1";
    }

    @GetMapping("/deleteFence")
    public String deleteFence(HttpServletRequest request) {
        String phone = (String) request.getSession().getAttribute("phone");
        //delete
        fenceService.deleteFenceByPhone(phone);
//        return "map1";
        return "redirect:/map/fence";
    }

    @PostMapping(value = "/api/danger/{deviceId}")
    public ResponseEntity<String> danger(
            @PathVariable("deviceId") int deviceId) {
        smsAlertService.send("18768194932");
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @PostMapping("/api/gps/{deviceId}")
    public ResponseEntity<String> login(
            @PathVariable("deviceId") int deviceId,
            @RequestParam(value = "longitude") double longitude,
            @RequestParam(value = "latitude") double latitude) {
        try {
            gpsService.addLocationByDeviceId(
                    new GpsDTO(longitude, latitude),
                    deviceId);

            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value = "/api/health/{deviceId}")
    public void heart(HttpServletRequest request, Model model,
                       @PathVariable("deviceId") int deviceId,
                       @RequestParam(value = "heartRate") int heartRate,
                       @RequestParam(value = "oxy") double oxy,
                       @RequestParam(value = "steps") int steps){
        //持久化心率
        heartService.addHeartByDeviceId(new HeartDTO(heartRate, oxy), deviceId);
        //持久化速度
        stepsService.setSteps(deviceId,steps);
    }

    @RequestMapping(value = "/heart")
    @ResponseBody
    public HeartDTO heart(HttpServletRequest request){
        String phone = (String) request.getSession().getAttribute("phone");
        HeartDTO heartDTO = heartService.queryLastHeartByPhone(phone);
        return heartDTO;
    }

    @RequestMapping(value = "/steps")
    @ResponseBody
    public int steps(HttpServletRequest request){
        String phone = (String) request.getSession().getAttribute("phone");
        int step = stepsService.queryStepsByPhone(phone);
        return step;
    }

    @GetMapping("/createfence")
    public String create(){
        //高德围栏
//        CircleFenceDTO circleFenceDTO = new CircleFenceDTO();
//        circleFenceDTO.setName("围栏一");
//        circleFenceDTO.setCenter("116.480151,39.998453");
//        circleFenceDTO.setRadius(1000);
//        String circleFence = fenceProvider.createCircleFence(circleFenceDTO);
        String fence = fenceProvider.getFence("围栏一");
        System.out.println(fence);
        return "redirect:/map/fence";
    }

    @GetMapping("/fence")
    public String fence(){
        return "fence";
    }

    @GetMapping("/fence2")
    public String fence2(){
        return "weilan2";
    }

}