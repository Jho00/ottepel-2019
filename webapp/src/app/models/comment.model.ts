export interface IComment {
  likes: number;
  text: String;
  username: String;
}

export class Comment implements IComment {
  likes: number;
  text: String;
  username: String;

  constructor(data: IComment) {
    Object.assign(this, data);
  }
}
