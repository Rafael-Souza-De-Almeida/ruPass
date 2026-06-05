import { useEffect, useState } from "react";
import { Badge } from "./ui/badge";
import { useOrderHistory } from "@/hooks/useStudentData";

import { Card, CardHeader, CardTitle, CardContent } from "./ui/card";
import { Skeleton } from "./ui/skeleton";
import {
  TableHeader,
  TableRow,
  TableHead,
  TableBody,
  TableCell,
  Table,
} from "./ui/table";
import { Button } from "./ui/button";

type OrderStatus = "PENDING" | "APPROVED" | "REJECTED" | "CANCELLED";

function StatusBadge({ status }: { status: OrderStatus }) {
  if (status === "APPROVED") {
    return (
      <Badge className="bg-emerald-600 hover:bg-emerald-600">Aprovado</Badge>
    );
  }

  if (status === "PENDING") {
    return <Badge className="bg-amber-500 hover:bg-amber-500">Pendente</Badge>;
  }

  if (status === "CANCELLED") {
    return <Badge className="bg-red-500 hover:bg-red-500">Cancelado</Badge>;
  }

  return <Badge variant="destructive">Rejeitado</Badge>;
}

export default function OrderHistory() {
  const [isMounted, setIsMounted] = useState(false);
  const [page, setPage] = useState(0);
  const { data, isLoading, error } = useOrderHistory(page);

  useEffect(() => {
    setIsMounted(true);
  }, []);

  if (!isMounted) {
    return null;
  }

  if (isLoading) {
    return (
      <Card>
        <CardHeader>
          <CardTitle>Order History</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="space-y-2">
            <Skeleton className="h-10 w-full" />
            <Skeleton className="h-12 w-full" />
            <Skeleton className="h-12 w-full" />
            <Skeleton className="h-12 w-full" />
          </div>
          <div className="flex items-center justify-between mt-4">
            <Skeleton className="h-9 w-24" />
            <Skeleton className="h-5 w-40" />
            <Skeleton className="h-9 w-24" />
          </div>
        </CardContent>
      </Card>
    );
  }

  if (error || !data) {
    return (
      <Card>
        <CardHeader>
          <CardTitle>Order History</CardTitle>
        </CardHeader>
        <CardContent className="text-sm text-muted-foreground">
          Nao foi possivel carregar o historico de pedidos.
        </CardContent>
      </Card>
    );
  }

  return (
    <Card>
      <CardHeader>
        <CardTitle>Histórico de compras</CardTitle>
      </CardHeader>
      <CardContent>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Data</TableHead>
              <TableHead>Descrição</TableHead>
              <TableHead>Valor Total</TableHead>
              <TableHead>Status</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data.content.map((order) => (
              <TableRow key={order.orderId}>
                <TableCell>
                  {new Date(order.createdAt).toLocaleDateString("pt-BR")}
                </TableCell>
                <TableCell>
                  {order.breakfastQuantity > 0 && (
                    <span>
                      {order.breakfastQuantity}x Café da manhã
                      {order.lunchDinnerQuantity > 0 && <br />}
                    </span>
                  )}
                  {order.lunchDinnerQuantity > 0 && (
                    <span>{order.lunchDinnerQuantity}x Almoço/Jantar</span>
                  )}
                </TableCell>
                <TableCell>
                  {order.totalAmount.toLocaleString("pt-BR", {
                    style: "currency",
                    currency: "BRL",
                  })}
                </TableCell>
                <TableCell>
                  <StatusBadge status={order.status} />
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <div className="flex justify-between items-center mt-4">
          <Button
            variant="outline"
            disabled={data.first}
            onClick={() => setPage((p) => p - 1)}
          >
            Anterior
          </Button>
          <span className="text-sm text-muted-foreground">
            Pagina {data.number + 1} de{" "}
            {data.totalPages > 0 ? data.totalPages : 1}
          </span>
          <Button
            variant="outline"
            disabled={data.last}
            onClick={() => setPage((p) => p + 1)}
          >
            Proxima
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
