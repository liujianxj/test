package com.lj.security.realm;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RolesOrAuthorizationFilter extends AuthorizationFilter { 

    //TODO - complete JavaDoc 

    @SuppressWarnings("unchecked") 
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException { 

        Subject subject = getSubject(request, response); 
        String[] rolesArray = (String[]) mappedValue; 

        if (rolesArray == null || rolesArray.length == 0) { 
            //no roles specified, so nothing to check - allow access. 
            return true; 
        } 

        Set<String> roles = CollectionUtils.asSet(rolesArray);
        for (String role : roles) {  
            if (subject.hasRole(role)) {  
                return true;  
            }  
        }
        return false;
//        return subject.hasAllRoles(roles); 
    } 

} 
