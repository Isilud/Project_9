import { JSX, useCallback, useEffect } from "react";
import { patientList } from "../store/patientsAtom";
import { useAtom } from "jotai";
import "./PatientTable.scss";
import {
  deletePatientRequest,
  getAllPatientRequest,
} from "../request/patientRequests";
import { useNavigate } from "react-router-dom";

export default function PatientTable(): JSX.Element {
  const [patients, setPatients] = useAtom(patientList);
  const navigate = useNavigate();

  const updatePatientList = useCallback(async () => {
    await getAllPatientRequest()
      .then((res) => {
        setPatients(res);
      })
      .catch((err) => console.log(err));
  }, [setPatients]);

  useEffect(() => {
    updatePatientList();
  }, [updatePatientList]);

  const handleDelete = async (patientId: number) => {
    await deletePatientRequest(patientId).catch((err) => console.log(err));
    await updatePatientList();
  };

  return (
    <table className="patienttable">
      <thead>
        <tr>
          <td>Nom</td>
          <td>Prénom</td>
          <td>Genre</td>
          <td>Naissance</td>
          <td>Adresse</td>
          <td>Téléphone</td>
          <td></td>
        </tr>
      </thead>
      <tbody>
        {patients.map((patient) => (
          <tr
            key={patient.id}
            onClick={() => navigate("/patientForm/" + patient.id)}
          >
            <td>{patient.nom}</td>
            <td>{patient.prenom}</td>
            <td>{patient.genre}</td>
            <td>{patient.dateNaissance}</td>
            <td>{patient.adressePostale}</td>
            <td>{patient.numeroTelephone}</td>
            <td>
              <div className="patienttable_action">
                <button
                  className="patienttable_button"
                  onClick={(e) => {
                    e.stopPropagation();
                    e.preventDefault();
                    handleDelete(patient.id!).then();
                  }}
                >
                  Supprimer
                </button>
              </div>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
