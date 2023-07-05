export interface Square {
  Position: Position;
  Piece: Piece;
}

export interface Position {
  x: number;
  y: number;
}

export interface Piece {
  colour: string;
  moved: boolean;
  type: string;
}
