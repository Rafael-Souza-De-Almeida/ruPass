import { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";

import { Button } from "./ui/button";
import { useRouter } from "next/navigation";

export default function TicketStore() {
  const [breakfastQty, setBreakfastQty] = useState(0);
  const [lunchQty, setLunchQty] = useState(0);
  const router = useRouter();

  function handleCheckout() {
    sessionStorage.setItem(
      "checkoutData",
      JSON.stringify({ breakfastQty, lunchQty }),
    );
    router.push("/dashboard/checkout");
  }

  function decreaseBreakfast() {
    setBreakfastQty((prev) => Math.max(0, prev - 1));
  }

  function increaseBreakfast() {
    setBreakfastQty((prev) => prev + 1);
  }

  function decreaseLunch() {
    setLunchQty((prev) => Math.max(0, prev - 1));
  }

  function increaseLunch() {
    setLunchQty((prev) => prev + 1);
  }

  return (
    <Card id="ticket-store" className="sm:col-span-2 xl:col-span-1">
      <CardHeader>
        <CardTitle>Comprar Tickets</CardTitle>
      </CardHeader>
      <CardContent className="space-y-3">
        <div className="rounded-xl border border-dashed border-yellow-300 bg-gradient-to-r from-yellow-100 to-yellow-50 p-4">
          <p className="text-sm font-medium">Ticket de café da manhã</p>
          <p className="mt-1 text-sm text-muted-foreground">
            Preco unitário: R$ 0,70
          </p>
          <div className="mt-4 flex items-center gap-3">
            <Button
              type="button"
              variant="outline"
              size="icon"
              onClick={decreaseBreakfast}
            >
              -
            </Button>
            <span className="min-w-8 text-center text-2xl font-semibold">
              {breakfastQty}
            </span>
            <Button
              type="button"
              variant="outline"
              size="icon"
              onClick={increaseBreakfast}
            >
              +
            </Button>
          </div>
        </div>

        <div className="rounded-xl border border-dashed border-blue-300 bg-gradient-to-r from-blue-100 to-blue-50 p-4">
          <p className="text-sm font-medium">Ticket de almoço e jantar</p>
          <p className="mt-1 text-sm text-muted-foreground">
            Preco unitário: R$ 1,45
          </p>
          <div className="mt-4 flex items-center gap-3">
            <Button
              type="button"
              variant="outline"
              size="icon"
              onClick={decreaseLunch}
            >
              -
            </Button>
            <span className="min-w-8 text-center text-2xl font-semibold">
              {lunchQty}
            </span>
            <Button
              type="button"
              variant="outline"
              size="icon"
              onClick={increaseLunch}
            >
              +
            </Button>
          </div>
        </div>

        <Button
          onClick={handleCheckout}
          className="h-12 w-full text-base font-semibold"
          type="button"
          disabled={breakfastQty === 0 && lunchQty === 0}
        >
          Comprar com Pix
        </Button>
      </CardContent>
    </Card>
  );
}
