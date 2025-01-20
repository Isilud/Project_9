import Patient from "../model/Patient";
import { requestMethod } from "./requestMethod";

export async function getAllPatientRequest(): Promise<Patient[]> {
    const patientList = await requestMethod<Patient[]>("GET","/patients");
    console.log(patientList);
    return patientList;
}

export async function postPatientRequest(patient: Patient): Promise<Patient> {
    const savedPatient = await requestMethod<Patient>("POST","/patients", patient);
    console.log(savedPatient);
    return savedPatient;
}