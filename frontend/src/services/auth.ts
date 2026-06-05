import api from "@/services/api";

export type LoginPayload = {
  email: string;
  password: string;
};

export type LoginResponse = {
  token: string;
};

export type RegisterStudentPayload = {
  fullName: string;
  email: string;
  password: string;
  registrationNumber: string;
  course: string;
  cpf: string;
};

export default class AuthService {
  async login(payload: LoginPayload): Promise<LoginResponse> {
    const response = await api.post<LoginResponse>("/auth/sign_in", payload);

    return response.data;
  }

  async registerStudent(payload: RegisterStudentPayload): Promise<void> {
    await api.post("/auth/sign_up", payload);
  }
}
