package com.antonio.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

@Action("/")
@ResultPath("/WEB-INF/views")
@Result(name = "SUCCESS", location = "Index.jsp")
public class Index extends ActionSupport {

}
