export interface IUser {
    id: number,
    firstName: string,
    lastName: string,
    email: string,
    token: string
}

export class User implements IUser{
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    token: string;

    constructor(data: IUser) {
        Object.assign(this, data);
    }
}
