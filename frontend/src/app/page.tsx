import CreateTaskForm from "@/components/CreateTaskForm";
import Header from "@/components/Header";
import TaskList, { type TaskItem } from "@/components/TaskList";

const exampleTasks: TaskItem[] = [
  {
    id: 1,
    title: "Präsentation vorbereiten",
    description: "Folien für das Uni-Projekt fertigstellen und den Vortrag üben.",
    priority: "HIGH",
    status: "NEW",
    dueDate: "20.07.2026",
  },
  {
    id: 2,
    title: "Java-Kapitel wiederholen",
    description: "Notizen zu Klassen, Objekten und Interfaces zusammenfassen.",
    priority: "MEDIUM",
    status: "NEW",
    dueDate: "24.07.2026",
  },
  {
    id: 3,
    title: "Projektplan abgeben",
    description: "Die finale Version im Lernportal hochladen.",
    priority: "LOW",
    status: "COMPLETED",
    dueDate: "28.07.2026",
  },
];

export default function Home() {
  return (
    <div className="app-shell">
      <Header />
      <main className="page-container">
        <section className="intro" aria-labelledby="page-title">
          <p className="eyebrow">Dein Studienalltag auf einen Blick</p>
          <h1 id="page-title">Aufgaben klar planen und entspannt erledigen.</h1>
          <p className="intro-text">
            Erfasse deine nächsten Aufgaben, setze Prioritäten und behalte alle
            Fälligkeitstermine übersichtlich im Blick.
          </p>
        </section>

        <div className="workspace">
          <CreateTaskForm />
          <TaskList tasks={exampleTasks} />
        </div>
      </main>
    </div>
  );
}
