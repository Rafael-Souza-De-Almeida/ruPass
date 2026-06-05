"use client";

import { FormEvent, useState } from "react";
import { useRouter } from "next/navigation";
import { Check, ChevronsUpDown } from "lucide-react";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from "@/components/ui/command";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { cn } from "@/lib/utils";
import AuthService from "@/services/auth";
import Link from "next/link";
import axios from "axios";

const COURSE_OPTIONS = [
  "MEDICINA",
  "DIREITO",
  "ENGENHARIA_CIVIL",
  "ENGENHARIA_DE_SOFTWARE",
  "CIENCIA_DA_COMPUTACAO",
  "SISTEMAS_DE_INFORMACAO",
  "ADMINISTRACAO",
  "ECONOMIA",
  "ARQUITETURA_E_URBANISMO",
  "PSICOLOGIA",
  "ODONTOLOGIA",
  "ENFERMAGEM",
  "FARMACIA",
  "JORNALISMO",
  "PUBLICIDADE_E_PROPAGANDA",
  "DESIGN_GRAFICO",
  "EDUCACAO_FISICA",
  "RELACOES_INTERNACIONAIS",
  "CONTABILIDADE",
  "BIOMEDICINA",
] as const;

type RegisterValues = {
  fullName: string;
  email: string;
  password: string;
  cpf: string;
  registrationNumber: string;
  course: string;
};

type RegisterErrors = Partial<Record<keyof RegisterValues, string>> & {
  root?: string;
};

function applyCpfMask(value: string): string {
  const digitsOnly = value.replace(/\D/g, "").slice(0, 11);
  const part1 = digitsOnly.slice(0, 3);
  const part2 = digitsOnly.slice(3, 6);
  const part3 = digitsOnly.slice(6, 9);
  const part4 = digitsOnly.slice(9, 11);

  if (digitsOnly.length <= 3) return part1;
  if (digitsOnly.length <= 6) return `${part1}.${part2}`;
  if (digitsOnly.length <= 9) return `${part1}.${part2}.${part3}`;
  return `${part1}.${part2}.${part3}-${part4}`;
}

function validateRegisterForm(values: RegisterValues): RegisterErrors {
  const errors: RegisterErrors = {};

  if (!values.fullName.trim()) {
    errors.fullName = "Informe o nome completo.";
  }

  if (!values.email) {
    errors.email = "Informe o e-mail.";
  } else if (!/\S+@\S+\.\S+/.test(values.email)) {
    errors.email = "Informe um e-mail valido.";
  }

  if (!values.password) {
    errors.password = "Informe a senha.";
  } else if (values.password.length < 8) {
    errors.password = "A senha deve ter pelo menos 8 caracteres.";
  }

  if (values.cpf.replace(/\D/g, "").length !== 11) {
    errors.cpf = "Informe um CPF valido.";
  }

  if (!values.registrationNumber.trim()) {
    errors.registrationNumber = "Informe a matricula.";
  }

  if (!values.course) {
    errors.course = "Selecione um curso.";
  } else if (
    !COURSE_OPTIONS.includes(values.course as (typeof COURSE_OPTIONS)[number])
  ) {
    errors.course = "Selecione um curso valido.";
  }

  return errors;
}

