package br.redhat.jmx.web.service;

import javax.ejb.Stateless;

@Stateless
public class HelloService {

    public String createHelloMessage(String welcome, String name) {
        return welcome + " " + name + "!";
    }
    
}
