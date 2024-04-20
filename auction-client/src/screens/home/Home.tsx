import { useState, useEffect } from "react";
import { User } from "@/types";
import { useSubscription, useStompClient } from "react-stomp-hooks";
// import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";
import { AuctionCard } from "./AuctionCard";
type Props = {
  user: User;
};
export const Home = ({ user }: Props) => {
  const [lastMessage, setLastMessage] = useState("");
  useSubscription("/join/admin", (message) => setLastMessage(message.body));

  // const stompClient = useStompClient();
  useEffect(() => {
    console.log(lastMessage);
  }, [lastMessage]);

  return (
    <div>
      <div className="pt-6">
        <h2 className="text-3xl font-semibold">
          Welcome to auctions{" "}
          <span className=" text-orange-500">{user.userName}</span>
        </h2>
      </div>
      <Separator className="my-6" />
      <div className="pb-6 flex items-center justify-between">
        <h3 className="text-2xl font-semibold">Your auctions</h3>
        <Button>Create a new auction</Button>
      </div>

      <div className="grid gap-4 grid-cols-3 ">
        <AuctionCard />
        <AuctionCard />
        <AuctionCard />
      </div>
      <Separator className="my-6" />

      <div>
        <h3 className="text-xl font-semibold">Active auctions</h3>
      </div>

      {/* <Button
        onClick={() =>
          stompClient?.publish({
            destination: "/app/newAuction",
            body: JSON.stringify({
              product: {
                productName: "KAHVE",
                basePrice: 10,
              },
              user: {
                id: 1,
                firstName: "Mahmut",
                lastName: "Çelik",
              },
            }),
          })
        }
      >
        Send
      </Button> */}
    </div>
  );
};
