import type { Task } from "@/types/task";

type TaskCardProps = {
  task: Task;
  isUpdating: boolean;
  onComplete: (id: number) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
};

export default function TaskCard({
  task,
  isUpdating,
  onComplete,
  onDelete,
}: TaskCardProps) {
  const isCompleted = task.status === "COMPLETED";
  const formattedDueDate = new Intl.DateTimeFormat("de-DE").format(
    new Date(`${task.dueDate}T00:00:00`),
  );

  return (
    <article className={`task-card${isCompleted ? " completed" : ""}`}>
      <div className="task-content">
        <div className="task-title-row">
          <span className={`priority-badge priority-${task.priority.toLowerCase()}`}>
            {task.priority}
          </span>
          <span className={`status-badge status-${task.status.toLowerCase()}`}>
            {isCompleted ? "ERLEDIGT" : "OFFEN"}
          </span>
        </div>

        <h3>{task.title}</h3>
        <p className="task-description">{task.description}</p>
        <p className="due-date">
          <span aria-hidden="true">□</span>
          Fällig am {formattedDueDate}
        </p>
      </div>

      <div className="task-actions">
        <button
          className="complete-button"
          type="button"
          onClick={() => void onComplete(task.id)}
          disabled={isCompleted || isUpdating}
        >
          <span aria-hidden="true">✓</span>
          {isCompleted ? "Erledigt" : "Als erledigt markieren"}
        </button>
        <button
          className="delete-button"
          type="button"
          onClick={() => void onDelete(task.id)}
          disabled={isUpdating}
        >
          Löschen
        </button>
      </div>
    </article>
  );
}
