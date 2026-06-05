"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import useCheckout from "@/hooks/useCheckout";
import { toast } from "sonner";

const BREAKFAST_UNIT_PRICE = 0.7;
const LUNCH_UNIT_PRICE = 1.45;

function formatCurrency(value: number): string {
  return new Intl.NumberFormat("pt-BR", {
    style: "currency",
    currency: "BRL",
  }).format(value);
}

export default function CheckoutPage() {
  const router = useRouter();
  const [quantities, setQuantities] = useState({
    breakfastQty: 0,
    lunchQty: 0,
  });

  const { createOrder, isCreating } = useCheckout();

  useEffect(() => {
    const storedData = sessionStorage.getItem("checkoutData");
    if (storedData) {
      const { breakfastQty, lunchQty } = JSON.parse(storedData);
      setQuantities({ breakfastQty, lunchQty });
    }
  }, []);

  const breakfastTotal = quantities.breakfastQty * BREAKFAST_UNIT_PRICE;
  const lunchTotal = quantities.lunchQty * LUNCH_UNIT_PRICE;
  const orderTotal = breakfastTotal + lunchTotal;

  async function handleConfirmOrder() {
    try {
      const payload = {
        breakfastAmount: quantities.breakfastQty,
        lunchDinnerAmount: quantities.lunchQty,
      };
      await createOrder(payload);
      router.push("/dashboard/checkout/payment");
    } catch (error) {
      toast.error("Erro ao criar o pedido. Por favor, tente novamente.");
    }
  }

  return (
    <section className="space-y-6">
      <header className="space-y-1">
        <h1 className="text-2xl font-semibold tracking-tight">Checkout</h1>
        <p className="text-sm text-muted-foreground">
          Revise seu pedido e finalize o pagamento via Pix.
        </p>
      </header>

      <Card>
        <CardHeader>
          <CardTitle>Resumo do Pedido</CardTitle>
        </CardHeader>
        <CardContent className="space-y-3 text-sm">
          <div className="flex items-center justify-between">
            <span>Café da manhã ({quantities.breakfastQty}x)</span>
            <span>{formatCurrency(BREAKFAST_UNIT_PRICE)}</span>
          </div>
          <div className="flex items-center justify-between">
            <span>Almoço/Janta ({quantities.lunchQty}x)</span>
            <span>{formatCurrency(LUNCH_UNIT_PRICE)}</span>
          </div>
          <div className="flex items-center justify-between border-t pt-3 text-base font-semibold">
            <span>Valor Total</span>
            <span>{formatCurrency(orderTotal)}</span>
          </div>
        </CardContent>
      </Card>

      <Button
        className="h-12 w-full text-base font-semibold"
        onClick={handleConfirmOrder}
        disabled={orderTotal === 0}
      >
        {isCreating ? "Processando..." : "Prosseguir para o pagamento"}
      </Button>
    </section>
  );
}
