import type { CreateTaskInput, Task, TaskSort } from "@/types/task";

// Verbindet das Frontend mit dem Spring-Boot-Backend.
const API_URL = (
  process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080"
).replace(/\/$/, "");

// Führt HTTP-Anfragen aus und behandelt gemeinsame Fehler.
async function request<T>(path: string, options?: RequestInit): Promise<T> {
  const response = await fetch(`${API_URL}${path}`, options);

  if (!response.ok) {
    let message = "Die Anfrage an das Backend ist fehlgeschlagen.";

    try {
      const error = (await response.json()) as { message?: string };
      if (error.message) {
        message = error.message;
      }
    } catch {
      // Die verständliche Standardmeldung bleibt erhalten.
    }

    throw new Error(message);
  }

  if (response.status === 204) {
    return undefined as T;
  }

  return (await response.json()) as T;
}

// Lädt alle Aufgaben oder eine vom Backend sortierte Liste.
export function getTasks(sort?: TaskSort): Promise<Task[]> {
  const query = sort ? `?sort=${sort}` : "";
  return request<Task[]>(`/api/tasks${query}`);
}

// Schickt eine neue Aufgabe als JSON an das Backend.
export function createTask(input: CreateTaskInput): Promise<Task> {
  return request<Task>("/api/tasks", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(input),
  });
}

// Markiert eine Aufgabe über die REST-API als erledigt.
export function completeTask(id: number): Promise<Task> {
  return request<Task>(`/api/tasks/${id}/complete`, { method: "PATCH" });
}

// Schickt die Löschanfrage für eine Aufgabe an das Backend.
export function deleteTask(id: number): Promise<void> {
  return request<void>(`/api/tasks/${id}`, { method: "DELETE" });
}
