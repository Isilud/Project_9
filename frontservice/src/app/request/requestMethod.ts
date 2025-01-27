import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post["Content-Type"] = 'application/json';

export async function requestMethod<T>(method?:string, url?:string, data?:unknown):Promise<T> {
    return await axios({method, url, data}).then((res)=>res.data)
}