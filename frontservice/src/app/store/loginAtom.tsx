import { atom } from "jotai";

export interface UserLogin {
  logged: boolean;
}

export const userLoginState = atom<UserLogin>({
  logged: false,
});
