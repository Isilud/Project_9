import { JSX, useEffect, useState } from "react";
import { getDiagnosticRequest } from "../request/diagnosticRequest";
import DiagnosticLevel from "../model/DiagnosticLevel";
import "./PatientWarning.scss";

export default function PatientWarning({
  patientId,
}: {
  patientId: number;
}): JSX.Element {
  const [diagnostic, setDiagnostic] = useState<DiagnosticLevel>("NONE");

  useEffect(() => {
    getDiagnosticRequest(patientId).then((level) => {
      setDiagnostic(level);
    });
  }, [patientId]);

  switch (diagnostic) {
    case "NONE":
      return <span className="level-none">-</span>;
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
