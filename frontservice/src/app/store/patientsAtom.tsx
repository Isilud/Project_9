import { atom } from "jotai";
import Patient from "../model/Patient";

export const patientList = atom<Patient[]>([]);
