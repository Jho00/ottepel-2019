import {environment} from "../../environments/environment";

const appUrl = environment.baseUrl;

export const ENDPOINTS = {
    login: `${appUrl}/auth/login`,
    register: `${appUrl}/auth/register`,
    logout: `${appUrl}/auth/logout`,
    getCurrentUser: `${appUrl}/auth/user`
};
