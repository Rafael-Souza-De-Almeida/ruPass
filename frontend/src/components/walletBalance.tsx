import { Coffee, Utensils } from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";
import { useWallet } from "@/hooks/useStudentData";

type WalletBalanceProps = {
  wallet: NonNullable<ReturnType<typeof useWallet>["wallet"]>;
};

export default function WalletBalance({ wallet }: WalletBalanceProps) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Carteira do aluno</CardTitle>
      </CardHeader>
      <CardContent className="grid gap-3 sm:grid-cols-2">
        <div className="rounded-xl border p-4">
          <div className="mb-3 flex items-center gap-2">
            <Coffee className="h-4 w-4 text-yellow-500" />
            <p className="text-sm text-muted-foreground">
              Tickets de café da manhã
            </p>
          </div>
          <p className="text-4xl font-semibold leading-none">
            {wallet.breakfastBalance}
          </p>
          <p className="mt-1 text-xs text-muted-foreground">Tickets de Café</p>
        </div>

        <div className="rounded-xl border p-4">
          <div className="mb-3 flex items-center gap-2">
            <Utensils className="h-4 w-4 text-blue-500" />
            <p className="text-sm text-muted-foreground">
              Tickets de almoço e jantar
            </p>
          </div>
          <p className="text-4xl font-semibold leading-none">
            {wallet.lunchDinnerBalance}
          </p>
          <p className="mt-1 text-xs text-muted-foreground">
            Creditos de Almoço/Jantar
          </p>
        </div>
      </CardContent>
    </Card>
  );
}
