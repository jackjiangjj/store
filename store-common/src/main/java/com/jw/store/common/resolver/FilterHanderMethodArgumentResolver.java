package com.jw.store.common.resolver;

import com.jw.store.common.annotation.Filter;
import com.jw.store.common.parameter.RequestParametersEntity;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FilterHanderMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Filter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
       String entity=nativeWebRequest.getParameter("entity");
       String type=nativeWebRequest.getParameter("type");
       Integer page=Integer.parseInt(nativeWebRequest.getParameter("page"));
       String map=nativeWebRequest.getParameter("filter");
        return new RequestParametersEntity(entity,type,page,map);
    }
}
