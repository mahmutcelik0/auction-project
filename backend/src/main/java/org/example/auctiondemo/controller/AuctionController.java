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
public class AuctionController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @MessageMapping("/hello")
//    @SendTo("/clients/greetings")
//    public GreetingMessage greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new GreetingMessage("Hello, " + HtmlUtils.htmlEscape(message.getHelloMessage()) + "!");
//    }

    @MessageMapping("/newAuction")
    @SendTo("/auctions")
    public List<Auction> createNewAuction(AuctionRequest auctionRequest) throws Exception {
        auctionService.createNewAuction(auctionRequest);
        return auctionService.getAuctions();
    }

    @MessageMapping("/offer")
    @SendTo("/auctions")
    public List<Auction> offerToAuction(OfferRequest offerRequest) throws Exception {
        auctionService.makeOfferToAuction(offerRequest);
        return auctionService.getAuctions();
    }

    @MessageMapping("/join/user")
    @SendTo("/auctions")
    public List<Auction> userConnected(User user) throws Exception {
        return auctionService.getAuctions();
    }

//    @MessageMapping("/join/auction/{auctionId}")
//    @SendTo("/{auction}")
//    public Auction joinAuction(@DestinationVariable String auctionId){
//        return auctionService.findById(auctionId);
//    }
}
