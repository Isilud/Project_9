import { JSX, useEffect } from "react";
import { patientList } from "../store/patientsAtom";
import { useAtom } from "jotai";
import "./PatientTable.scss";

export default function PatientTable(): JSX.Element {
  const [patients, setPatients] = useAtom(patientList);

  // TODO : Implement fetching existing patients
  useEffect(() => {
    setPatients([
      {
        nom: "Smith",
        prenom: "John",
        id: 0,
        dateNaissance: "01/01/01",
        genre: "Homme",
        addresse: "123",
        telephone: "0123456789",
      },
    ]);
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
            <td>{patient.addresse}</td>
            <td>{patient.telephone}</td>
            <td>
              <div className="patienttable_action">
                <button
                  className="patienttable_button"
                  onClick={() => handleDelete(patient.id)}
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
