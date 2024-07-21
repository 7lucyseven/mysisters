package com.lucyseven.mysisters.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterUtil {

    public List ConvertToOneComeList(Object  commentsOoject) {
        List list = new ArrayList();

        if(commentsOoject instanceof List) {
            List commentList = (List) commentsOoject;
            for(Object commentObject  : commentList) {
                list.add( ConvertToOneComeMap(commentObject) );
            }
        }

        return list;
    }

    private Map ConvertToOneComeMap(Object commentObject) {
        Map<String, Object> map = new HashMap<>();
        if(commentObject instanceof Map) {
            Map<String, Object> commentMap = (Map<String, Object>) commentObject;
            for(Map.Entry<String, Object> entry  : commentMap.entrySet()) {
                map.put( entry.getKey(), entry.getValue() );
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            map.put("color", (Map<String, Integer>) map.get("color"));
            map.put("data", (Map<String, Object>) map.get("data"));
            Map dataMap = (Map<String, Object>) map.get("data");
            List list = new ArrayList<>();
            list = (List) dataMap.get("badges");
            map.put("badges", list );
            System.out.println(list.get(0));
            Map badgesMap = (Map<String, String>) list.get(0);
            list.add(0, badgesMap);
        }
        return map;
    }


}
