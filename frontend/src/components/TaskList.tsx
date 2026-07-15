import TaskCard from "./TaskCard";
import type { Task, TaskSort } from "@/types/task";

type TaskListProps = {
  tasks: Task[];
  activeSort?: TaskSort;
  isLoading: boolean;
  error?: string;
  activeTaskId?: number;
  onSort: (sort: TaskSort) => Promise<void>;
  onComplete: (id: number) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
};

// Zeigt Aufgaben, Sortierung sowie Lade- und Fehlerzustände.
export default function TaskList({
  tasks,
  activeSort,
  isLoading,
  error,
  activeTaskId,
  onSort,
  onComplete,
  onDelete,
}: TaskListProps) {
  return (
    <section className="task-section" aria-labelledby="task-list-title">
      <div className="list-header">
        <div className="section-heading">
          <p className="section-kicker">Aufgabenliste</p>
          <h2 id="task-list-title">Deine Aufgaben</h2>
          <p>{tasks.length} {tasks.length === 1 ? "Aufgabe" : "Aufgaben"}</p>
        </div>

        <div className="sort-actions" aria-label="Aufgaben sortieren">
          <button
            className={`sort-button${activeSort === "priority" ? " active" : ""}`}
            type="button"
            onClick={() => void onSort("priority")}
            disabled={isLoading}
          >
            Priorität
          </button>
          <button
            className={`sort-button${activeSort === "dueDate" ? " active" : ""}`}
            type="button"
            onClick={() => void onSort("dueDate")}
            disabled={isLoading}
          >
            Fälligkeitsdatum
          </button>
        </div>
      </div>

      <div className="task-list">
        {/* Je nach aktuellem Zustand wird ein Hinweis oder die Aufgabenliste gezeigt. */}
        {isLoading && <p className="list-state" role="status">Aufgaben werden geladen …</p>}
        {!isLoading && error && (
          <p className="list-state error" role="alert">{error}</p>
        )}
        {!isLoading && !error && tasks.length === 0 && (
          <p className="list-state">Noch keine Aufgaben vorhanden.</p>
        )}
        {!isLoading && tasks.map((task) => (
          <TaskCard
            key={task.id}
            task={task}
            isUpdating={activeTaskId === task.id}
            onComplete={onComplete}
            onDelete={onDelete}
          />
        ))}
      </div>
    </section>
  );
}
