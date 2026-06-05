"use client";

import { Card, CardContent } from "@/components/ui/card";

import { useDigitalId, useWallet } from "@/hooks/useStudentData";

import DigitalIdCard from "@/components/digitalIdCard";
import WalletBalance from "@/components/walletBalance";
import TicketStore from "@/components/ticketStore";
import OrderHistory from "@/components/orderHistory";
import DigitalIdSkeleton from "@/components/digitalIdSkeleton";
import WalletBalanceSkeleton from "@/components/walletBalanceSkeleton";

export default function DashboardPage() {
  const {
    digitalId,
    isLoading: isLoadingDigitalId,
    error: digitalIdError,
  } = useDigitalId();
  const {
    wallet,
    isLoading: isLoadingWallet,
    error: walletError,
  } = useWallet();

  return (
    <section className="space-y-6">
      <header className="space-y-1">
        <h1 className="text-2xl font-semibold tracking-tight">Visão Geral</h1>
        <p className="text-sm text-muted-foreground">
          Acompanhe seus dados principais e gerencie seus tickets de refeição.
        </p>
      </header>

      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-3">
        <div className="sm:col-span-2 xl:col-span-2">
          {digitalIdError ? (
            <Card>
              <CardContent className="flex min-h-52 items-center justify-center text-sm text-muted-foreground">
                Não foi possível carregar a carteirinha digital. Tente novamente
                em instantes.
              </CardContent>
            </Card>
          ) : isLoadingDigitalId || !digitalId ? (
            <DigitalIdSkeleton />
          ) : (
            <DigitalIdCard data={digitalId} />
          )}
        </div>

        <div className="sm:col-span-2 xl:col-span-2">
          {walletError ? (
            <Card>
              <CardContent className="flex min-h-52 items-center justify-center text-sm text-muted-foreground">
                Não foi possível carregar a carteira de tickets. Tente novamente
                em instantes.
              </CardContent>
            </Card>
          ) : isLoadingWallet || !wallet ? (
            <WalletBalanceSkeleton />
          ) : (
            <WalletBalance wallet={wallet} />
          )}
        </div>

        <TicketStore />
      </div>

      <OrderHistory />
    </section>
  );
}
