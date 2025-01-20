import Patient from "../model/Patient";
import { requestMethod } from "./requestMethod";

export async function getAllPatientRequest(): Promise<Patient[]> {
    const patientList = await requestMethod<Patient[]>("GET","/patients");
    return patientList;
}

export async function getPatientRequest(id:number): Promise<Patient[]> {
    const patientList = await requestMethod<Patient[]>("GET","/patients/"+id);
    return patientList;
}

export async function postPatientRequest(patient: Patient): Promise<Patient> {
    const savedPatient = await requestMethod<Patient>("POST","/patients", patient);
    return savedPatient;
}

export async function deletePatientRequest(id:number): Promise<Patient> {
    const savedPatient = await requestMethod<Patient>("DELETE","/patients/"+id);
    return savedPatient;
}