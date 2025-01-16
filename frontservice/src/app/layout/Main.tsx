import { JSX } from "react";
import "./Main.scss";
import PatientPage from "../page/PatientPage";
import { Route, Routes } from "react-router-dom";
import { userLoginState } from "../store/loginAtom";
import { useAtom } from "jotai";
import LoginPage from "../page/LoginPage";

export default function Main(): JSX.Element {
  const [userLogin] = useAtom(userLoginState);

  return (
    <Routes>
      <Route path="/">
        {userLogin.logged ? (
          <>
            <Route index element={<PatientPage />} />
            <Route path="addPatient" element={<></>} />
          </>
        ) : (
          <Route index element={<LoginPage />} />
        )}
      </Route>
    </Routes>
  );
}
