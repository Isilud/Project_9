import { JSX } from "react";
import "./header.scss";
import { userLoginState } from "../store/loginAtom";
import { useAtom } from "jotai";

export default function Header(): JSX.Element {
  const [userLogin, setUserLogin] = useAtom(userLoginState);

  const loginHandle = () => {
    setUserLogin({ logged: !userLogin.logged });
  };

  return (
    <div className="header">
      <span className="header_title">MédiLabo Solutions</span>
      <button onClick={loginHandle}>
        {userLogin.logged ? "Déconnexion" : "Connexion"}
      </button>
    </div>
  );
}
