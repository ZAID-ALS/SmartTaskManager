import type { CreateTaskInput, Task, TaskSort } from "@/types/task";

const API_URL = (
  process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080"
).replace(/\/$/, "");

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

export function getTasks(sort?: TaskSort): Promise<Task[]> {
  const query = sort ? `?sort=${sort}` : "";
  return request<Task[]>(`/api/tasks${query}`);
}

export function createTask(input: CreateTaskInput): Promise<Task> {
  return request<Task>("/api/tasks", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(input),
  });
}

export function completeTask(id: number): Promise<Task> {
  return request<Task>(`/api/tasks/${id}/complete`, { method: "PATCH" });
}

export function deleteTask(id: number): Promise<void> {
  return request<void>(`/api/tasks/${id}`, { method: "DELETE" });
}
