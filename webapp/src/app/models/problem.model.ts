export interface IProblem {
    id: number;
    title: string;
    text: string;
    userId: number;
    images: any[];
    lat: number;
    lon: number;

}

export class Problem implements IProblem {
    id: number;
    title: string;
    text: string;
    userId: number;
    images: any[];
    lat: number;
    lon: number;

    constructor(data: IProblem) {
        Object.assign(this, data);
    }
}
