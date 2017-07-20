package com.liazylee.Mysql;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by li on 7/11/17.
 */
@Service
public class AppAgentFacede {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UsersAgentMapper usersagentMapper;

    public void SaveUserClick(String udid, String multipleurl, String appid) {
        try {
            Map<String, Object> usersagentparams = new HashMap<>();
            usersagentparams.put("udid", udid);
            usersagentparams.put("multipleurl", multipleurl);
            usersagentparams.put("isAcitve", 0);
            usersagentparams.put("iscallback", 0);
            usersagentparams.put("appid", appid);
            usersagentparams.put("dateCreated", new Date());
            usersagentMapper.insert(usersagentparams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SaveUserActive(String udid, String appid) {
        try {

            Map<String, Object> params = new HashMap<>();
            params.put("udid", udid);
            params.put("appid", appid);
            params.put("dateCreated", new Date());
            List<Map<String, Object>> user = usersMapper.find_one(udid);
            if (user.size() == 0) {
                usersMapper.insert(params);
                Map<String, Object> agentParams = new HashMap<>();
                agentParams.put("udid", udid);
                List<Map<String, Object>> docUserAgent = usersagentMapper.find_one(agentParams);
                if (docUserAgent != null && docUserAgent.size() > 0) {
                    String isAcitve = String.valueOf(docUserAgent.get(0).get("isAcitve") == null ? "" : docUserAgent.get(0).get("isAcitve"));
                    if ("0".equals(isAcitve)) {
                        Map<String, Object> activeParams = new HashMap<>();
                        activeParams.put("udid", udid);
                        activeParams.put("isAcitve", "1");
                        usersagentMapper.update_one(activeParams);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> CheckIDFAs(String idfas, String appid) {

        Map<String, Object> dicResult = new HashMap<>();
        try {
            String[] listIdfas = idfas.split(",");
            List<Map<String, Object>> user = null;
            for (String idfa : listIdfas) {
                String udid = idfa;
                user = usersMapper.find_one(udid);
                if (user == null) {
                    dicResult.put(idfa, "0");
                } else if (user != null && user.size() == 0) {
                    dicResult.put(idfa, "0");
                } else {
                    dicResult.put(idfa, "1");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dicResult;
    }

    private String null2Obj(Object obj) {
        return String.valueOf(obj == null ? "" : obj);
    }

    public void CallBackUserAgents() {

        Map<String, Object> params = new HashMap<>();
        params.put("isAcitve", "1");
        params.put("iscallback", "0");
        try {
            List<Map<String, Object>> findResult = usersagentMapper.callback_find(params);
            int count = 0;
            String multipleurl = "";
            Map<String, Object> paramss = new HashMap<>();
            for (Map<String, Object> item : findResult) {
                count++;
                multipleurl = null2Obj(item.get("multipleurl"));
                try {
                    get(multipleurl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                paramss.put("iscallback", "1");
                paramss.put("udid", null2Obj(item.get("udid")));
                usersagentMapper.callback_update_one(paramss);
                System.out.println(count);
                System.out.println(item.get("udid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String get(String url) throws Exception {
        HttpClient httpCient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String response = "";
        try {
            HttpResponse httpResponse = httpCient.execute(httpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}