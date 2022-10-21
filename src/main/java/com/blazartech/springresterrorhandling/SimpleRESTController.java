/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.springresterrorhandling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aar1069
 */
@RestController
public class SimpleRESTController {

    @GetMapping("/simple")
    public SimpleReply getReply() {
        SimpleReply reply = new SimpleReply();
        reply.setAge(50);
        reply.setName(("Scott"));
        return reply;
    }

    @GetMapping("/simple/{name}")
    public SimpleReply getReplyPossibleError(@PathVariable String name) {
     //   try {
            SimpleReply reply = new SimpleReply();
            reply.setAge(50);
            reply.setName(name);

            if (name.equals("Scott")) {
                throw new IllegalArgumentException("Scott intentionally throws error");
            } else if (name.equals("Pauline")) {
                throw new IllegalStateException("Pauline is ageless");
            }

            return reply;
       /* } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request with param " + name, e);
        }*/
    }
}
