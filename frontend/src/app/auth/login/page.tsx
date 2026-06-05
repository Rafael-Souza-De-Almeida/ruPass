"use client";

import { FormEvent, useState } from "react";
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
import { useAuth } from "@/hooks/useAuth";
import Link from "next/link";

type FormValues = {
  email: string;
  password: string;
};

type FormErrors = {
  email?: string;
  password?: string;
  root?: string;
};

function validateLoginForm(values: FormValues): FormErrors {
  const errors: FormErrors = {};

  if (!values.email) {
    errors.email = "Informe o e-mail.";
  } else if (!/\S+@\S+\.\S+/.test(values.email)) {
    errors.email = "Informe um e-mail valido.";
  }

  return errors;
}

export default function LoginPage() {
  const { login, isLoading } = useAuth();
  const [values, setValues] = useState<FormValues>({
    email: "",
    password: "",
  });
  const [errors, setErrors] = useState<FormErrors>({});

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    const validationErrors = validateLoginForm(values);
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length > 0) {
      return;
    }

    try {
      await login(values);
    } catch (error: any) {
      setErrors({
        root: "Email ou senha inválidos. Tente novamente.",
      });
    }
  }

  return (
    <div className="flex min-h-screen items-center justify-center bg-muted/30 p-4">
      <Card className="w-full max-w-md">
        <CardHeader>
          <CardTitle>Entrar no RuPass</CardTitle>
          <CardDescription>
            Use seu e-mail institucional e senha para continuar.
          </CardDescription>
        </CardHeader>

        <form onSubmit={handleSubmit} noValidate>
          <CardContent className="space-y-4 pb-4">
            <div className="space-y-2">
              <Label htmlFor="email">E-mail</Label>
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

            {errors.root ? (
              <p className="text-sm text-destructive">{errors.root}</p>
            ) : null}
          </CardContent>

          <CardFooter className="flex flex-col justify-center gap-4">
            <Button
              className="w-full p-5 cursor-pointer"
              type="submit"
              disabled={isLoading}
            >
              {isLoading ? "Carregando..." : "Entrar"}
            </Button>
            <Link href="/auth/register" className="text-sm text-primary">
              Não tem uma conta?{" "}
              <span className="hover:underline">Registre-se</span>
            </Link>
          </CardFooter>
        </form>
      </Card>
    </div>
  );
}
