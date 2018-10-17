package cn.footman.springboot.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author footman77
 * @create 2018-09-22 17:08
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);

        map.put("li","nihao");


        Map<String,Object> example = (Map<String, Object>) requestAttributes.getAttribute("example", 0);


        map.put("example",example);
        return map;
    }
}
