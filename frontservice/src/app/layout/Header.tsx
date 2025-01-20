import { JSX } from "react";
import "./header.scss";
import { userLoginState } from "../store/loginAtom";
import { useAtom } from "jotai";
import { useNavigate } from "react-router-dom";

export default function Header(): JSX.Element {
  const [userLogin, setUserLogin] = useAtom(userLoginState);
  const navigate = useNavigate();

  const loginHandle = () => {
    setUserLogin({ logged: !userLogin.logged });
    navigate("/");
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
