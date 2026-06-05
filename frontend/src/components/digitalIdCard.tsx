import { useDigitalId } from "@/hooks/useStudentData";
import Image from "next/image";

type DigitalIdCardProps = {
  data: NonNullable<ReturnType<typeof useDigitalId>["digitalId"]>;
};

export default function DigitalIdCard({ data }: DigitalIdCardProps) {
  return (
    <div className="flex min-h-52 items-center gap-4 rounded-2xl bg-[#006b3f] p-5 shadow-lg">
      <div className="flex h-20 w-20 shrink-0 items-center justify-center overflow-hidden rounded-full border-2 border-white/70 bg-white/15">
        {data.photoUrl ? (
          <Image
            src={data.photoUrl}
            alt={`Foto de ${data.fullName}`}
            className="h-full w-full object-cover"
          />
        ) : (
          <span className="text-xs font-semibold text-white/80">FOTO</span>
        )}
      </div>

      <div className="space-y-1 text-white">
        <p className="text-base font-semibold leading-tight">{data.fullName}</p>
        <p className="text-sm text-white/90">
          Matricula: {data.registrationNumber}
        </p>
        <p className="text-sm text-white/90">Curso: {data.course}</p>
      </div>
    </div>
  );
}
