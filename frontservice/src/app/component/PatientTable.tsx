import { JSX, useEffect } from "react";
import { patientList } from "../store/patientsAtom";
import { useAtom } from "jotai";
import "./PatientTable.scss";
import { getAllPatientRequest } from "../request/patientRequests";

export default function PatientTable(): JSX.Element {
  const [patients, setPatients] = useAtom(patientList);

  useEffect(() => {
    getAllPatientRequest()
      .then((res) => {
        setPatients(res);
      })
      .catch((err) => console.log(err));
  }, []);

  // TODO : Implement Deletion
  const handleDelete = (patientId: number) => {
    console.log("TODO : DELETE ", patientId);
  };

  return (
    <table className="patienttable">
      <thead>
        <tr>
          <td>Nom</td>
          <td>Prénom</td>
          <td>Genre</td>
          <td>Naissance</td>
          <td>Addresse</td>
          <td>Téléphone</td>
          <td></td>
        </tr>
      </thead>
      <tbody>
        {patients.map((patient) => (
          <tr key={patient.id}>
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
                  onClick={() => handleDelete(patient.id!)}
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
