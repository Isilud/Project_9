import { atom } from "jotai";

interface Patient {
  id: number;
  prenom: string;
  nom: string;
  dateNaissance: string;
  genre: string;
  addresse: string;
  telephone: string;
}

export const patientList = atom<Patient[]>([]);
