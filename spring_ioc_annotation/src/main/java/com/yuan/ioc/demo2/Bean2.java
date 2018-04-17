package com.yuan.ioc.demo2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bean2")
@Scope(scopeName = "prototype")
public class Bean2 {

}
