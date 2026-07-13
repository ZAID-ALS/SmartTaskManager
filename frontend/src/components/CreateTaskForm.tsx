"use client";

import { FormEvent, useState } from "react";
import type { CreateTaskInput, Priority } from "@/types/task";

const priorities: Priority[] = ["LOW", "MEDIUM", "HIGH"];

type CreateTaskFormProps = {
  onCreate: (input: CreateTaskInput) => Promise<void>;
};

const initialForm: CreateTaskInput = {
  title: "",
  description: "",
  priority: "MEDIUM",
  dueDate: "",
};

export default function CreateTaskForm({ onCreate }: CreateTaskFormProps) {
  const [form, setForm] = useState<CreateTaskInput>(initialForm);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [message, setMessage] = useState<{
    type: "success" | "error";
    text: string;
  }>();

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setIsSubmitting(true);
    setMessage(undefined);

    try {
      await onCreate(form);
      setForm(initialForm);
      setMessage({ type: "success", text: "Aufgabe wurde erfolgreich erstellt." });
    } catch (error) {
      setMessage({
        type: "error",
        text:
          error instanceof Error
            ? error.message
            : "Die Aufgabe konnte nicht erstellt werden.",
      });
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <section className="panel form-panel" id="new-task" aria-labelledby="form-title">
      <div className="section-heading">
        <p className="section-kicker">Neue Aufgabe</p>
        <h2 id="form-title">Was steht als Nächstes an?</h2>
        <p>Trage die wichtigsten Informationen zu deiner Aufgabe ein.</p>
      </div>

      <form className="task-form" onSubmit={handleSubmit}>
        <label className="field">
          <span>Titel</span>
          <input
            type="text"
            placeholder="z. B. Prüfung vorbereiten"
            value={form.title}
            onChange={(event) => setForm({ ...form, title: event.target.value })}
            required
          />
        </label>

        <label className="field">
          <span>Beschreibung</span>
          <textarea
            rows={4}
            placeholder="Kurze Notizen oder wichtige Schritte"
            value={form.description}
            onChange={(event) =>
              setForm({ ...form, description: event.target.value })
            }
          />
        </label>

        <fieldset className="priority-field">
          <legend>Priorität</legend>
          <div className="priority-options">
            {priorities.map((priority) => (
              <label key={priority}>
                <input
                  type="radio"
                  name="priority"
                  value={priority}
                  checked={form.priority === priority}
                  onChange={() => setForm({ ...form, priority })}
                />
                <span>{priority}</span>
              </label>
            ))}
          </div>
        </fieldset>

        <label className="field">
          <span>Fälligkeitsdatum</span>
          <input
            type="date"
            value={form.dueDate}
            onChange={(event) => setForm({ ...form, dueDate: event.target.value })}
            required
          />
        </label>

        {message && (
          <p className={`form-message ${message.type}`} role="status">
            {message.text}
          </p>
        )}

        <button
          className="primary-button form-submit"
          type="submit"
          disabled={isSubmitting}
        >
          {isSubmitting ? "Wird erstellt …" : "Aufgabe erstellen"}
        </button>
      </form>
    </section>
  );
}
