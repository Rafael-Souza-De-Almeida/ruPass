"use client";

import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";

import { QrCode, Copy } from "lucide-react";
import { toast } from "sonner";

export default function PaymentPage() {
  const PIX_KEY_PLACEHOLDER = "pix-rupass@ufrrj.br";

  async function handleCopyPixKey() {
    try {
      await navigator.clipboard.writeText(PIX_KEY_PLACEHOLDER);
      toast.success("Chave Pix copiada!");
    } catch {
      toast.error("Nao foi possivel copiar a chave Pix.");
    }
  }

  return (
    <Card>
      <CardHeader>
        <CardTitle>Pagamento via Pix</CardTitle>
      </CardHeader>
      <CardContent className="space-y-4">
        <div className="flex h-56 items-center justify-center rounded-xl bg-muted">
          <div className="flex h-36 w-36 items-center justify-center rounded-md border-2 border-dashed border-muted-foreground/40 bg-gray-200">
            <QrCode className="h-12 w-12 text-muted-foreground" />
          </div>
        </div>

        <div className="flex items-center justify-between rounded-lg border p-3 text-sm">
          <span className="truncate text-muted-foreground">
            {PIX_KEY_PLACEHOLDER}
          </span>
          <Button
            type="button"
            variant="outline"
            size="sm"
            onClick={handleCopyPixKey}
          >
            <Copy className="mr-2 h-4 w-4" />
            Copiar Chave Pix
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
