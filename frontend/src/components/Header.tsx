// Zeigt den Kopfbereich und den Sprung zum Aufgabenformular.
export default function Header() {
  return (
    <header className="topbar">
      <div className="topbar-inner">
        <div className="brand">
          <span className="brand-mark" aria-hidden="true">
            ✓
          </span>
          <div>
            <p className="brand-name">Smart Task Manager</p>
            <p className="brand-subtitle">Einfach organisiert durchs Studium</p>
          </div>
        </div>

        <a className="primary-button header-button" href="#new-task">
          <span aria-hidden="true">＋</span>
          Neue Aufgabe
        </a>
      </div>
    </header>
  );
}
