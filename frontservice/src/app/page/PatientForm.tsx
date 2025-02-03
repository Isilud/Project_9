import { JSX, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Patient from "../model/Patient";
import "./PatientForm.scss";
import {
  getPatientRequest,
  postPatientRequest,
  putPatientRequest,
} from "../request/patientRequests";

export default function PatientForm({
  patientId,
}: {
  patientId?: number;
}): JSX.Element {
  const navigate = useNavigate();

  const [patientData, setPatientData] = useState<Patient>({
    prenom: "",
    nom: "",
    dateNaissance: "",
    genre: "",
    adressePostale: "",
    numeroTelephone: "",
  });

  const [isDirty, setIsDirty] = useState<boolean>(false);

  useEffect(() => {
    updatePatient();
  }, [patientId]);

  const updatePatient = () => {
    if (patientId)
      getPatientRequest(patientId as unknown as number)
        .then((res) => {
          setPatientData(res);
        })
        .catch((err) => console.log(err));
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setPatientData({ ...patientData, [name]: value });
    setIsDirty(true);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (patientId)
      putPatientRequest(patientData)
        .then((res) => navigate("/patientForm/" + res.id))
        .catch((err) => console.log(err));
    else
      postPatientRequest(patientData)
        .then((res) => navigate("/patientForm/" + res.id))
        .catch((err) => console.log(err));
    setIsDirty(false);
  };

  return (
    <form onSubmit={handleSubmit} className="patientform">
      <label className="patientform_label">Nom</label>
      <input
        className="patientform_input"
        type="text"
        name="nom"
        value={patientData.nom}
        onChange={handleChange}
        required
      />

      <label className="patientform_label">Prénom</label>
      <input
        className="patientform_input"
        type="text"
        name="prenom"
        value={patientData.prenom}
        onChange={handleChange}
        required
      />

      <label className="patientform_label">Date de Naissance</label>
      <input
        className="patientform_input"
        type="text"
        name="dateNaissance"
        value={patientData.dateNaissance}
        onChange={handleChange}
        required
      />

      <label className="patientform_label">Genre</label>
      <input
        className="patientform_input"
        type="text"
        name="genre"
        value={patientData.genre}
        onChange={handleChange}
        required
      />

      <label className="patientform_label">Adresse</label>
      <input
        className="patientform_input"
        type="text"
        name="adressePostale"
        value={patientData.adressePostale}
        onChange={handleChange}
      />

      <label className="patientform_label">Téléphone</label>
      <input
        className="patientform_input"
        type="text"
        name="numeroTelephone"
        value={patientData.numeroTelephone}
        onChange={handleChange}
      />

      <button type="submit" className="patientform_submit" disabled={!isDirty}>
        {patientId ? "Mettre à jour" : "Créer"}
      </button>
    </form>
  );
}
