import type { Metadata } from "next";
import "./globals.css";

// Liefert Next.js den Titel und die Beschreibung der Seite.
export const metadata: Metadata = {
  title: "Smart Task Manager",
  description: "Eine übersichtliche Aufgabenverwaltung für Studierende.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  // Umschließt jede Seite der Anwendung.
  return (
    <html lang="de">
      <body>{children}</body>
    </html>
  );
}
