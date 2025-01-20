import { JSX, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Patient from "../model/Patient";
import "./PatientForm.scss";
import { postPatientRequest } from "../request/patientRequests";

export default function PatientForm(): JSX.Element {
  const { patientId } = useParams<{ patientId: string }>();
  const navigate = useNavigate();

  const [patientData, setPatientData] = useState<Patient>({
    prenom: "",
    nom: "",
    dateNaissance: "",
    genre: "",
    adressePostale: "",
    numeroTelephone: "",
  });

  useEffect(() => {
    // TODO : Fetch patient with ID if any
    if (patientId) console.log("TODO: Fetch patient with id : ", patientId);
    else console.log("No fetching");
  }, [patientId]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setPatientData({ ...patientData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (patientId) console.log("TODO : Edit patient endpoint");
    else
      postPatientRequest(patientData)
        .then(() => navigate("/"))
        .catch((err) => console.log(err));
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

      <label className="patientform_label">Addresse</label>
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

      <button type="submit" className="patientform_submit">
        {patientId ? "Mettre à jour" : "Créer"}
      </button>
    </form>
  );
}
