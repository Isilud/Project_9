import { useNavigate } from "react-router-dom";
import PatientTable from "../component/PatientTable";
import "./PatientPage.scss";

export default function PatientPage() {
  const navigate = useNavigate();
  return (
    <div className="patientpage">
      <div className="patientpage_header">
        <button
          className="patientpage_button"
          onClick={() => navigate("/patientForm")}
        >
          Ajouter Patient
        </button>
      </div>
      <PatientTable />
    </div>
  );
}
