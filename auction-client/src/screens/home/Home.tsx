import { useState, useEffect } from "react";
import { Auction, OfferRequest, User } from "@/types";
import { useSubscription, useStompClient } from "react-stomp-hooks";
// import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { AuctionCard } from "./AuctionCard";

import { AuctionCreateDialog } from "./AuctionCreateDialog";

type Props = {
  user: User;
};
export const Home = ({ user }: Props) => {
  const [lastMessage, setLastMessage] = useState("[]");
  const [auctions, setAuctions] = useState([]);
  useSubscription(`/auctions`, (message) =>
    setLastMessage(message.body ? message.body : "[]")
  );

  const stompClient = useStompClient();

  const createAuction = (data: Auction) => {
    stompClient?.publish({
      destination: "/app/newAuction",
      body: JSON.stringify({
        ...data,
      }),
    });
  };

  const placeBid = (offer: OfferRequest) => {
    stompClient?.publish({
      destination: "/app/offer",
      body: JSON.stringify({
        ...offer,
      }),
    });
  };

  const endAuction = (user: User, auctionId: string) => {
    stompClient?.publish({
      destination: `/app/terminate/${auctionId}`,
      body: JSON.stringify({
        ...user,
      }),
    });
  };

  useEffect(() => {
    if (user.username !== "") {
      stompClient?.publish({
        destination: "/app/join/user",
        body: JSON.stringify({
          ...user,
        }),
      });
    }
  }, [stompClient, user]);

  useEffect(() => {
    setAuctions(JSON.parse(lastMessage));
  }, [lastMessage]);

  return (
    <div>
      <div className="pt-6">
        <h2 className="text-3xl font-semibold">
          Welcome to auctions{" "}
          <span className=" text-orange-500">{user.username}</span>
        </h2>
      </div>
      <Separator className="my-6" />
      <div className="pb-6 flex items-center justify-between">
        <h3 className="text-2xl font-semibold">Your auctions</h3>
        <span>
          <AuctionCreateDialog user={user} createAuction={createAuction} />
        </span>
      </div>

      <div className="grid gap-4 grid-cols-3 ">
        {auctions.map((auction: Auction) =>
          auction.user.id === user.id ? (
            <AuctionCard
              key={auction.id}
              auction={auction}
              isBelongToUser={true}
              user={user}
              endAuction={endAuction}
            />
          ) : null
        )}
      </div>

      <Separator className="my-6" />

      <div className="pb-6 flex items-center justify-between">
        <h3 className="text-2xl font-semibold">Open Auctions</h3>
      </div>

      <div className="grid gap-4 grid-cols-3 ">
        {auctions.map((auction: Auction) =>
          auction.user.id !== user.id && !auction.end ? (
            <AuctionCard
              key={auction.id}
              auction={auction}
              isBelongToUser={false}
              placeBid={placeBid}
              user={user}
            />
          ) : null
        )}
      </div>
    </div>
  );
};
