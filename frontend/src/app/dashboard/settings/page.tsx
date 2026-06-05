"use client";

import { FormEvent, useState, useEffect, use } from "react";
import { useRouter } from "next/navigation";
import { toast } from "sonner";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { removeAuthToken } from "@/lib/auth-cookies";
import {
  EditStudentData,
  StudentData,
  useDeleteStudent,
  useEditStudent,
  useStudentData,
} from "@/hooks/useStudentData";

type ProfileFormValues = {
  fullName: string;
  email: string;
  password?: string;
  confirmPassword?: string;
};

export default function SettingsPage() {
  const router = useRouter();
  const { updatedStudent } = useEditStudent();
  const { deletedStudent } = useDeleteStudent();
  const { studentData } = useStudentData();
  const [isSaving, setIsSaving] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);
  const [values, setValues] = useState<ProfileFormValues>({
    fullName: studentData?.fullName || "",
    email: studentData?.email || "",
    password: "",
    confirmPassword: "",
  });

  useEffect(() => {
    if (studentData) {
      setValues({
        fullName: studentData.fullName,
        email: studentData.email,
        password: "",
        confirmPassword: "",
      });
    }
  }, [studentData]);

  async function handleSaveChanges(
    event: FormEvent<HTMLFormElement>,
    updatedData: EditStudentData,
  ) {
    event.preventDefault();

    try {
      setIsSaving(true);

      if (values.password && values.password.length < 8) {
        toast.error("A senha deve conter pelo menos 8 caracteres.");
        return;
      }

      if (values.password && values.password !== values.confirmPassword) {
        toast.error("As senhas não coincidem.");
        return;
      }

      await updatedStudent(updatedData);

      toast.success("Perfil atualizado com sucesso.");
      router.push("/dashboard");
    } catch (error: any) {
      const message = error.response?.data?.message || error.message;
      toast.error(message || "Ocorreu um erro ao atualizar o perfil.");
    } finally {
      setIsSaving(false);
    }
  }

  async function handleDeleteAccount() {
    setIsDeleting(true);

    try {
      await deletedStudent();
    } catch (error: any) {
      const message = error.response?.data?.message || error.message;
      toast.error(message || "Ocorreu um erro ao excluir a conta.");
      return;
    } finally {
      setIsDeleting(false);
    }

    removeAuthToken();
    toast.success("Conta excluída com sucesso.");
    router.replace("/auth/login");
  }

  return (
    <section className="space-y-6">
      <header className="space-y-1">
        <h1 className="text-2xl font-semibold tracking-tight">Configurações</h1>
        <p className="text-sm text-muted-foreground">
          Gerencie seus dados de perfil e configuracões da conta.
        </p>
      </header>

      <Card>
        <CardHeader>
          <CardTitle>Editar Perfil</CardTitle>
        </CardHeader>
        <CardContent>
          <form
            className="space-y-4"
            onSubmit={(event) => handleSaveChanges(event, values)}
          >
            <div className="space-y-2">
              <Label htmlFor="nome">Nome</Label>
              <Input
                id="nome"
                value={values.fullName}
                onChange={(event) =>
                  setValues((prev) => ({
                    ...prev,
                    fullName: event.target.value,
                  }))
                }
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="email">Email</Label>
              <Input
                id="email"
                type="email"
                value={values.email}
                onChange={(event) =>
                  setValues((prev) => ({ ...prev, email: event.target.value }))
                }
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="password">Senha</Label>
              <Input
                id="password"
                type="password"
                value={values.password}
                onChange={(event) =>
                  setValues((prev) => ({
                    ...prev,
                    password: event.target.value,
                  }))
                }
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="confirmPassword">Confirmar Senha</Label>
              <Input
                id="confirmPassword"
                type="password"
                value={values.confirmPassword}
                onChange={(event) =>
                  setValues((prev) => ({
                    ...prev,
                    confirmPassword: event.target.value,
                  }))
                }
              />
            </div>

            <Button type="submit" disabled={isSaving}>
              {isSaving ? "Salvando..." : "Salvar Alterações"}
            </Button>
          </form>
        </CardContent>
      </Card>

      <Card className="border-red-300">
        <CardHeader>
          <CardTitle className="text-red-700">Zona de Perigo</CardTitle>
        </CardHeader>
        <CardContent className="space-y-3">
          <p className="text-sm text-muted-foreground">
            A exclusão da conta é permanente e não pode ser desfeita.
          </p>

          <AlertDialog>
            <AlertDialogTrigger render={<Button variant="destructive" />}>
              Excluir Conta
            </AlertDialogTrigger>
            <AlertDialogContent>
              <AlertDialogHeader>
                <AlertDialogTitle>
                  Tem certeza que deseja excluir?
                </AlertDialogTitle>
                <AlertDialogDescription>
                  Essa ação remove sua conta permanentemente. Confirme para
                  continuar.
                </AlertDialogDescription>
              </AlertDialogHeader>
              <AlertDialogFooter>
                <AlertDialogCancel disabled={isDeleting}>
                  Cancelar
                </AlertDialogCancel>
                <AlertDialogAction
                  className="bg-red-600 hover:bg-red-600/90"
                  onClick={handleDeleteAccount}
                  disabled={isDeleting}
                >
                  {isDeleting ? "Excluindo..." : "Confirmar exclusão"}
                </AlertDialogAction>
              </AlertDialogFooter>
            </AlertDialogContent>
          </AlertDialog>
        </CardContent>
      </Card>
    </section>
  );
}
