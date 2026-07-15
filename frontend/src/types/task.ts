// Erlaubte Werte für die Priorität.
export type Priority = "HIGH" | "MEDIUM" | "LOW";

// Erlaubte Zustände einer Aufgabe.
export type TaskStatus = "NEW" | "IN_PROGRESS" | "COMPLETED" | "DELETED";

// Beschreibt eine vollständige Aufgabe aus dem Backend.
export type Task = {
  id: number;
  title: string;
  description: string;
  priority: Priority;
  status: TaskStatus;
  dueDate: string;
};

// Enthält genau die Daten zum Erstellen einer Aufgabe.
export type CreateTaskInput = {
  title: string;
  description: string;
  priority: Priority;
  dueDate: string;
};

// Erlaubte Sortierparameter für das Backend.
export type TaskSort = "priority" | "dueDate";
