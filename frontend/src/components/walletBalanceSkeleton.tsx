import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";
import { Skeleton } from "./ui/skeleton";

export default function WalletBalanceSkeleton() {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Carteira do aluno</CardTitle>
      </CardHeader>
      <CardContent className="grid gap-3 sm:grid-cols-2">
        <div className="rounded-xl border p-4">
          <div className="mb-3 flex items-center gap-2">
            <Skeleton className="h-4 w-4 rounded-full" />
            <Skeleton className="h-4 w-40" />
          </div>
          <Skeleton className="h-10 w-12" />
          <Skeleton className="mt-2 h-3 w-24" />
        </div>
        <div className="rounded-xl border p-4">
          <div className="mb-3 flex items-center gap-2">
            <Skeleton className="h-4 w-4 rounded-full" />
            <Skeleton className="h-4 w-44" />
          </div>
          <Skeleton className="h-10 w-12" />
          <Skeleton className="mt-2 h-3 w-32" />
        </div>
      </CardContent>
    </Card>
  );
}
