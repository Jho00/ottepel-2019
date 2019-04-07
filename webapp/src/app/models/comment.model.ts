export interface IComment {
  text: String;
  username: String;
}

export class Comment implements IComment {
  text: String;
  username: String;

  constructor(data: IComment) {
    Object.assign(this, data);
  }
}