export default function RegisterPage() {
  const router = useRouter();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isCourseOpen, setIsCourseOpen] = useState(false);
  const [values, setValues] = useState<RegisterValues>({
    fullName: "",
    email: "",
    password: "",
    cpf: "",
    registrationNumber: "",
    course: "",
  });
  const [errors, setErrors] = useState<RegisterErrors>({});

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    const validationErrors = validateRegisterForm(values);
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length > 0) {
      return;
    }

    try {
      const auth = new AuthService();

      setIsSubmitting(true);
      await auth.registerStudent({
        fullName: values.fullName.trim(),
        email: values.email.trim(),
        password: values.password,
        registrationNumber: values.registrationNumber.trim(),
        course: values.course,
        cpf: values.cpf,
      });

      toast.success("Cadastro realizado com sucesso!");
      router.push("/login");
    } catch (error) {
      let errorMessage =
        "Nao foi possivel finalizar o cadastro. Tente novamente.";

      if (axios.isAxiosError(error) && error.response?.data?.message) {
        errorMessage = error.response.data.message;
      }

      setErrors({
        root: errorMessage,
      });
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <div className="flex min-h-screen items-center justify-center bg-muted/30 p-4">
      <Card className="w-full max-w-md">
        <CardHeader>
          <CardTitle>Criar conta no RuPass</CardTitle>
          <CardDescription>
            Preencha seus dados para cadastrar um novo estudante.
          </CardDescription>
        </CardHeader>

        <form onSubmit={handleSubmit} noValidate>
          <CardContent className="space-y-4 pb-4">
            <div className="space-y-2">
              <Label htmlFor="fullName">Nome completo</Label>
              <Input
                id="fullName"
                value={values.fullName}
                onChange={(event) =>
                  setValues((prev) => ({
                    ...prev,
                    fullName: event.target.value,
                  }))
                }
              />
              {errors.fullName ? (
                <p className="text-sm text-destructive">{errors.fullName}</p>
              ) : null}
            </div>

            <div className="space-y-2">
              <Label htmlFor="email">Email</Label>
              <Input
                id="email"
                type="email"
                placeholder="voce@ufrrj.br"
                value={values.email}
                onChange={(event) =>
                  setValues((prev) => ({ ...prev, email: event.target.value }))
                }
              />
              {errors.email ? (
                <p className="text-sm text-destructive">{errors.email}</p>
              ) : null}
            </div>

            <div className="space-y-2">
              <Label htmlFor="password">Senha</Label>
              <Input
                id="password"
                type="password"
                placeholder="********"
                value={values.password}
                onChange={(event) =>
                  setValues((prev) => ({
                    ...prev,
                    password: event.target.value,
                  }))
                }
              />
              {errors.password ? (
                <p className="text-sm text-destructive">{errors.password}</p>
              ) : null}
            </div>

            <div className="space-y-2">
              <Label htmlFor="cpf">CPF</Label>
              <Input
                id="cpf"
                inputMode="numeric"
                placeholder="000.000.000-00"
                value={values.cpf}
                onChange={(event) =>
                  setValues((prev) => ({
                    ...prev,
                    cpf: applyCpfMask(event.target.value),
                  }))
                }
              />
              {errors.cpf ? (
                <p className="text-sm text-destructive">{errors.cpf}</p>
              ) : null}
            </div>

            <div className="space-y-2">
              <Label htmlFor="registrationNumber">Matrícula</Label>
              <Input
                id="registrationNumber"
                value={values.registrationNumber}
                onChange={(event) =>
                  setValues((prev) => ({
                    ...prev,
                    registrationNumber: event.target.value,
                  }))
                }
              />
              {errors.registrationNumber ? (
                <p className="text-sm text-destructive">
                  {errors.registrationNumber}
                </p>
              ) : null}
            </div>

            <div className="space-y-2">
              <Label>Curso</Label>
              <Popover open={isCourseOpen} onOpenChange={setIsCourseOpen}>
                <PopoverTrigger
                  render={
                    <Button
                      type="button"
                      variant="outline"
                      role="combobox"
                      className="w-full justify-between"
                    />
                  }
                >
                  {values.course || "Selecione um curso..."}
                  <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                </PopoverTrigger>
                <PopoverContent className="w-[360px] max-w-[calc(100vw-2rem)] p-0">
                  <Command>
                    <CommandInput placeholder="Buscar curso..." />
                    <CommandList>
                      <CommandEmpty>Nenhum curso encontrado.</CommandEmpty>
                      <CommandGroup>
                        {COURSE_OPTIONS.map((course) => (
                          <CommandItem
                            key={course}
                            value={course}
                            onSelect={(currentValue) => {
                              const normalizedValue =
                                currentValue.toUpperCase();
                              const selectedCourse = COURSE_OPTIONS.find(
                                (option) => option === normalizedValue,
                              );

                              if (!selectedCourse) {
                                return;
                              }

                              setValues((prev) => ({
                                ...prev,
                                course: selectedCourse,
                              }));
                              setIsCourseOpen(false);
                            }}
                          >
                            {course}
                            <Check
                              className={cn(
                                "ml-auto h-4 w-4",
                                values.course === course
                                  ? "opacity-100"
                                  : "opacity-0",
                              )}
                            />
                          </CommandItem>
                        ))}
                      </CommandGroup>
                    </CommandList>
                  </Command>
                </PopoverContent>
              </Popover>
              {errors.course ? (
                <p className="text-sm text-destructive">{errors.course}</p>
              ) : null}
            </div>

            {errors.root ? (
              <p className="text-sm text-destructive">{errors.root}</p>
            ) : null}
          </CardContent>

          <CardFooter className="flex flex-col justify-center gap-4">
            <Button
              className="w-full p-5"
              type="submit"
              disabled={isSubmitting}
            >
              {isSubmitting ? "Cadastrando..." : "Cadastrar"}
            </Button>
            <div>
              <span className="text-sm text-primary">
                Já possui uma conta?{" "}
              </span>
              <Link href="/auth/login" className="text-sm text-primary">
                <span className="hover:underline">Entre aqui</span>
              </Link>
            </div>
          </CardFooter>
        </form>
      </Card>
    </div>
  );
}
