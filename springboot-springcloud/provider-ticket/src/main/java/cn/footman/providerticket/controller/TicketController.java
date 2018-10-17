package cn.footman.providerticket.controller;

import cn.footman.providerticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author footman77
 * @create 2018-10-16 12:52
 */
@RestController
public class TicketController {


    @Autowired
    private TicketService ticketService;

    @GetMapping("/hello")
    public String getTicket(){
        return ticketService.hello();
    }
}
