const priorities = ["LOW", "MEDIUM", "HIGH"] as const;

export default function CreateTaskForm() {
  return (
    <section className="panel form-panel" id="new-task" aria-labelledby="form-title">
      <div className="section-heading">
        <p className="section-kicker">Neue Aufgabe</p>
        <h2 id="form-title">Was steht als Nächstes an?</h2>
        <p>Trage die wichtigsten Informationen zu deiner Aufgabe ein.</p>
      </div>

      <form className="task-form">
        <label className="field">
          <span>Titel</span>
          <input type="text" placeholder="z. B. Prüfung vorbereiten" />
        </label>

        <label className="field">
          <span>Beschreibung</span>
          <textarea
            rows={4}
            placeholder="Kurze Notizen oder wichtige Schritte"
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
                  defaultChecked={priority === "MEDIUM"}
                />
                <span>{priority}</span>
              </label>
            ))}
          </div>
        </fieldset>

        <label className="field">
          <span>Fälligkeitsdatum</span>
          <input type="date" />
        </label>

        <button className="primary-button form-submit" type="button">
          Aufgabe erstellen
        </button>
      </form>
    </section>
  );
}
