package com.liazylee.Web;

import com.liazylee.Mysql.AppAgentFacede;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by li on 7/11/17.
 */
@RestController
public class Apiweb {
    @Autowired
    AppAgentFacede appAgentFacede;

    @RequestMapping("/")
    public String application(HttpServletRequest env, HttpServletResponse response) {
        String response_body = new String();
        try {
            String t = env.getParameter("t");
            if (t.equals("click")) {
                response_body = processUserClick(env);
            } else if (t.equals("checkidfa")) {
                response_body = processCheckIDFAs(env);

            } else if (t.equals("active")) {
                response_body = processUserActive(env);
            } else {
                response_body = "Params Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        return response_body;
    }

    private String processUserClick(HttpServletRequest env) {

        JSONObject result = new JSONObject();
        result.put("message", "返回结果");
        result.put("success", "true");


        try {
            String udid = env.getParameter("udid");
            String multipleurl = env.getParameter("multipleurl");
            String appid = env.getParameter("appid");

            appAgentFacede.SaveUserClick(udid, multipleurl, appid);
        } catch (Exception e) {
            result.put("message", e.getMessage());
            result.put("success", "false");
            e.printStackTrace();
        }

        return result.toString();
    }

    private String processCheckIDFAs(HttpServletRequest env) {
        String result = "{}";
        try {
            String appid = env.getParameter("appid");
            String idfas = env.getParameter("idfa");
            Map<String, Object> map = appAgentFacede.CheckIDFAs(idfas, appid);
            JSONObject jasonObject = JSONObject.fromObject(map);
            result = jasonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String processUserActive(HttpServletRequest env) {
        JSONObject result = new JSONObject();
        result.put("message", "返回结果");
        result.put("success", "true");
        try {
            String appid = env.getParameter("appid");
            String uuid = env.getParameter("idfa");
            appAgentFacede.SaveUserActive(uuid, appid);
        } catch (Exception e) {
            result.put("message", e.getMessage());
            result.put("success", "false");
            e.printStackTrace();
        }
        return result.toString();
    }
}



