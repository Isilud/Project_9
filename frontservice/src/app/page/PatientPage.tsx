import PatientTable from "../component/PatientTable";
import "./PatientPage.scss";

export default function PatientPage() {
  return (
    <div className="patientpage">
      <div className="patientpage_header">
        <button className="patientpage_button">Ajouter Patient</button>
      </div>
      <PatientTable />
    </div>
  );
}
