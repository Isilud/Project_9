import DiagnosticLevel from "../model/DiagnosticLevel";
import { requestMethod } from "./requestMethod";

export async function getDiagnosticRequest(id:number): Promise<DiagnosticLevel> {
    const result = await requestMethod<DiagnosticLevel>("GET","/diagnostic/"+id);
    return result;
}