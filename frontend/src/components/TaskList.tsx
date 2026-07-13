import TaskCard from "./TaskCard";

export type TaskPriority = "HIGH" | "MEDIUM" | "LOW";
export type TaskStatus = "NEW" | "COMPLETED";

export type TaskItem = {
  id: number;
  title: string;
  description: string;
  priority: TaskPriority;
  status: TaskStatus;
  dueDate: string;
};

type TaskListProps = {
  tasks: TaskItem[];
};

export default function TaskList({ tasks }: TaskListProps) {
  return (
    <section className="task-section" aria-labelledby="task-list-title">
      <div className="list-header">
        <div className="section-heading">
          <p className="section-kicker">Aufgabenliste</p>
          <h2 id="task-list-title">Deine Aufgaben</h2>
          <p>{tasks.length} statische Beispielaufgaben für die Designprüfung</p>
        </div>

        <div className="sort-actions" aria-label="Aufgaben sortieren">
          <button className="sort-button active" type="button">
            Priorität
          </button>
          <button className="sort-button" type="button">
            Fälligkeitsdatum
          </button>
        </div>
      </div>

      <div className="task-list">
        {tasks.map((task) => (
          <TaskCard key={task.id} task={task} />
        ))}
      </div>
    </section>
  );
}
