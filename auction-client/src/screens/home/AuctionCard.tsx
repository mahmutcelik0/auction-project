import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";

export const AuctionCard = () => {
  return (
    <Card>
      <CardHeader>
        <CardTitle className="text-xl">Random bir eser</CardTitle>
        <CardDescription>
          by <span className="font-semibold text-primary ">Mahmut Çelik</span>
        </CardDescription>
      </CardHeader>
      <CardContent>
        <div className="grid gap-3">
          <ul className="grid gap-3 text-sm">
            <li className="flex items-center justify-between">
              <span className="text-muted-foreground">Base Price</span>
              <span>$49.00</span>
            </li>
            <li className="flex items-center justify-between">
              <span className="text-muted-foreground">Current Price</span>
              <span>$250.00</span>
            </li>
          </ul>
        </div>
      </CardContent>
      <CardFooter className="grid gap-2 grid-cols-2">
        <Button variant={"destructive"}>End auction</Button>
        <Button>Place a bid</Button>
      </CardFooter>
    </Card>
  );
};
