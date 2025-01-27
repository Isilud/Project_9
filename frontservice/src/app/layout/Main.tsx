import { JSX } from "react";
import "./Main.scss";
import PatientPage from "../page/PatientPage";
import { Route, Routes } from "react-router-dom";
import { userLoginState } from "../store/loginAtom";
import { useAtom } from "jotai";
import LoginPage from "../page/LoginPage";
import PatientForm from "../page/PatientForm";

export default function Main(): JSX.Element {
  const [userLogin] = useAtom(userLoginState);

  return (
    <Routes>
      <Route path="/">
        {userLogin.logged ? (
          <>
            <Route index element={<PatientPage />} />
            <Route path="patientForm" element={<PatientForm />} />
            <Route path="patientForm/:patientId" element={<PatientForm />} />
          </>
        ) : (
          <Route index element={<LoginPage />} />
        )}
      </Route>
    </Routes>
  );
}
