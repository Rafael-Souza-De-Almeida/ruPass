import useSWRMutation from "swr/mutation";
import { useAuth } from "./useAuth";
import { getStudentIdFromToken } from "./useStudentData";
import { fetcher, poster } from "@/services/api";

interface CheckoutOrderResponse {
  orderId: string;
  totalAmount: number;
  status: "PENDING" | "APPROVED" | "REJECTED" | "CANCELLED";
  message: string;
}

export default function useCheckout() {
  const { token } = useAuth();
  const studentId = getStudentIdFromToken(token);
  const endpoint = studentId ? `/students/${studentId}/orders` : null;

  const { trigger, isMutating, error } = useSWRMutation(endpoint, poster);

  return {
    createOrder: trigger,
    isCreating: isMutating,
    error,
  };
}
