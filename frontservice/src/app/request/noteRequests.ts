import Note from "../model/Note";
import { requestMethod } from "./requestMethod";

export async function getAllNotesRequestForNote(id:number): Promise<Note[]> {
    const notesList = await requestMethod<Note[]>("GET","/notes/"+id);
    return notesList;
}

export async function postNoteRequest(note: Note): Promise<Note> {
    const savedNote = await requestMethod<Note>("POST","/notes", note);
    return savedNote;
}

export async function putNoteRequest(note: Note): Promise<Note> {
    const updatedNote = await requestMethod<Note>("PUT","/notes/"+note.id, note);
    return updatedNote;
}

export async function deleteNoteRequest(id:number): Promise<Note> {
    const deletedNote = await requestMethod<Note>("DELETE","/notes/"+id);
    return deletedNote;
}