import { Separator } from "@/components/ui/separator";
import { Auction, User } from "@/types";
import { FC, useState, useEffect } from "react";
import { AuctionCard } from "../home/AuctionCard";
import { useSubscription, useStompClient } from "react-stomp-hooks";

type AdminPageProps = {
  user: User;
};

export const AdminPage: FC<AdminPageProps> = ({ user }) => {
  const [lastMessage, setLastMessage] = useState("[]");
  const [auctions, setAuctions] = useState([]);
  useSubscription(`/auctions`, (message) =>
    setLastMessage(message.body ? message.body : "[]")
  );
  const stompClient = useStompClient();

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
          Welcome to auctions admin page{" "}
          <span className=" text-orange-500">{user.username}</span>
        </h2>
      </div>
      <Separator className="my-6" />
      <div className="pb-6 flex items-center justify-between">
        <h3 className="text-2xl font-semibold">Open Auctions</h3>
      </div>
      <div className="grid gap-4 grid-cols-3 ">
        {auctions.map((auction: Auction) =>
          !auction.end ? (
            <AuctionCard
              key={auction.id}
              auction={auction}
              isBelongToUser={false}
              user={user}
            />
          ) : null
        )}{" "}
      </div>
      <Separator className="my-6" />

      <div className="pb-6 flex items-center justify-between">
        <h3 className="text-2xl font-semibold">Closed Auctions</h3>
      </div>
      <div className="grid gap-4 grid-cols-3 ">
        {auctions.map((auction: Auction) =>
          auction.end ? (
            <AuctionCard
              key={auction.id}
              auction={auction}
              isBelongToUser={false}
              user={user}
            />
          ) : null
        )}{" "}
      </div>
    </div>
  );
};
