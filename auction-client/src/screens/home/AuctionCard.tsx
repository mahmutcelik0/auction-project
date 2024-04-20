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
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { Input } from "@/components/ui/input";
import { Badge } from "@/components/ui/badge";
import { Separator } from "@/components/ui/separator";
import moment from "moment";

type AuctionCardProps = {
  auction: Auction;
  isBelongToUser: boolean;
  placeBid?: (offer: OfferRequest) => void;
  endAuction?: (user: User, auctionId: string) => void;
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
  endAuction,
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
              <span>
                {auction.winnerUser ? auction.winnerUser.username : "-"}
              </span>
            </li>
          </ul>
        </div>
      </CardContent>
      <CardFooter>
        {user.role === "ADMIN" ? (
          <Popover>
            <PopoverTrigger asChild>
              <Button className="w-full">See all bids</Button>
            </PopoverTrigger>
            <PopoverContent className="w-96">
              <ul className="grid gap-3 text-sm">
                {auction.logRecords?.map((log, index) => (
                  <>
                    <li
                      key={index}
                      className="flex justify-between items-center"
                    >
                      <div>
                        <span className="font-semibold">
                          {log.user.username}{" "}
                        </span>
                        <span>{log.message}</span>
                      </div>

                      <span className="ml-auto text-muted-foreground">
                        {moment(log.time).fromNow()}
                      </span>
                    </li>
                    {auction.logRecords &&
                      index < auction.logRecords.length - 1 && <Separator />}
                  </>
                ))}
              </ul>
            </PopoverContent>
          </Popover>
        ) : isBelongToUser ? (
          <Button
            variant={"destructive"}
            className="w-full"
            onClick={() => {
              if (endAuction) {
                endAuction(user, auction.id);
              }
            }}
            disabled={auction.end}
          >
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
                      <Input type="number" disabled={auction.end} {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <Button
                className="w-full"
                type="submit"
                disabled={
                  auction.end ||
                  form.watch("bid") <= auction.product.currentPrice
                }
              >
                Bid
              </Button>
            </form>
          </Form>
        )}
      </CardFooter>
    </Card>
  );
};
