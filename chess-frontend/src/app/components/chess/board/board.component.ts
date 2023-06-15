import { Component } from '@angular/core';
import { Coord } from 'src/app/models/coord';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent {
  sixtyFour = new Array(64).fill(0).map((_, i) => i);

  bnPos1$ = this.game.bnPos1$;
  bnPos2$ = this.game.bnPos2$;
  bbPos1$ = this.game.bbPos1$;
  bbPos2$ = this.game.bbPos2$;
  brPos1$ = this.game.brPos1$;
  brPos2$ = this.game.brPos2$;
  bkPos$ = this.game.bkPos$;
  bqPos$ = this.game.bqPos$;

  wnPos1$ = this.game.wnPos1$;
  wnPos2$ = this.game.wnPos2$;
  wbPos1$ = this.game.wbPos1$;
  wbPos2$ = this.game.wbPos2$;
  wrPos1$ = this.game.wrPos1$;
  wrPos2$ = this.game.wrPos2$;
  wkPos$ = this.game.wkPos$;
  wqPos$ = this.game.wqPos$;

  constructor(private game: GameService) {}

  xy(i: number): Coord {
    console.log('in xy()');
    return {
      x: i % 8,
      y: Math.floor(i / 8),
    };
  }
}
