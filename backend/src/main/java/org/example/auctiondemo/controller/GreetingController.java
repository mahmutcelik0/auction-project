package org.example.auctiondemo.controller;

import org.example.auctiondemo.model.*;
import org.example.auctiondemo.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/clients/greetings")
    public GreetingMessage greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new GreetingMessage("Hello, " + HtmlUtils.htmlEscape(message.getHelloMessage()) + "!");
    }

    @MessageMapping("/newAuction")
    @SendTo("/auctions")
    public List<Auction> createNewAuction(AuctionRequest auctionRequest) throws Exception {
        Auction auction = new Auction(auctionRequest.getProduct(),auctionRequest.getUser(), LocalDateTime.now());
        auctionService.getAuctions().add(auction);
        System.out.println(auction);
        return auctionService.getAuctions();
    }

    @MessageMapping("/joinAdmin")
    public List<Auction> adminConnected(User user) throws Exception {
        System.out.println("ADMIN CONNECTED");
        messagingTemplate.convertAndSendToUser(user.getId().toString(),"/admin",auctionService.getAuctions());
        return auctionService.getAuctions();
    }

    @MessageMapping("/info/{auctionId}")
    @SendTo("/{auction}")
    public Auction sendAuctionInfo(@DestinationVariable String auctionId){
        return auctionService.findById(auctionId);
    }
}
