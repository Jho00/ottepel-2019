export interface IProblem {
    id: number;
    title: string;
    body: string;
}

export class Problem implements IProblem {
    id: number;
    title: string;
    body: string;

    constructor(data: IProblem) {
        Object.assign(this, data);
    }
}
