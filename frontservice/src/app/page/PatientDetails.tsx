import { JSX, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./PatientDetails.scss";
import Note from "../model/Note";
import {
  deleteNoteRequest,
  getAllNotesRequestForPatient,
  postNoteRequest,
  putNoteRequest,
} from "../request/noteRequests";
import PatientForm from "./PatientForm";
import { getDiagnosticRequest } from "../request/diagnosticRequest";
import PatientWarning from "../component/PatientWarning";

export default function PatientDetails(): JSX.Element {
  const { patientId } = useParams<{ patientId: string }>() as unknown as {
    patientId: number;
  };

  const [noteData, setNoteData] = useState<Note[]>([]);

  const [isEditingNode, setIsEditingNode] = useState<number | undefined>();

  const [editedText, setEditedText] = useState<string>("");

  useEffect(() => {
    updateNotes();
    getDiagnosticRequest(patientId).then((res) => {
      console.log(res);
    });
  }, [patientId]);

  const updateNotes = () => {
    if (patientId) {
      getAllNotesRequestForPatient(patientId)
        .then((res) => {
          setNoteData(res);
        })
        .catch((err) => console.log(err));
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { value } = e.target;
    setEditedText(value);
  };

  const modifyNote = (note: Note) => {
    note.text = editedText;
    putNoteRequest(note)
      .then(() => {
        updateNotes();
        setIsEditingNode(undefined);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deleteNote = (id: string) => {
    deleteNoteRequest(id)
      .then(() => {
        updateNotes();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const addNote = (text: string) => {
    const note: Note = { patientId: patientId as unknown as string, text };
    postNoteRequest(note)
      .then(() => {
        updateNotes();
        setIsEditingNode(undefined);
        setEditedText("");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="patientdetails">
      <PatientForm patientId={patientId} />
      <div className="patientdetails_notes">
        <table className="patientdetails_table">
          <thead>
            <tr>
              <th className="patientdetails_title">
                Notes
                <PatientWarning patientId={patientId} />
              </th>
              <th>
                <button
                  className="patientdetails_button"
                  disabled={isEditingNode !== undefined}
                  onClick={() => setIsEditingNode(-1)}
                >
                  Ajouter
                </button>
              </th>
            </tr>
          </thead>
          <tbody>
            {isEditingNode === -1 ? (
              <tr key={-1}>
                <td>
                  <textarea
                    style={{ width: "100%" }}
                    defaultValue={editedText}
                    onChange={(e) => handleChange(e)}
                  ></textarea>
                </td>
                <td>
                  <div>
                    <button
                      className="patientdetails_button"
                      onClick={() => addNote(editedText)}
                    >
                      Ajouter
                    </button>
                    <button
                      className="patientdetails_button"
                      onClick={() => {
                        setIsEditingNode(undefined);
                        setEditedText("");
                      }}
                    >
                      Annuler
                    </button>
                  </div>
                </td>
              </tr>
            ) : (
              <></>
            )}
            {noteData.reverse().map((note, index) => (
              <tr key={note.id}>
                <td>
                  {isEditingNode === index ? (
                    <textarea
                      style={{ width: "100%" }}
                      defaultValue={editedText}
                      onChange={(e) => handleChange(e)}
                    ></textarea>
                  ) : (
                    <span style={{ whiteSpace: "pre-wrap" }}>{note.text}</span>
                  )}
                </td>
                <td>
                  {index === isEditingNode ? (
                    <div>
                      <button
                        className="patientdetails_button"
                        onClick={() => modifyNote(note)}
                      >
                        Valider
                      </button>
                      <button
                        className="patientdetails_button"
                        onClick={() => {
                          setIsEditingNode(undefined);
                          setEditedText("");
                        }}
                      >
                        Annuler
                      </button>
                    </div>
                  ) : (
                    <div>
                      <button
                        className="patientdetails_button"
                        onClick={() => {
                          setIsEditingNode(index);
                          setEditedText(note.text);
                        }}
                        disabled={isEditingNode !== undefined}
                      >
                        Editer
                      </button>
                      <button
                        className="patientdetails_button"
                        onClick={() => deleteNote(note.id!)}
                        disabled={isEditingNode !== undefined}
                      >
                        Supprimer
                      </button>
                    </div>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
