import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Smart Task Manager",
  description: "Eine übersichtliche Aufgabenverwaltung für Studierende.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="de">
      <body>{children}</body>
    </html>
  );
}
