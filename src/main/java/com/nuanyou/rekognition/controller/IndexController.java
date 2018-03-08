package com.nuanyou.rekognition.controller;

import com.google.common.collect.Lists;
import com.nuanyou.rekognition.model.vo.GraphicVo;
import com.nuanyou.rekognition.service.GraphicService;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by mylon.sun on 2017/12/8.
 */
@Controller
@RequestMapping(value = "")
public class IndexController {

    private final static Logger _LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private GraphicService graphicService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(
            @ApiParam(value = "关键字") @RequestParam(value = "key",required = false) String key,
            Model model) throws Exception {
        List<GraphicVo> topImages = Lists.newArrayList();
        if(StringUtils.isEmpty(key)){
            topImages = graphicService.getTopImages();
        }else{
            topImages = graphicService._detect(key);
            model.addAttribute("key", key);
        }
        model.addAttribute("list", topImages);
        return "index";
    }

    @RequestMapping(value = "_extend", method = RequestMethod.GET)
    public String _extend(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("faceDetails", request.getSession().getAttribute("faceDetails"));
        model.addAttribute("textDetections", request.getSession().getAttribute("textDetections"));
        return "extend";
    }

}
