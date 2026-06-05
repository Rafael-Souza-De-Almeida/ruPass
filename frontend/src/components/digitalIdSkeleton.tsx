import { Skeleton } from "./ui/skeleton";

export default function DigitalIdSkeleton() {
  return (
    <div className="flex min-h-52 items-center gap-4 rounded-2xl bg-[#006b3f] p-5 shadow-lg">
      <Skeleton className="h-20 w-20 shrink-0 rounded-full bg-white/20" />
      <div className="w-full space-y-3">
        <Skeleton className="h-5 w-3/4 bg-white/20" />
        <Skeleton className="h-4 w-2/3 bg-white/20" />
        <Skeleton className="h-4 w-1/2 bg-white/20" />
      </div>
    </div>
  );
}
