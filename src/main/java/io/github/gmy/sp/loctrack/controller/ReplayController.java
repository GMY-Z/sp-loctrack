package io.github.gmy.sp.loctrack.controller;

import io.github.gmy.sp.loctrack.DTO.GpsDTO;
import io.github.gmy.sp.loctrack.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @authon GMY
 * @create 2020-05-28 17:31
 */
@Controller
public class ReplayController {

    @Autowired
    GpsService gpsService;

//    @RequestMapping(value = "/demo", method = {RequestMethod.GET})
    @GetMapping("/replay1")
    public String queryErrGpsInfo1(HttpServletRequest request,
                                   Model model) {

        String phone = (String) request.getSession().getAttribute("phone");
        List<GpsDTO> GpsDTOList = gpsService.queryLocationsByPhone("phone");
        request.setAttribute("GpsDTOList", GpsDTOList);
//        model.addAttribute("GpsDTOList", GpsDTOList);
        return "replay";
    }

}
