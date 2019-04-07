import {environment} from "../../environments/environment";

const appUrl = environment.baseUrl;

export const ENDPOINTS = {
    login: `${appUrl}/auth/login`,
    register: `${appUrl}/auth/register`,
    logout: `${appUrl}/auth/logout`,
    getCurrentUser: `${appUrl}/auth/user`,
    getProblemsList: `${appUrl}/problem/get`,
    sendProblem:  `${appUrl}/problems/add`,
    getProblemById: `${appUrl}/problem/get`,
    sendPhotos: `${appUrl}/photos/add`,
    getComments: `${appUrl}/comments/post`,
    addComment: `${appUrl}/comments/add`,
    approveProblem: `${appUrl}/problems/approve`,
    resolveProblem: `${appUrl}/problems/resolve`
};
