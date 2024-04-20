import { FC, useState } from "react";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogTrigger,
} from "@/components/ui/dialog";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { Auction, User } from "@/types";
import { v4 as uuid } from "uuid";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
const auctionCreateSchema = z.object({
  title: z.string().min(2).max(50),
  basePrice: z.coerce.number().min(0.01, "Price must be greater than 0"),
});

type AuctionCreateDialogProps = {
  user: User;
  createAuction: (data: Auction) => void;
};

export const AuctionCreateDialog: FC<AuctionCreateDialogProps> = ({
  user,
  createAuction,
}) => {
  const [open, setOpen] = useState(false);
  const form = useForm<z.infer<typeof auctionCreateSchema>>({
    resolver: zodResolver(auctionCreateSchema),
    defaultValues: {
      title: "",
      basePrice: 0,
    },
  });

  function onSubmit(values: z.infer<typeof auctionCreateSchema>) {
    const auction: Auction = {
      id: uuid(),
      product: {
        productName: values.title,
        basePrice: values.basePrice,
        currentPrice: values.basePrice,
      },
      user,
    };
    createAuction(auction);
    setOpen(false);
  }
  return (
    <Dialog
      open={open}
      onOpenChange={() => {
        setOpen(!open);
      }}
    >
      <DialogTrigger asChild>
        <Button>Create new auction</Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-[425px]">
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)}>
            <div className="grid gap-4 py-4">
              <FormField
                control={form.control}
                name="title"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Product Name</FormLabel>
                    <FormControl>
                      <Input placeholder="Mona lisa" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="basePrice"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Base Price</FormLabel>
                    <FormControl>
                      <Input type="number" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <DialogFooter>
              <Button type="submit">Create auction</Button>
            </DialogFooter>
          </form>
        </Form>
      </DialogContent>
    </Dialog>
  );
};
