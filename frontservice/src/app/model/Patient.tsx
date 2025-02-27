export default interface Patient {
  id?: number;
  prenom: string;
  nom: string;
  dateNaissance: string;
  genre: "M" | "F";
  adressePostale?: string;
  numeroTelephone?: string;
}
