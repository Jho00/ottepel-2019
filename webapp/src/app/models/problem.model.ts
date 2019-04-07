export interface IProblem {
    id: number;
    title: string;
    text: string;
    userId: number;
    images: any[];
    createdAt: string;
    likesCount: number;
    dislikesCount: number;
    lat: number;
    lon: number;

}

export class Problem implements IProblem {
    id: number;
    title: string;
    text: string;
    userId: number;
    createdAt: string;
    likesCount: number;
    dislikesCount: number;
    images: any[];
    lat: number;
    lon: number;

    constructor(data: IProblem) {
        Object.assign(this, data);
    }
}
