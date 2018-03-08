package com.nuanyou.rekognition.controller;

/**
 * Created by mylon.sun on 2017/12/8.
 */

import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.TextDetection;
import com.nuanyou.rekognition.service.GraphicService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mylon.sun on 2017/12/8.
 */
@Controller
@RequestMapping("graphic")
public class GraphicController {

    @Autowired
    private GraphicService graphicService;

    @RequestMapping(value = "/_label", method = RequestMethod.POST)
    public String _label(
            @ApiParam(value = "图片") @RequestParam("file") MultipartFile file
    ) throws Exception {
        graphicService._label(file);
        return "redirect:/";
    }

    @RequestMapping(value = "/_text", method = RequestMethod.POST)
    public String _text(
            @ApiParam(value = "图片") @RequestParam("textFile") MultipartFile textFile,
            HttpServletRequest request,
            Model model
    ) throws Exception {
        String textDetections = graphicService._text(textFile);
        model.addAttribute("textDetections", textDetections);
        request.getSession().setAttribute("textDetections", textDetections);
        return "redirect:/_extend";
    }

    @RequestMapping(value = "/_face", method = RequestMethod.POST)
    public String _face(
            @ApiParam(value = "图片") @RequestParam("faceFile") MultipartFile faceFile,
            HttpServletRequest request,
            Model model
    ) throws Exception {
        String faceDetails = graphicService._face(faceFile);
        model.addAttribute("faceDetails", faceDetails);
        request.getSession().setAttribute("faceDetails", faceDetails);
        return "redirect:/_extend";
    }

}
