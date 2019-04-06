export interface IProblem {
    id: number;
    title: string;
    text: string;
}

export class Problem implements IProblem {
    id: number;
    title: string;
    text: string;

    constructor(data: IProblem) {
        Object.assign(this, data);
    }
}
