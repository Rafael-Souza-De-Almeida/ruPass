import axios from "axios";
import { getAuthToken } from "@/lib/auth-cookies";

const api = axios.create({
  baseURL: "http://localhost:8080/api/v1",
});

api.interceptors.request.use((config) => {
  const token = getAuthToken();

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

export default api;
export const fetcher = (url: string) => api.get(url).then((res) => res.data);
export const poster = (url: string, { arg }: { arg: any }) =>
  api.post(url, arg).then((res) => res.data);
export const patcher = (url: string, { arg }: { arg: any }) =>
  api.patch(url, arg).then((res) => res.data);
export const deleter = (url: string) => api.delete(url).then((res) => res.data);
