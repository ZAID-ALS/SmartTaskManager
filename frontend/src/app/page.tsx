"use client";

import { useCallback, useEffect, useState } from "react";
import CreateTaskForm from "@/components/CreateTaskForm";
import Header from "@/components/Header";
import TaskList from "@/components/TaskList";
import {
  completeTask,
  createTask,
  deleteTask,
  getTasks,
} from "@/lib/taskApi";
import type { CreateTaskInput, Task, TaskSort } from "@/types/task";

// Verwaltet den Zustand und setzt die Hauptseite zusammen.
export default function Home() {
  // Speichert die Aufgaben und die Zustände für Laden, Fehler und Aktionen.
  const [tasks, setTasks] = useState<Task[]>([]);
  const [activeSort, setActiveSort] = useState<TaskSort>();
  const [isLoading, setIsLoading] = useState(true);
  const [listError, setListError] = useState<string>();
  const [activeTaskId, setActiveTaskId] = useState<number>();

  // Lädt Aufgaben vom Backend und kann sie dort sortieren lassen.
  const loadTasks = useCallback(async (sort?: TaskSort) => {
    setIsLoading(true);
    setListError(undefined);

    try {
      const loadedTasks = await getTasks(sort);
      setTasks(loadedTasks);
      setActiveSort(sort);
    } catch {
      setListError(
        "Die Aufgaben konnten nicht geladen werden. Ist das Backend auf Port 8080 gestartet?",
      );
    } finally {
      setIsLoading(false);
    }
  }, []);

  // Beim ersten Anzeigen der Seite werden die Aufgaben einmal vom Backend geladen.
  useEffect(() => {
    let isCurrent = true;

    getTasks()
      .then((loadedTasks) => {
        if (isCurrent) {
          setTasks(loadedTasks);
        }
      })
      .catch(() => {
        if (isCurrent) {
          setListError(
            "Die Aufgaben konnten nicht geladen werden. Ist das Backend auf Port 8080 gestartet?",
          );
        }
      })
      .finally(() => {
        if (isCurrent) {
          setIsLoading(false);
        }
      });

    return () => {
      isCurrent = false;
    };
  }, []);

  // Erstellt eine Aufgabe und aktualisiert danach die sichtbare Liste.
  async function handleCreate(input: CreateTaskInput) {
    const newTask = await createTask(input);

    if (activeSort) {
      await loadTasks(activeSort);
    } else {
      setTasks((currentTasks) => [...currentTasks, newTask]);
    }
  }

  // Markiert die ausgewählte Aufgabe über das Backend als erledigt.
  async function handleComplete(id: number) {
    setActiveTaskId(id);
    setListError(undefined);

    try {
      const updatedTask = await completeTask(id);
      setTasks((currentTasks) =>
        currentTasks.map((task) => (task.id === id ? updatedTask : task)),
      );
    } catch {
      setListError("Die Aufgabe konnte nicht abgeschlossen werden.");
    } finally {
      setActiveTaskId(undefined);
    }
  }

  // Löscht die ausgewählte Aufgabe und entfernt sie aus der Anzeige.
  async function handleDelete(id: number) {
    setActiveTaskId(id);
    setListError(undefined);

    try {
      await deleteTask(id);
      setTasks((currentTasks) =>
        currentTasks.filter((task) => task.id !== id),
      );
    } catch {
      setListError("Die Aufgabe konnte nicht gelöscht werden.");
    } finally {
      setActiveTaskId(undefined);
    }
  }

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
          <CreateTaskForm onCreate={handleCreate} />
          <TaskList
            tasks={tasks}
            activeSort={activeSort}
            isLoading={isLoading}
            error={listError}
            activeTaskId={activeTaskId}
            onSort={loadTasks}
            onComplete={handleComplete}
            onDelete={handleDelete}
          />
        </div>
      </main>
    </div>
  );
}
