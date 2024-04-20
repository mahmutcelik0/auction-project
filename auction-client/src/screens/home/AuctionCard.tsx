import { FC } from "react";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Auction, OfferRequest, User } from "@/types";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Badge } from "@/components/ui/badge";

type AuctionCardProps = {
  auction: Auction;
  isBelongToUser: boolean;
  placeBid?: (offer: OfferRequest) => void;
  user: User;
};
const auctionCardSchema = z.object({
  bid: z.coerce.number().min(0.01, "Price must be greater than 0"),
});

export const AuctionCard: FC<AuctionCardProps> = ({
  auction,
  isBelongToUser,
  placeBid,
  user,
}) => {
  const form = useForm<z.infer<typeof auctionCardSchema>>({
    resolver: zodResolver(auctionCardSchema),
    defaultValues: {
      bid: auction.product.currentPrice,
    },
  });

  function onSubmit(values: z.infer<typeof auctionCardSchema>) {
    if (placeBid) {
      placeBid({
        auctionId: auction.id,
        offer: {
          offerPrice: values.bid,
          user,
        },
      });
    }
  }
  return (
    <Card>
      <CardHeader>
        <CardTitle className="text-xl">{auction.product.productName}</CardTitle>
        <CardDescription>
          by{" "}
          <span className="font-semibold text-primary ">
            {auction.user.username}
          </span>
        </CardDescription>
      </CardHeader>
      <CardContent>
        <div className="grid gap-3">
          <ul className="grid gap-3 text-sm">
            <li className="flex items-center justify-between">
              <span className="text-muted-foreground">Auction Status</span>
              <span>
                {auction.end ? (
                  <Badge variant={"destructive"}>closed</Badge>
                ) : (
                  <Badge variant={"success"}>open</Badge>
                )}
              </span>
            </li>
            <li className="flex items-center justify-between">
              <span className="text-muted-foreground">Base Price</span>
              <span>${auction.product.basePrice}</span>
            </li>
            <li className="flex items-center justify-between">
              <span className="text-muted-foreground">Current Price</span>
              <span>${auction.product.currentPrice}</span>
            </li>
            <li className="flex items-center justify-between">
              <span className="text-muted-foreground">Winner</span>
              <span>{auction.winner ? auction.winner.username : "-"}</span>
            </li>
          </ul>
        </div>
      </CardContent>
      <CardFooter>
        {isBelongToUser ? (
          <Button variant={"destructive"} className="w-full">
            End auction
          </Button>
        ) : (
          <Form {...form}>
            <form
              onSubmit={form.handleSubmit(onSubmit)}
              className="w-full grid gap-4"
            >
              <FormField
                control={form.control}
                name="bid"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Place a bid</FormLabel>
                    <FormControl>
                      <Input type="number" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <Button className="w-full" type="submit">
                Bid
              </Button>
            </form>
          </Form>
        )}
      </CardFooter>
    </Card>
  );
};
