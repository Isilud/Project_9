import { JSX } from "react";
import DiagnosticLevel from "../model/DiagnosticLevel";
import "./PatientWarning.scss";

export default function PatientWarning({
  diagnostic = "NONE",
}: {
  diagnostic: DiagnosticLevel;
}): JSX.Element {
  switch (diagnostic) {
    case "NONE":
      return <span className="level-none">None</span>;
    case "EARLY":
      return <span className="level-early">Early Onset</span>;
    case "BORDERLINE":
      return <span className="level-borderline">Borderline</span>;
    case "DANGER":
      return <span className="level-danger">Danger</span>;
    default:
      return <span>Inconnu</span>;
  }
}
