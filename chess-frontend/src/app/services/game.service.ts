import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Coord } from '../models/coord';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  /*
   * Black Piece Positions
   */
  brPos1$ = new BehaviorSubject<Coord>({ x: 0, y: 0 });
  brPos2$ = new BehaviorSubject<Coord>({ x: 7, y: 0 });

  bnPos1$ = new BehaviorSubject<Coord>({ x: 1, y: 0 });
  bnPos2$ = new BehaviorSubject<Coord>({ x: 6, y: 0 });

  bbPos1$ = new BehaviorSubject<Coord>({ x: 2, y: 0 });
  bbPos2$ = new BehaviorSubject<Coord>({ x: 5, y: 0 });

  bqPos$ = new BehaviorSubject<Coord>({ x: 3, y: 0 });

  bkPos$ = new BehaviorSubject<Coord>({ x: 4, y: 0 });

  /*
   * White Piece Positions
   */
  wrPos1$ = new BehaviorSubject<Coord>({ x: 0, y: 7 });
  wrPos2$ = new BehaviorSubject<Coord>({ x: 7, y: 7 });

  wnPos1$ = new BehaviorSubject<Coord>({ x: 1, y: 7 });
  wnPos2$ = new BehaviorSubject<Coord>({ x: 6, y: 7 });

  wbPos1$ = new BehaviorSubject<Coord>({ x: 2, y: 7 });
  wbPos2$ = new BehaviorSubject<Coord>({ x: 5, y: 7 });

  wqPos$ = new BehaviorSubject<Coord>({ x: 3, y: 7 });

  wkPos$ = new BehaviorSubject<Coord>({ x: 4, y: 7 });

  generatePawns() {
    for (let i = 0; i < 8; i++) {}
  }

  constructor() {}
}
