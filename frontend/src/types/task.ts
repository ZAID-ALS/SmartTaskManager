export type Priority = "HIGH" | "MEDIUM" | "LOW";

export type TaskStatus = "NEW" | "IN_PROGRESS" | "COMPLETED" | "DELETED";

export type Task = {
  id: number;
  title: string;
  description: string;
  priority: Priority;
  status: TaskStatus;
  dueDate: string;
};

export type CreateTaskInput = {
  title: string;
  description: string;
  priority: Priority;
  dueDate: string;
};

export type TaskSort = "priority" | "dueDate";
