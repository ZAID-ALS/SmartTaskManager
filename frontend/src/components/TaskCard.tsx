import type { TaskItem } from "./TaskList";

type TaskCardProps = {
  task: TaskItem;
};

export default function TaskCard({ task }: TaskCardProps) {
  const isCompleted = task.status === "COMPLETED";

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
          Fällig am {task.dueDate}
        </p>
      </div>

      <div className="task-actions">
        <button className="complete-button" type="button">
          <span aria-hidden="true">✓</span>
          Erledigt
        </button>
        <button className="delete-button" type="button">
          Löschen
        </button>
      </div>
    </article>
  );
}
